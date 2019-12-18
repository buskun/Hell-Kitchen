package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.bounding.BoundingArea;
import utility.loader.ImageLoader;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class gameScene1 extends Scene{

    public gameScene1(Window _window, Controller _controller) {
        super(_window, _controller);
    }
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/gameScene/background.jpg");
        imageLoader.add("head","resources/gameScene/head.jpg");
    }
    int wH = getWindow().getHeight();
    int wW = getWindow().getWidth();
    BoundingArea map = new BoundingArea();
    JLabel character = null;
    public void moveUp() {
        Container p = getParent();
        int x = getLocation().x;
        int y = getLocation().y;
        setLocation(x,y-25);
        y = getLocation().y;
        if(y < 0) {y = 0;}
        if (y + wH > p.getHeight())  y = p.getHeight() - wH;
        setLocation(x, y);
        System.out.println(getLocation().y);
    }
    public void moveDown() {
        Container p = getParent();
        int x = getLocation().x;
        int y = getLocation().y;
        setLocation(x,y+25);
        y = getLocation().y;
        if(y < 0) {y = 0;}
        if (y + wH > p.getHeight())  y = p.getHeight() - wH;


        setLocation(x, y);

        System.out.println(getLocation().y);
    }

    public void moveLeft() {
        Container p = getParent();
        int x = getLocation().x;
        int y = getLocation().y;
        setLocation(x-25,y);
        x = getLocation().x;

        if (x < 0   )  x = p.getWidth();

        if (x + wW  > p.getWidth())   x = p.getWidth() - wW;

        System.out.println(getLocation().x);
        setLocation(x, y);
    }

    public void moveRight() {
        Container p = getParent();
        int x = getLocation().x;
        int y = getLocation().y;
        setLocation(x+25,y);
        x = getLocation().x;
        if (x > p.getWidth() - this.wW   )  x = 0;

        if (x + wW  > p.getWidth())   x = p.getWidth() - wW;

        setLocation(x, y);
        System.out.println(getLocation().x);
    }
    @Override
    public void init() {


        changeBackground(getImageLoader().getIcon("background").resize(wH, wW));
        character = new JLabel(getImageLoader().getIcon("head").resize(sizeByH(0.2)));
        character.setBounds(grid(0.5,0.5,sizeByH(0.2)));
        add(character);

        ready();
    }

    @Override
    public void tick() {
    if(isKeyPressed(KeyEvent.VK_UP)){
        character.setLocation(character.getX(),character.getY()+25);
        }
    repaint();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
