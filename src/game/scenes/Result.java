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


    }
    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();
        changeBackground(imageLoader.getIcon("background"));
        JButton back = new JButton();
        cm.setIcon(back, imageLoader.getIcon("load"), CM.size(20, 10));
        cm.setBounds(back, CM.grid(75, 71, 20, 10));
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
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
