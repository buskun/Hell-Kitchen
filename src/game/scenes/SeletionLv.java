package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class SeletionLv extends Scene {

    public SeletionLv(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("seletion", "resources/seletionlv/LevelSelection.png");
        imageLoader.add("closebtn", "resources/seletionlv/closebtn.png");
        imageLoader.add("1", "resources/seletionlv/1.png");
        imageLoader.add("2", "resources/seletionlv/2.png");
        imageLoader.add("3", "resources/seletionlv/3.png");
        imageLoader.add("4", "resources/seletionlv/4.png");
        imageLoader.add("5", "resources/seletionlv/5.png");
        imageLoader.add("6", "resources/seletionlv/6.png");
        imageLoader.add("7", "resources/seletionlv/7.png");
        imageLoader.add("8", "resources/seletionlv/8.png");
        imageLoader.add("9", "resources/seletionlv/9.png");
        imageLoader.add("10", "resources/seletionlv/10.png");
        imageLoader.add("star","resources/seletionlv/star.png");
        imageLoader.add("starvictory","resources/seletionlv/starvictory.png");
    }

    @Override
    public void init() {
        int wH = getWindow().getHeight();
        int wW = getWindow().getWidth();
        System.out.printf("%d %d", wH, wW);

        changeBackground(getImageLoader().getIcon("seletion"));
        JLabel totalStar = new JLabel("Test");
        totalStar.setText("10/30");
        totalStar.setFont(new Font("Dimbo", Font.PLAIN, 75));
        totalStar.setBounds(grid(0.12,0-0.015,sizeByH(0.25)));
        add(totalStar);


        JButton backBtn = new JButton(getImageLoader().getIcon("closebtn").resize(sizeByH(0.13)));
        backBtn.addActionListener(e -> {
            getController().changeScene("MenuScene");
        });
        backBtn.setBounds(grid(0.80,0.050,0.073,0.13));
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        add(backBtn);


        JButton btn1 = new JButton(getImageLoader().getIcon("1").resize(sizeByH(0.15)));
        btn1.setBounds(grid(0.07, 0.3, sizeByH(0.15)));
        btn1.setOpaque(false);
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        btn1.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn1);

        JLabel lv1star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv1star1.setBounds(grid(0.030,0.455,sizeByH(0.1)));//ลบด้วย 0.055 ของอันกลาง
        add(lv1star1);
        JLabel lv1star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv1star2.setBounds(grid(0.085,0.455,sizeByH(0.1)));//++0.015 ของ X
        add(lv1star2);
        JLabel lv1star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv1star3.setBounds(grid(0.14,0.455,sizeByH(0.1)));
        add(lv1star3);

        JButton btn2 = new JButton(getImageLoader().getIcon("2").resize(sizeByH(0.15)));
        btn2.setBounds(grid(0.26, 0.3, sizeByH(0.15)));
        btn2.setOpaque(false);
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        btn2.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn2);
        JLabel lv2star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv2star1.setBounds(grid(0.22,0.455,sizeByH(0.1)));
        add(lv2star1);
        JLabel lv2star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv2star2.setBounds(grid(0.275,0.455,sizeByH(0.1)));
        add(lv2star2);
        JLabel lv2star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv2star3.setBounds(grid(0.33,0.455,sizeByH(0.1)));
        add(lv2star3);

        JButton btn3 = new JButton(getImageLoader().getIcon("3").resize(sizeByH(0.15)));
        btn3.setBounds(grid(0.45, 0.3, sizeByH(0.15)));
        btn3.setOpaque(false);
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn3.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn3);

        JLabel lv3star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv3star1.setBounds(grid(0.41,0.455,sizeByH(0.1)));
        add(lv3star1);
        JLabel lv3star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv3star2.setBounds(grid(0.465,0.455,sizeByH(0.1)));
        add(lv3star2);
        JLabel lv3star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv3star3.setBounds(grid(0.52,0.455,sizeByH(0.1)));
        add(lv3star3);


        JButton btn4 = new JButton(getImageLoader().getIcon("4").resize(sizeByH(0.15)));
        btn4.setBounds(grid(0.64, 0.3, sizeByH(0.15)));
        btn4.setOpaque(false);
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn4.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn4);

        JLabel lv4star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv4star1.setBounds(grid(0.6,0.455,sizeByH(0.1)));
        add(lv4star1);
        JLabel lv4star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv4star2.setBounds(grid(0.655,0.455,sizeByH(0.1)));
        add(lv4star2);
        JLabel lv4star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv4star3.setBounds(grid(0.71,0.455,sizeByH(0.1)));
        add(lv4star3);


        JButton btn5 = new JButton(getImageLoader().getIcon("5").resize(sizeByH(0.15)));
        btn5.setBounds(grid(0.83, 0.3, sizeByH(0.15)));
        btn5.setOpaque(false);
        btn5.setContentAreaFilled(false);
        btn5.setBorderPainted(false);
        btn5.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn5);

        JLabel lv5star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv5star1.setBounds(grid(0.79,0.455,sizeByH(0.1)));
        add(lv5star1);
        JLabel lv5star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv5star2.setBounds(grid(0.845,0.455,sizeByH(0.1)));
        add(lv5star2);
        JLabel lv5star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv5star3.setBounds(grid(0.9,0.455,sizeByH(0.1)));
        add(lv5star3);

        JButton btn6 = new JButton(getImageLoader().getIcon("6").resize(sizeByH(0.15)));
        btn6.setBounds(grid(0.07, 0.6, sizeByH(0.15)));
        btn6.setOpaque(false);
        btn6.setContentAreaFilled(false);
        btn6.setBorderPainted(false);
        btn6.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn6);

        JLabel lv6star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv6star1.setBounds(grid(0.030,0.755,sizeByH(0.1)));//ลบด้วย 0.055 ของอันกลาง
        add(lv6star1);
        JLabel lv6star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv6star2.setBounds(grid(0.085,0.755,sizeByH(0.1)));//++0.015 ของ X
        add(lv6star2);
        JLabel lv6star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv6star3.setBounds(grid(0.14,0.755,sizeByH(0.1)));
        add(lv6star3);

        JButton btn7 = new JButton(getImageLoader().getIcon("7").resize(sizeByH(0.15)));
        btn7.setBounds(grid(0.26, 0.6, sizeByH(0.15)));
        btn7.setOpaque(false);
        btn7.setContentAreaFilled(false);
        btn7.setBorderPainted(false);
        btn7.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn7);
        JLabel lv7star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv7star1.setBounds(grid(0.22,0.755,sizeByH(0.1)));
        add(lv7star1);
        JLabel lv7star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv7star2.setBounds(grid(0.275,0.755,sizeByH(0.1)));
        add(lv7star2);
        JLabel lv7star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv7star3.setBounds(grid(0.33,0.755,sizeByH(0.1)));
        add(lv7star3);

        JButton btn8 = new JButton(getImageLoader().getIcon("8").resize(sizeByH(0.15)));
        btn8.setBounds(grid(0.45, 0.6, sizeByH(0.15)));
        btn8.setOpaque(false);
        btn8.setContentAreaFilled(false);
        btn8.setBorderPainted(false);
        btn8.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn8);

        JLabel lv8star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv8star1.setBounds(grid(0.41,0.755,sizeByH(0.1)));
        add(lv8star1);
        JLabel lv8star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv8star2.setBounds(grid(0.465,0.755,sizeByH(0.1)));
        add(lv8star2);
        JLabel lv8star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv8star3.setBounds(grid(0.52,0.755,sizeByH(0.1)));
        add(lv8star3);

        JButton btn9 = new JButton(getImageLoader().getIcon("9").resize(sizeByH(0.15)));
        btn9.setBounds(grid(0.64, 0.6, sizeByH(0.15)));
        btn9.setOpaque(false);
        btn9.setContentAreaFilled(false);
        btn9.setBorderPainted(false);
        btn9.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn9);

        JLabel lv9star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv9star1.setBounds(grid(0.6,0.755,sizeByH(0.1)));
        add(lv9star1);
        JLabel lv9star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv9star2.setBounds(grid(0.655,0.755,sizeByH(0.1)));
        add(lv9star2);
        JLabel lv9star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv9star3.setBounds(grid(0.71,0.755,sizeByH(0.1)));
        add(lv9star3);

        JButton btn10 = new JButton(getImageLoader().getIcon("10").resize(sizeByH(0.15)));
        btn10.setBounds(grid(0.83, 0.6, sizeByH(0.15)));
        btn10.setOpaque(false);
        btn10.setContentAreaFilled(false);
        btn10.setBorderPainted(false);
        btn10.addActionListener(e -> {
            //getController().changeScene("MenuScene");
        });
        add(btn10);

        JLabel lv10star1 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv10star1.setBounds(grid(0.79,0.755,sizeByH(0.1)));
        add(lv10star1);
        JLabel lv10star2 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv10star2.setBounds(grid(0.845,0.755,sizeByH(0.1)));
        add(lv10star2);
        JLabel lv10star3 = new JLabel(getImageLoader().getIcon("star").resize(sizeByH(0.1)));
        lv10star3.setBounds(grid(0.9,0.755,sizeByH(0.1)));
        add(lv10star3);



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
