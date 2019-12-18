package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.loader.ImageLoader;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class gameScene2 extends Scene{

    public gameScene2(Window _window, Controller _controller) {
        super(_window, _controller);
    }
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/gameScene/background.jpg");
    }


    @Override
    public void init() {
        int wH = getWindow().getHeight();
        int wW = getWindow().getWidth();

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
