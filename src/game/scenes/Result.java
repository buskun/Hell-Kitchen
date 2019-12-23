package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.cm.CM;
import utility.loader.ImageLoader;

import javax.swing.*;

public class Result extends Scene {

    public Result(Window _window, Controller _controller) {
        super(_window, _controller);
    }
    @Override
    public void loadImage(ImageLoader imageLoader) {

    }
    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();
        changeBackground(imageLoader.getIcon("background"));

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
