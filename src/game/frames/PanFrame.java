package game.frames;

import utility.StateManager;
import utility.Utility;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.function.Consumer;

public class PanFrame extends JFrame {
    static final int width = 900;
    static final int height = 525;

    private boolean turnedOn = false;
    private boolean cooking = false;
    private int cookedState = 1;
    private Runnable interval;

    public PanFrame(ImageLoader imageLoader, AudioLoader audioLoader, String holdingItem, Consumer<String> getItemListener) {
        setTitle("pan");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("pan"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton onBtn = new JButton();
        cm.setIcon(onBtn, imageLoader.getIcon("onBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(onBtn, CM.grid(70, 70, CM.size(20, CMFlag.BY_H)));
        onBtn.setOpaque(false);
        onBtn.setContentAreaFilled(false);
        onBtn.setBorderPainted(false);
        contentPane.add(onBtn);
        onBtn.addActionListener(e -> {
            audioLoader.get("Frying").loop(Clip.LOOP_CONTINUOUSLY);
            cm.setIcon(contentPane, imageLoader.getIcon("pan-on"));
            cm.recalculateIcon(contentPane);
            remove(onBtn);
            turnedOn = true;
        });

        JButton offBtn = new JButton();
        cm.setIcon(offBtn, imageLoader.getIcon("offBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(offBtn, CM.grid(10, 70, CM.size(20, CMFlag.BY_H)));
        offBtn.setOpaque(false);
        offBtn.setContentAreaFilled(false);
        offBtn.setBorderPainted(false);
        contentPane.add(offBtn);

        offBtn.addActionListener(e -> {
            if (cooking && cookedState == 2) {
                if (interval != null) interval.run();
                getItemListener.accept(holdingItem + "-fried");
                audioLoader.get("Frying").stop();
            }

            dispose();
        });

        JButton pan = new JButton();
        cm.setBounds(pan, CM.grid(28, 50, 35, 50));
        pan.setOpaque(false);
        pan.setContentAreaFilled(false);
        pan.setBorderPainted(false);
        contentPane.add(pan);

        pan.addActionListener(e -> {
            if (!turnedOn) return;

            cooking = true;
            getItemListener.accept("");

            JLabel food = new JLabel();
            cm.setBounds(food, CM.grid(37, 56, CM.size(18, CMFlag.BY_W)));
            cm.recalculate(food);
            food.setOpaque(false);
            contentPane.add(food);

            StateManager<Integer> passedSecond = Utility.useState(-1);

            interval = Utility.setInterval(() -> {
                switch (passedSecond.get()) {
                    case 8:
                        cookedState = 2;
                        break;
                    case 15:
                        cookedState = 3;
                        break;
                    case 20:
                        offBtn.doClick();
                        return false;
                }

                cm.setIcon(food, imageLoader.getIcon("pan-" + holdingItem + "-" + cookedState), CM.size(18, CMFlag.BY_W));
                cm.recalculateIcon(food);

                passedSecond.set(passedSecond.get() + 1);

                return true;
            }, 1000);

            remove(pan);
        });

        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}
