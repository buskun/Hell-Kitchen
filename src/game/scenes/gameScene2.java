package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.cm.CM;
import utility.cm.CMFlag;
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
        imageLoader.add("background", "resources/gameScene/Bgfridge.png");
        imageLoader.add("Burger", "resources/gameScene/Burger.png");
        imageLoader.add("Fish", "resources/gameScene/Fish.png");
        imageLoader.add("Ketchup", "resources/gameScene/Ketchup.png");
        imageLoader.add("Lettuce", "resources/gameScene/Lettuce.png");
        imageLoader.add("Meat", "resources/gameScene/Meat.png");
        imageLoader.add("Potato", "resources/gameScene/Potato.png");
        imageLoader.add("Rice", "resources/gameScene/Rice.png");
    }


    @Override
    public void init() {
        changeBackground(getImageLoader().getIcon("background"));
        CM cm = getCM();
        JButton Burger = new JButton(getImageLoader().getIcon("Burger").resize(sizeByH(0.13)));
        cm.setIcon(Burger, getImageLoader().getIcon("Burger"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Burger, CM.grid(30, 5,  CM.size(20, CMFlag.BY_H)));
        Burger.setOpaque(false);
        Burger.setContentAreaFilled(false);
        Burger.setBorderPainted(false);
        add(Burger);

        JButton Fish = new JButton(getImageLoader().getIcon("Fish").resize(sizeByH(0.13)));
        cm.setIcon(Fish, getImageLoader().getIcon("Fish"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Fish, CM.grid(48, 5,  CM.size(20, CMFlag.BY_H)));
        Fish.setOpaque(false);
        Fish.setContentAreaFilled(false);
        Fish.setBorderPainted(false);
        add(Fish);

        JButton Ketchup = new JButton(getImageLoader().getIcon("Ketchup").resize(sizeByH(0.13)));
        cm.setIcon(Ketchup, getImageLoader().getIcon("Ketchup"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Ketchup, CM.grid(30, 32,  CM.size(20, CMFlag.BY_H)));
        Ketchup.setOpaque(false);
        Ketchup.setContentAreaFilled(false);
        Ketchup.setBorderPainted(false);
        add(Ketchup);

        JButton Lettuce = new JButton(getImageLoader().getIcon("Lettuce").resize(sizeByH(0.13)));
        cm.setIcon(Lettuce, getImageLoader().getIcon("Lettuce"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Lettuce, CM.grid(48, 32,  CM.size(20, CMFlag.BY_H)));
        Lettuce.setOpaque(false);
        Lettuce.setContentAreaFilled(false);
        Lettuce.setBorderPainted(false);
        add(Lettuce);

        JButton Meat = new JButton(getImageLoader().getIcon("Meat").resize(sizeByH(0.13)));
        cm.setIcon(Meat, getImageLoader().getIcon("Meat"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Meat, CM.grid(30, 60,  CM.size(20, CMFlag.BY_H)));
        Meat.setOpaque(false);
        Meat.setContentAreaFilled(false);
        Meat.setBorderPainted(false);
        add(Meat);

        JButton Potato = new JButton(getImageLoader().getIcon("Potato").resize(sizeByH(0.13)));
        cm.setIcon(Potato, getImageLoader().getIcon("Potato"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Potato, CM.grid(48, 60,  CM.size(20, CMFlag.BY_H)));
        Potato.setOpaque(false);
        Potato.setContentAreaFilled(false);
        Potato.setBorderPainted(false);
        add(Potato);

        JButton Rice = new JButton(getImageLoader().getIcon("Rice").resize(sizeByH(0.13)));
        cm.setIcon(Rice, getImageLoader().getIcon("Rice"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Rice, CM.grid(68, 60,  CM.size(20, CMFlag.BY_H)));
        Rice.setOpaque(false);
        Rice.setContentAreaFilled(false);
        Rice.setBorderPainted(false);
        add(Rice);






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
