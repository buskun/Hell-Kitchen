package game.frames;

import utility.StateManager;
import utility.Utility;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class PotFrame extends JFrame {
    static final int width = 1200;
    static final int height = 700;

    private boolean turnedOn = false;
    private boolean cooking = false;
    private int cookedState = 1;

    public PotFrame(ImageLoader imageLoader, AudioLoader audioLoader, String holdingItem, Consumer<String> getItemListener) {
        setTitle("Pot");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("pot"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton onBtn = new JButton();
        cm.setIcon(onBtn, imageLoader.getIcon("onBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(onBtn, CM.grid(76, 70, CM.size(20, CMFlag.BY_H)));
        onBtn.setOpaque(false);
        onBtn.setContentAreaFilled(false);
        onBtn.setBorderPainted(false);
        contentPane.add(onBtn);
        onBtn.addActionListener(e -> {
            cm.setIcon(contentPane, imageLoader.getIcon("pot-on"));
            cm.recalculateIcon(contentPane);
            remove(onBtn);
            turnedOn = true;
        });

        JButton offBtn = new JButton();
        cm.setIcon(offBtn, imageLoader.getIcon("offBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(offBtn, CM.grid(12, 70, CM.size(20, CMFlag.BY_H)));
        offBtn.setOpaque(false);
        offBtn.setContentAreaFilled(false);
        offBtn.setBorderPainted(false);
        contentPane.add(offBtn);

        offBtn.addActionListener(e -> {
            if (cooking) {
                if (cookedState != 2) {
                    getItemListener.accept("");
                } else {
                    getItemListener.accept(holdingItem + "-boiled");
                }
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

            StateManager<Integer> passedSecond = Utility.useState(-1);

            Utility.setInterval(() -> {
                switch (passedSecond.get()) {
                    case 16:
                        cookedState = 2;
                        break;
                    case 25:
                        cookedState = 3;
                        break;
                    case 30:
                        offBtn.doClick();
                        return false;
                }

                cm.setIcon(contentPane, imageLoader.getIcon("pot-" + holdingItem + "-" + cookedState));
                cm.recalculateIcon(contentPane);

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

