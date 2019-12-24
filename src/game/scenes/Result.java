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

    private JLabel totalScore = new JLabel("0");
    private JLabel level = new JLabel("0");
    private JLabel numberOrder = new JLabel();
    private JLabel totalOrderScore = new JLabel();

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();
        changeBackground(getImageLoader().getIcon("background"));
        JButton exitGameBtn = new JButton();
        cm.setIcon(exitGameBtn, imageLoader.getIcon("exit"), CM.size(20, 10));
        cm.setBounds(exitGameBtn, CM.grid(75, 82, 20, 10));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);
        cm.setActiveIcon(exitGameBtn, imageLoader.getIcon("exit"), imageLoader.getIcon("exitpress"));
        exitGameBtn.addActionListener(e -> getController().changeScene("SeletionLv"));
        add(exitGameBtn);

        level.setText("1");
        level.setFont(new Font("Dimbo", Font.PLAIN, 65));
        cm.setBounds(level, CM.grid(52, 8.5, CM.size(25, CMFlag.BY_H)));
        add(level);

        numberOrder.setText("0");
        numberOrder.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(numberOrder, CM.grid(51, 19.5, CM.size(25, CMFlag.BY_H)));
        add(numberOrder);

        JLabel orderLabel = new JLabel();
        orderLabel.setText("x20 = ");
        orderLabel.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(orderLabel, CM.grid(53, 19.5, CM.size(25, CMFlag.BY_H)));
        add(orderLabel);

        totalOrderScore.setText("0");
        totalOrderScore.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(totalOrderScore, CM.grid(62, 19.5, CM.size(25, CMFlag.BY_H)));
        add(totalOrderScore);

        JLabel tip = new JLabel();
        tip.setText("0");
        tip.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(tip, CM.grid(62, 25, CM.size(25, CMFlag.BY_H)));
        add(tip);

        JLabel orderFail = new JLabel();
        orderFail.setText("0");
        orderFail.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(orderFail, CM.grid(62, 30, CM.size(25, CMFlag.BY_H)));
        add(orderFail);

        totalScore.setFont(new Font("Dimbo", Font.PLAIN, 40));
        cm.setBounds(totalScore, CM.grid(62, 42, CM.size(25, CMFlag.BY_H)));
        add(totalScore);

        cm.recalculate();
        ready();
    }

    @Override
    public void tick() {

    }

    @Override
    public void onStart() {
        numberOrder.setText(Integer.toString(getController().getState("score") != null ? (int) getController().getState("score") / 20 : 0));
        totalOrderScore.setText(Integer.toString(getController().getState("score") != null ? (int) getController().getState("score") : 0));
        totalScore.setText(Integer.toString(getController().getState("score") != null ? (int) getController().getState("score") : 0));
        level.setText(Integer.toString((int) getController().getState("level")));
        getAudioLoader().get("timeRunout").loop(Clip.LOOP_CONTINUOUSLY);

    }

    @Override
    public void onStop() {
        getAudioLoader().get("timeRunout").stop();
    }
}
