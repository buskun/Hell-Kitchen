package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends Scene {

    public MenuScene(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background","resources/menu/background.png");
        imageLoader.add("start","resources/menu/start.png");
        imageLoader.add("load","resources/menu/load.png");
        imageLoader.add("exit","resources/menu/exit.png");
        imageLoader.add("startp","resources/menu/startp.png");
        imageLoader.add("loadp","resources/menu/loadp.png");
        imageLoader.add("exitp","resources/menu/exitp.png");
    }

    @Override
    public void init() {
        int wH = getWindow().getHeight();
        int wW = getWindow().getWidth();

        changeBackground(getImageLoader().getIcon("background").resize(wH,wW));


        JButton startBtn = new JButton(getImageLoader().getIcon("start").resize(size(0.2, 0.1)));
        startBtn.setBounds(grid(0.75, 0.55, 0.2, 0.1));
        startBtn.setOpaque(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
       //startBtn.setFont(getFont().deriveFont(30f));
        startBtn.addChangeListener(e -> {
            startBtn.setIcon(getImageLoader().getIcon("startp"));
        });

        startBtn.addActionListener(e -> {
            getController().changeScene("SeletionLv");

        });
        add(startBtn);

        JButton loadGameBtn = new JButton(getImageLoader().getIcon("load").resize(size(0.2, 0.1)));
        loadGameBtn.setBounds(grid(0.75,0.67,0.2,0.1));
        loadGameBtn.setOpaque(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setBorderPainted(false);
        add(loadGameBtn);

        JButton exitGameBtn = new JButton(getImageLoader().getIcon("exit").resize(size(0.2, 0.1)));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);
        exitGameBtn.setBounds(grid(0.75,0.79,0.2,0.1));
        exitGameBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    System.exit(0);
            }
        });
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
