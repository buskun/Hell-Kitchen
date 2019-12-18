package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import components.CustomImageIcon;
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

        changeBackground(getImageLoader().getIcon("background").resize(wH, wW));


        JButton startBtn = new JButton(getImageLoader().getIcon("start").resize(size(0.2, 0.1)));
        startBtn.setBounds(grid(0.75, 0.55, 0.2, 0.1));
        startBtn.setOpaque(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
        //startBtn.setFont(getFont().deriveFont(30f));
        startBtn.addMouseListener(new MouseAdapter() {
            CustomImageIcon hoveredIcon = getImageLoader().getIcon("startpress").resize(size(0.2, 0.1));
            CustomImageIcon normalIcon = getImageLoader().getIcon("start").resize(size(0.2, 0.1));
            @Override
            public void mousePressed(MouseEvent e) {
                startBtn.setIcon(this.hoveredIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                startBtn.setIcon(this.normalIcon);
            }
        });
        startBtn.addActionListener(e -> {
            getController().changeScene("SeletionLv");
        });
        add(startBtn);

        JButton loadGameBtn = new JButton(getImageLoader().getIcon("load").resize(size(0.2, 0.1)));
        loadGameBtn.addMouseListener(new MouseAdapter() {
            CustomImageIcon hoveredIcon = getImageLoader().getIcon("loadpress").resize(size(0.2, 0.1));
            CustomImageIcon normalIcon = getImageLoader().getIcon("load").resize(size(0.2, 0.1));
            public void mousePressed(MouseEvent e) {
                loadGameBtn.setIcon(this.hoveredIcon);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                loadGameBtn.setIcon(this.normalIcon);
            }
        });
        loadGameBtn.setBounds(grid(0.75, 0.67, 0.2, 0.1));
        loadGameBtn.setOpaque(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setBorderPainted(false);
        add(loadGameBtn);

        JButton exitGameBtn = new JButton(getImageLoader().getIcon("exit").resize(size(0.2, 0.1)));
        exitGameBtn.addMouseListener(new MouseAdapter() {
            CustomImageIcon hoveredIcon = getImageLoader().getIcon("exitpress").resize(size(0.2, 0.1));
            CustomImageIcon normalIcon = getImageLoader().getIcon("exit").resize(size(0.2, 0.1));
            public void mousePressed(MouseEvent e) {
                exitGameBtn.setIcon(this.hoveredIcon);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                exitGameBtn.setIcon(this.normalIcon);
            }
        });
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);
        exitGameBtn.setBounds(grid(0.75, 0.79, 0.2, 0.1));
        exitGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
