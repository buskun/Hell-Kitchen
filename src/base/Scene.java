package base;

import components.CustomImageIcon;
import utility.Animation;
import utility.AudioLoader;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

abstract public class Scene extends JLabel implements KeyListener {
    private ImageLoader imageLoader;
    private AudioLoader audioLoader;
    private Controller controller;
    private Window window;
    private boolean readyFlag = false;
    private HashMap<String, JComponent> componentIDMap = new HashMap<>();
    private ArrayList<Animation> animations = new ArrayList<>();
    private boolean imageLoaded = false;
    private boolean audioLoaded = false;

    public Scene(Window _window, Controller _controller) {
        imageLoader = new ImageLoader(this::onStartLoadingImage, this::onImageLoaded);
        audioLoader = new AudioLoader(this::onStartLoadingAudio, this::onAudioLoaded);

        controller = _controller;
        window = _window;

        addKeyListener(this);

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

    /*
     * Dummy methods
     **/
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) { }

    public void keyReleased(KeyEvent e) { }

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
                while (!isReady()) Thread.currentThread().interrupt();
            }

            controller.getLoadingScene().stop();
        }

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

        removeAll();
        revalidate();
        repaint();

        window.getContentPane().remove(this);

        readyFlag = false;

        window.revalidate();
        window.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean hasAnimation = false;

        for (Iterator<Animation> iterator = animations.iterator(); iterator.hasNext(); ) {
            hasAnimation = true;
            if (iterator.next().next()) continue;

            iterator.remove();
        }

        if (hasAnimation) repaint();
    }

    public final Window getWindow() { return window; }

    public final Controller getController() { return controller; }

    public final void ready() {
        readyFlag = true;
    }

    public final void changeBackground(CustomImageIcon imageIcon) {
        setIcon(imageIcon.resize(window.getSize()));
    }

    public final void changeBackground(Image image) {
        changeBackground(new CustomImageIcon(image));
    }

    public final void changeBackground(String imagePath) {
        changeBackground(new CustomImageIcon(imagePath));
    }

    public final boolean isReady() { return readyFlag && imageLoaded && audioLoaded; }

    public final void bindID(String id, JComponent component) { componentIDMap.put(id, component); }

    public final void componentID(String id) { componentIDMap.get(id); }

    public final String IDOf(JComponent component) {
        return componentIDMap.keySet().stream().filter(_id -> componentIDMap.get(_id) == component).findFirst().orElse(null);
    }

    public final void unbindID(String id) { componentIDMap.remove(id); }

    public final void unbindID(JComponent component) { unbindID(IDOf(component)); }

    public final void addAnimation(Animation animation) { animations.add(animation); }

    public final Rectangle grid(double x, double y, double width, double height) {
        return new Rectangle(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y),
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public final Rectangle grid(Point pos, Dimension size) {
        return new Rectangle(
                (int) Math.round(pos.getX()),
                (int) Math.round(pos.getY()),
                (int) Math.round(size.getWidth()),
                (int) Math.round(size.getHeight())
        );
    }

    public final Rectangle grid(double x, double y, Dimension size) {
        return new Rectangle(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y),
                (int) Math.round(size.getWidth()),
                (int) Math.round(size.getHeight())
        );
    }

    public final Rectangle grid(Point pos, double width, double height) {
        return new Rectangle(
                (int) Math.round(pos.getX()),
                (int) Math.round(pos.getY()),
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public final Dimension size(double width, double height) {
        return new Dimension(
                (int) Math.round(getWidth() * width),
                (int) Math.round(getHeight() * height)
        );
    }

    public final Dimension sizeByW(double val) {
        return new Dimension(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }

    public final Dimension sizeByH(double val) {
        return new Dimension(
                (int) Math.round(getHeight() * val),
                (int) Math.round(getHeight() * val)
        );
    }

    public final Point position(double x, double y) {
        return new Point(
                (int) Math.round(getWidth() * x),
                (int) Math.round(getHeight() * y)
        );
    }

    public final Point positionByW(double val) {
        return new Point(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }

    public final Point positionByH(double val) {
        return position(
                (int) Math.round(getWidth() * val),
                (int) Math.round(getWidth() * val)
        );
    }
}
