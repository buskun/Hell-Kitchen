package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import components.CustomImageIcon;
import utility.Utility;
import utility.cm.CM;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuScene extends Scene {

    public MenuScene(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/menu/background.png");
        imageLoader.add("start", "resources/menu/start.png");
        imageLoader.add("load", "resources/menu/load.png");
        imageLoader.add("exit", "resources/menu/exit.png");
        imageLoader.add("startpress", "resources/menu/startp.png");
        imageLoader.add("loadpress", "resources/menu/loadp.png");
        imageLoader.add("exitpress", "resources/menu/exitp.png");
    }

    @Override
    public void init() {
        int wH = getWindow().getHeight();
        int wW = getWindow().getWidth();
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(imageLoader.getIcon("background").resize(wH, wW));


        JButton startBtn = new JButton();
        cm.setIcon(startBtn, imageLoader.getIcon("start"), CM.size(20, 10));
        cm.setBounds(startBtn, CM.grid(75, 60, 20, 10));
        startBtn.setOpaque(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
        //startBtn.setFont(getFont().deriveFont(30f));
        cm.setActiveIcon(startBtn, imageLoader.getIcon("start"), imageLoader.getIcon("startpress"));
        startBtn.addActionListener(e -> getController().changeScene("SeletionLv"));
        add(startBtn);

        JButton loadGameBtn = new JButton();
        cm.setIcon(loadGameBtn, imageLoader.getIcon("load"), CM.size(20, 10));
        cm.setBounds(loadGameBtn, CM.grid(75, 71, 20, 10));
        loadGameBtn.setOpaque(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setBorderPainted(false);

        cm.setActiveIcon(loadGameBtn, imageLoader.getIcon("load"), imageLoader.getIcon("loadpress"));
        add(loadGameBtn);

        JButton exitGameBtn = new JButton(imageLoader.getIcon("exit").resize(size(0.2, 0.1)));
        cm.setIcon(exitGameBtn, imageLoader.getIcon("exit"), CM.size(20,10));
        cm.setBounds(exitGameBtn, CM.grid(75, 82, 20, 10));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);

        cm.setActiveIcon(exitGameBtn, imageLoader.getIcon("exit"),imageLoader.getIcon("exitpress"));
        exitGameBtn.addActionListener(e -> System.exit(0));
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
