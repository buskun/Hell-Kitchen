package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import utility.cm.CM;
import utility.loader.ImageLoader;

import javax.swing.*;

public class MenuScene extends Scene {

    public MenuScene(WindowFrame _window, Controller _controller) {
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
        imageLoader.add("setting","resources/menu/settingbtn.png");
        imageLoader.add("settingpress","resources/menu/settingbtnP.png");
    }

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

        changeBackground(imageLoader.getIcon("background"));


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
        cm.setIcon(loadGameBtn, imageLoader.getIcon("setting"), CM.size(20, 10));
        cm.setBounds(loadGameBtn, CM.grid(75, 71, 20, 10));
        loadGameBtn.setOpaque(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setBorderPainted(false);

        cm.setActiveIcon(loadGameBtn, imageLoader.getIcon("setting"), imageLoader.getIcon("settingpress"));
        loadGameBtn.addActionListener(e -> getController().changeScene("settingScene"));
        add(loadGameBtn);

        JButton exitGameBtn = new JButton();
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
