package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.bounding.BoundingArea;
import utility.cm.CM;
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
        imageLoader.add("head","resources/gameScene/Iconplayer.png");
        imageLoader.add("refrigerator","resources/gameScene/refrigerator.png");
        imageLoader.add("BarLeft","resources/gameScene/left.png");
        imageLoader.add("BarCenter","resources/gameScene/bar.png");
        imageLoader.add("BarUnder","resources/gameScene/under.png");

    }
    int wH = getWindow().getHeight();
    int wW = getWindow().getWidth();
    BoundingArea map = new BoundingArea();
    JLabel character = new JLabel();
    JLabel refrigerator = new JLabel();
    JLabel barButton = new JLabel();
    JLabel barLeft = new JLabel();
    JLabel barCenter = new JLabel();
    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(getImageLoader().getIcon("background").resize(wH, wW));

        cm.setIcon(character,imageLoader.getIcon("head"), CM.size(10, 20));
        cm.setBounds(character, CM.grid(20, 20, 15, 20));
        add(character);

        cm.setIcon(refrigerator,imageLoader.getIcon("refrigerator"), CM.size(20, 40));
        cm.setBounds(refrigerator, CM.grid(80, 20, 20, 40));
        add(refrigerator);

        cm.setIcon(barButton,imageLoader.getIcon("BarUnder"), CM.size(40, 20));
        cm.setBounds(barButton, CM.grid(30, 70, 40, 20));
        add(barButton);

        cm.setIcon(barLeft,imageLoader.getIcon("BarLeft"), CM.size(20, 40));
        cm.setBounds(barLeft, CM.grid(0, 20, 20, 40));
        add(barLeft);

        cm.setIcon(barCenter,imageLoader.getIcon("BarCenter"), CM.size(40, 20));
        cm.setBounds(barCenter, CM.grid(30, 40, 40, 20));
        add(barCenter);
        if (map.intersects(character)) {
            /* Do something */
        }
        ready();
    }

    @Override
    public void tick() {
    if(isKeyPressed(KeyEvent.VK_UP)){
        Container p = getParent();
        int x = getLocation().x;
        int y = getLocation().y;
        character.setLocation(character.getX(),character.getY()-20);
         y = getLocation().y;
        if(y < 0) {y = 0;}
        if (y + wH > p.getHeight())  y = p.getHeight() - wH;
        //setLocation(x, y);
        character.setLocation(character.getX(),character.getY());
        }
        if(isKeyPressed(KeyEvent.VK_DOWN)){
            Container p = getParent();
            int x = getLocation().x;
            int y = getLocation().y;
            character.setLocation(character.getX(),character.getY()+20);
            y = getLocation().y;
            if(y < 0) {y = 0;}
            if (y + wH > p.getHeight())  y = p.getHeight() - wH;
            //setLocation(x, y);
            character.setLocation(character.getX(),character.getY());
        }
        if(isKeyPressed(KeyEvent.VK_LEFT)){
            Container p = getParent();
            int x = getLocation().x;
            int y = getLocation().y;
            character.setLocation(character.getX()-20,character.getY());
        }
        if(isKeyPressed(KeyEvent.VK_RIGHT)){
            Container p = getParent();
            int x = getLocation().x;
            int y = getLocation().y;
            character.setLocation(character.getX()+20,character.getY());
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
