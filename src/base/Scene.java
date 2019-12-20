package base;

import components.CustomImageIcon;
import utility.Utility;
import utility.animation.Animation;
import utility.cm.CM;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Scene extends JLabel implements KeyListener, ComponentListener {
    private ImageLoader imageLoader;
    private AudioLoader audioLoader;
    private Controller controller;
    private Window window;
    private CM cm;
    private boolean readyFlag = false;
    private HashMap<String, JComponent> componentIDMap = new HashMap<>();
    private ArrayList<Animation> animations = new ArrayList<>();
    private boolean imageLoaded = false;
    private boolean audioLoaded = false;
    private HashMap<Integer, Boolean> pressedKey = new HashMap<>();
    private CustomImageIcon background = null;
    ArrayList<Runnable> backgroundAutoResizeQueue = new ArrayList<>();

    public Scene(Window _window, Controller _controller) {
        imageLoader = new ImageLoader(this::onStartLoadingImage, this::onImageLoaded);
        audioLoader = new AudioLoader(this::onStartLoadingAudio, this::onAudioLoaded);
        cm = new CM(this);

        controller = _controller;
        window = _window;

        setBounds(0, 0, window.getWidth(), window.getHeight());
        setLayout(null);

        setVisible(false);

        new Thread(() -> {
            loadImage(imageLoader);
            loadAudio(audioLoader);
        }).start();
    }

    public void loadImage(ImageLoader imageLoader) {}

    public void loadAudio(AudioLoader audioLoader) {}

    public abstract void init();

    public abstract void tick();

    public abstract void onStart();

    public abstract void onStop();

    protected ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void onStartLoadingImage() { imageLoaded = false; }

    public void onImageLoaded() { imageLoaded = true; }

    public void onStartLoadingAudio() { audioLoaded = false; }

    public void onAudioLoaded() { audioLoaded = true; }

    public void keyTyped(KeyEvent e) { }

    public final void keyPressed(KeyEvent e) {
        pressedKey.put(e.getKeyCode(), true);
        onKeyPress(e);
    }

    public void onKeyPress(KeyEvent e) {}

    public final void keyReleased(KeyEvent e) {
        pressedKey.put(e.getKeyCode(), false);
        onKeyReleased(e);
    }

    public void onKeyReleased(KeyEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        if (background != null) {
            if (!backgroundAutoResizeQueue.isEmpty()) {
                backgroundAutoResizeQueue.forEach(Runnable::run);
                backgroundAutoResizeQueue = new ArrayList<>();
            }

            backgroundAutoResizeQueue.add(Utility.setTimeout(() -> changeBackground(background), 100));
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }

    /*
     * Class Methods
     **/
    synchronized public final void start() {
        if (controller.getLoadingScene() != this) {
            controller.getLoadingScene().start();

            if (!imageLoaded) imageLoader.load();
            if (!audioLoaded) audioLoader.load();
            if (!readyFlag) init();

            if (!isReady()) {
                while (!isReady()) try { wait(); } catch (Exception ignored) {}
            }

            controller.getLoadingScene().stop();
        }

        window.addKeyListener(this);
        window.addComponentListener(this);
        if (background != null) changeBackground(background);
        setVisible(true);
        window.getContentPane().add(this, BorderLayout.CENTER);

        window.revalidate();
        window.repaint();

        try {
            onStart();
        } catch (Exception exception) {
            System.err.println("Error while starting Scene " + getClass().getName());
            exception.printStackTrace();
        }

        window.requestFocus();
    }

    synchronized public final void stop() {
        try {
            onStop();
        } catch (Exception exception) {
            System.err.println("Error while stopping Scene " + getClass().getName());
            exception.printStackTrace();
        }
        animations = new ArrayList<>();

        setVisible(false);
        window.removeKeyListener(this);
        window.removeComponentListener(this);

//        removeAll();
//        revalidate();
//        repaint();

        window.getContentPane().remove(this);

//        readyFlag = false;

        window.revalidate();
        window.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean hasAnimation = false;
        ArrayList<Animation> toBeRemoved = new ArrayList<>();

        for (Animation animation : animations) {
            hasAnimation = true;
            if (animation.next()) continue;

            toBeRemoved.add(animation);
        }

        if (hasAnimation) {
            animations.removeAll(toBeRemoved);
            repaint();
        }
    }

    public final Window getWindow() { return window; }

    public final Controller getController() { return controller; }

    public final CM getCM() { return cm; }

    public void ready() { readyFlag = true; }

    public void changeBackground(CustomImageIcon imageIcon) {
        background = imageIcon.resize(window.getSize());
        setIcon(background);
    }

    public void changeBackground(Image image) { changeBackground(new CustomImageIcon(image)); }

    public void changeBackground(String imagePath) { changeBackground(new CustomImageIcon(imagePath)); }

    public boolean isReady() { return readyFlag && imageLoaded && audioLoaded; }

    public boolean isKeyPressed(int keyCode) { return Boolean.TRUE.equals(pressedKey.get(keyCode)); }

    public void bindID(String id, JComponent component) { componentIDMap.put(id, component); }

    public void componentID(String id) { componentIDMap.get(id); }

    public String IDOf(JComponent component) {
        return componentIDMap.keySet().stream().filter(_id -> componentIDMap.get(_id) == component).findFirst().orElse(null);
    }

    public void unbindID(String id) { componentIDMap.remove(id); }

    public void unbindID(JComponent component) { unbindID(IDOf(component)); }

    public void addAnimation(Animation animation) { animations.add(animation); }

    public Rectangle grid(double x, double y, double width, double height) {
        return new Rectangle(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y),
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public Rectangle grid(Point pos, Dimension size) {
        return new Rectangle(
                (int) Math.round(pos.getX()),
                (int) Math.round(pos.getY()),
                (int) Math.round(size.getWidth()),
                (int) Math.round(size.getHeight())
        );
    }

    public Rectangle grid(double x, double y, Dimension size) {
        return new Rectangle(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y),
                (int) Math.round(size.getWidth()),
                (int) Math.round(size.getHeight())
        );
    }

    public Rectangle grid(Point pos, double width, double height) {
        return new Rectangle(
                (int) Math.round(pos.getX()),
                (int) Math.round(pos.getY()),
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public Dimension size(double width, double height) {
        return new Dimension(
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public Dimension sizeByW(double val) {
        return new Dimension(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }

    public Dimension sizeByH(double val) {
        return new Dimension(
                (int) Math.round(getHeight() * val),
                (int) Math.round(getHeight() * val)
        );
    }

    public Point position(double x, double y) {
        return new Point(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y)
        );
    }

    public Point positionByW(double val) {
        return new Point(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }

    public Point positionByH(double val) {
        return position(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }
}
