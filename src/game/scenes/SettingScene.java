package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;
import game.Data;
import utility.Utility;
import utility.cm.CM;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class SettingScene extends Scene {
    public SettingScene(WindowFrame _window, Controller _controller) {
        super(_window, _controller);
    }

    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("background", "resources/setting/background.png");
        imageLoader.add("exit", "resources/menu/exit.png");
        imageLoader.add("exitpress", "resources/menu/exitp.png");
    }

    private JTextField nameTextBox = new JTextField("Your Name");
    private JSlider volumeSlider = new JSlider();
    private JLabel volumeLabel = new JLabel("Volume");
    private String[] difficultyList = new String[]{ "Very easy", "Easy", "Normal", "Hard", "Very hard" };
    private JComboBox<String> songSelectComboBox = new JComboBox<>(Data.songList);
    private JRadioButton[] difficultyButtonList = new JRadioButton[difficultyList.length];

    @Override
    public void init() {
        CM cm = getCM();
        ImageLoader imageLoader = getImageLoader();

//        changeBackground(getImageLoader().getIcon("background"));

        JButton exitGameBtn = new JButton();
        cm.setIcon(exitGameBtn, imageLoader.getIcon("exit"), CM.size(20, 10));
        cm.setBounds(exitGameBtn, CM.grid(75, 82, 20, 10));
        exitGameBtn.setOpaque(false);
        exitGameBtn.setContentAreaFilled(false);
        exitGameBtn.setBorderPainted(false);
        cm.setActiveIcon(exitGameBtn, imageLoader.getIcon("exit"), imageLoader.getIcon("exitpress"));
        exitGameBtn.addActionListener(e -> getController().changeScene("menu"));
        add(exitGameBtn);

        JLabel settingTitleLabel = new JLabel("Setting");
        settingTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingTitleLabel.setFont(Utility.getFont("Dimbo").deriveFont(60f));
        cm.setBounds(settingTitleLabel, CM.grid(30, 5, 40, 15));
        add(settingTitleLabel);


        JLabel nameTitleLabel = new JLabel("Your Name");
        nameTitleLabel.setFont(Utility.getFont("Dimbo").deriveFont(40f));
        cm.setBounds(nameTitleLabel, CM.grid(10, 20, 20, 10));
        add(nameTitleLabel);

        nameTextBox.setFont(Utility.getFont("Dimbo").deriveFont(30f));
        cm.setBounds(nameTextBox, CM.grid(40, 21, 50, 8));
        add(nameTextBox);
        nameTextBox.addCaretListener(e -> getController().changeState("nick_name", nameTextBox.getText()));

        JLabel songSelectTitle = new JLabel("Select Music");
        songSelectTitle.setFont(Utility.getFont("Dimbo").deriveFont(40f));
        cm.setBounds(songSelectTitle, CM.grid(10, 35, 20, 10));
        add(songSelectTitle);

        songSelectComboBox.setFont(Utility.getFont("Dimbo").deriveFont(30f));
        cm.setBounds(songSelectComboBox, CM.grid(40, 36, 50, 8));
        add(songSelectComboBox);
        songSelectComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                getController().changeState("song", songSelectComboBox.getSelectedIndex());
            }
        });

        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyLabel.setFont(Utility.getFont("Dimbo").deriveFont(40f));
        cm.setBounds(difficultyLabel, CM.grid(30, 45, 40, 10));
        add(difficultyLabel);

        ButtonGroup difficultyButtonGroup = new ButtonGroup();
        for (int i = 0; i < difficultyList.length; i++) {
            JRadioButton radioButton = new JRadioButton(difficultyList[i]);
            radioButton.setFont(Utility.getFont("Dimbo").deriveFont(30f));
            cm.setBounds(radioButton, CM.grid(10 + 16 * i, 55, 16, 10));
            difficultyButtonList[i] = radioButton;
            difficultyButtonGroup.add(radioButton);
            add(radioButton);

            int finalI = i;
            radioButton.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getController().changeState("difficulty", finalI + 1);
                }
            });
        }

        volumeLabel.setFont(Utility.getFont("Dimbo").deriveFont(40f));
        cm.setBounds(volumeLabel, CM.grid(10, 70, 20, 10));
        add(volumeLabel);

        cm.setBounds(volumeSlider, CM.grid(30, 70, 60, 10));
        volumeSlider.setOpaque(false);
        add(volumeSlider);
        volumeSlider.addChangeListener(e -> {
            getController().changeState("volume", volumeSlider.getValue());
        });

        cm.recalculate();
        ready();
    }

    @Override
    public void tick() {

    }

    @Override
    public void onStart() {
        nameTextBox.setText(getController().getState("nick_name") == null ? "" : (String) getController().getState("nick_name"));
        difficultyButtonList[(int) getController().getState("difficulty") - 1].setSelected(true);
        volumeSlider.setValue((int) getController().getState("volume"));
        songSelectComboBox.setSelectedIndex((int) getController().getState("song"));
    }

    @Override
    public void onStop() {

    }
}
