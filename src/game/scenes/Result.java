package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import game.Data;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Result extends Scene {

    public Result(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }
    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/Result/background.png");
        imageLoader.add("exit", "resources/menu/exit.png");
        imageLoader.add("exitpress", "resources/menu/exitp.png");
    }
    public void loadAudio(AudioLoader audioLoader) {
        audioLoader.add("theme", "resources/soundtrack/" + Data.songList[(int) getController().getState("song")]);
        audioLoader.add("timeRunout", "resources/soundtrack/timeRunout.wav");
    }
    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();
        changeBackground(getImageLoader().getIcon("background"));
        JButton exitGameBtn = new JButton();
        cm.setIcon(exitGameBtn, imageLoader.getIcon("exit"), CM.size(20,10));
        cm.setBounds(exitGameBtn, CM.grid(75, 82, 20, 10));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);
        cm.setActiveIcon(exitGameBtn, imageLoader.getIcon("exit"),imageLoader.getIcon("exitpress"));
        exitGameBtn.addActionListener(e -> getController().changeScene("SeletionLv"));
        add(exitGameBtn);

        JLabel level = new JLabel();
        level.setText("1");
        level.setFont(new Font("Dimbo", Font.PLAIN, 65));
        cm.setBounds(level, CM.grid(52, 8.5, CM.size(25, CMFlag.BY_H)));
        add(level);

        JLabel numberOrder = new JLabel();
        numberOrder.setText("1");
        numberOrder.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(numberOrder, CM.grid(52, 19.5, CM.size(25, CMFlag.BY_H)));
        add(numberOrder);

        JLabel totalOrderScore = new JLabel();
        totalOrderScore.setText("x20 = ");
        totalOrderScore.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(totalOrderScore, CM.grid(53, 19.5, CM.size(25, CMFlag.BY_H)));
        add(totalOrderScore);

        JLabel tip = new JLabel();
        tip.setText("1");
        tip.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(tip, CM.grid(62, 25, CM.size(25, CMFlag.BY_H)));
        add(tip);

        JLabel orderFail = new JLabel();
        orderFail.setText("0");
        orderFail.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(orderFail, CM.grid(62, 30, CM.size(25, CMFlag.BY_H)));
        add(orderFail);










        ready();
    }

    @Override
    public void tick() {

    }

    @Override
    public void onStart() {
        getAudioLoader().get("timeRunout").loop(Clip.LOOP_CONTINUOUSLY);

    }

    @Override
    public void onStop() {
        getAudioLoader().get("timeRunout").stop();
    }
}
