package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import utility.cm.CM;
import utility.loader.ImageLoader;

import javax.swing.*;

public class Result extends Scene {

    public Result(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }
    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/Result/background.png");
        imageLoader.add("exit", "resources/menu/exit.png");
        imageLoader.add("exitpress", "resources/menu/exitp.png");


    }
    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();
        changeBackground(getImageLoader().getIcon("background"));
        JButton exitGameBtn = new JButton();
        cm.setIcon(exitGameBtn, imageLoader.getIcon("exit"), CM.size(20,10));
        cm.setBounds(exitGameBtn, CM.grid(75, 82, 20, 10));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);

        cm.setActiveIcon(exitGameBtn, imageLoader.getIcon("exit"),imageLoader.getIcon("exitpress"));
        exitGameBtn.addActionListener(e -> getController().changeScene("SeletionLv"));
        add(exitGameBtn);
        ready();
    }

    @Override
    public void tick() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
