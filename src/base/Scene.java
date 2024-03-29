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
    private WindowFrame window;
    private CM cm;
    private boolean readyFlag = false;
    private HashMap<String, JComponent> componentIDMap = new HashMap<>();
    private ArrayList<Animation> animations = new ArrayList<>();
    private boolean imageLoaded = false;
    private boolean audioLoaded = false;
    private HashMap<Integer, Boolean> pressedKey = new HashMap<>();
    private CustomImageIcon background = null;
    private ArrayList<Runnable> backgroundAutoResizeQueue = new ArrayList<>();

    public Scene(WindowFrame _window, Controller _controller) {
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

    public final ImageLoader getImageLoader() {
        return imageLoader;
    }

    public final AudioLoader getAudioLoader() {
        return audioLoader;
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
        resize(true);
    }

    public void resize(boolean source) {
        if (background != null) {
            if (!backgroundAutoResizeQueue.isEmpty()) {
                backgroundAutoResizeQueue.forEach(Runnable::run);
                backgroundAutoResizeQueue = new ArrayList<>();
            }

            backgroundAutoResizeQueue.add(Utility.setTimeout(() -> {
                changeBackground(background);
                if (!source) return;
                controller.getSceneList().values().forEach(sc -> {
                    if (sc != Scene.this) sc.resize(false);
                });

                if (controller.getLoadingScene() != Scene.this)
                    new Thread(() -> {
                        controller.getLoadingScene().resize(false);
                    }).start();
            }, 100));
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
            cm.recalculate();

            if (!isReady()) {
                while (!isReady()) try { wait(); } catch (Exception ignored) {}
            }

            window.addKeyListener(this);
            window.addComponentListener(this);
            window.getContentPane().add(this, BorderLayout.CENTER);
        }
        setVisible(true);

        if (background != null) changeBackground(background);

        window.revalidate();
        window.repaint();

        try {
            onStart();
        } catch (Exception exception) {
            System.err.println("Error while starting Scene " + getClass().getName());
            exception.printStackTrace();
        }
        if (controller.getLoadingScene() != this) controller.getLoadingScene().stop();
        window.requestFocus();
    }

    synchronized public final void stop() {
        try {
            onStop();
        } catch (Exception exception) {
            System.err.println("Error while stopping Scene " + getClass().getName());
            exception.printStackTrace();
        }
        setVisible(false);
        if (controller.getLoadingScene() != this) {
            animations = new ArrayList<>();

            window.removeKeyListener(this);
            window.removeComponentListener(this);

            window.getContentPane().remove(this);
        }

        window.revalidate();
        window.repaint();
        window.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!cm.isReady()) return;

        ArrayList<Animation> toBeRemoved = new ArrayList<>();

        for (Animation animation : animations) {
            if (animation.next()) continue;

            toBeRemoved.add(animation);
        }

        animations.removeAll(toBeRemoved);

        super.paintComponent(g);
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        if (background != null) changeBackground(background);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (background != null) changeBackground(background);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        if (background != null) changeBackground(background);
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        if (background != null) changeBackground(background);
    }

    public final WindowFrame getWindow() { return window; }

    public final Controller getController() { return controller; }

    public final CM getCM() { return cm; }

    public void ready() { readyFlag = true; }

    public void changeBackground(CustomImageIcon imageIcon) {
        background = imageIcon.scaleToFill(window.getSize());
        setIcon(background);
    }

    public void changeBackground(Image image) { changeBackground(new CustomImageIcon(image)); }

    public void changeBackground(String imagePath) { changeBackground(new CustomImageIcon(imagePath)); }

    public boolean isReady() { return readyFlag && imageLoaded && audioLoaded && cm.isReady(); }

    public boolean isKeyPressed(int keyCode, boolean consume) {
        boolean isPressed = Boolean.TRUE.equals(pressedKey.get(keyCode));
        if (consume) pressedKey.put(keyCode, false);
        return isPressed;
    }

    public boolean isKeyPressed(int keyCode) { return isKeyPressed(keyCode, false); }

    public void bindID(String id, JComponent component) { componentIDMap.put(id, component); }

    public void componentID(String id) { componentIDMap.get(id); }

    public String IDOf(JComponent component) {
        return componentIDMap.keySet().stream().filter(_id -> componentIDMap.get(_id) == component).findFirst().orElse(null);
    }

    public void unbindID(String id) { componentIDMap.remove(id); }

    public void unbindID(JComponent component) { unbindID(IDOf(component)); }

    public void addAnimation(Animation animation) { animations.add(animation); }

    public void onStateChange(String name, Object value) { }
}
