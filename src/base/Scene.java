package base;

import components.CustomImageIcon;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

abstract public class Scene extends JLabel implements KeyListener {
    private ImageLoader imageLoader;
    private Controller controller;
    private Window window;
    private boolean readyFlag = false;

    public Scene(Window _window, Controller _controller) {
        imageLoader = new ImageLoader(this);

        controller = _controller;
        window = _window;

        addKeyListener(this);

        setBounds(0, 0, window.getWidth(), window.getHeight());
        setLayout(null);

        setVisible(false);
    }

    public abstract void init();

    public abstract void tick();

    public abstract void onStart();

    public abstract void onStop();

    protected ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void onStartLoadingResource() {
        controller.getLoadingScene().start();
    }

    public void onResourceLoaded() {
        new Thread(() -> {
            while (!readyFlag) Thread.currentThread().interrupt();
            controller.getLoadingScene().stop();
        }).start();
    }

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
        if (!readyFlag) init();

        setVisible(true);
        window.getContentPane().add(this, BorderLayout.CENTER);

        window.revalidate();
        window.repaint();

        onStart();
    }

    synchronized public final void stop() {
        onStop();

        setVisible(false);

        removeAll();
        revalidate();
        repaint();

        window.getContentPane().remove(this);

        readyFlag = false;

        window.revalidate();
        window.repaint();
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

    public boolean isReady() { return readyFlag && !imageLoader.isLoading(); }
}
