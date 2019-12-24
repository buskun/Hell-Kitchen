package game.frames;

import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class DrinkFrame extends JFrame {
    static final int width = 900;
    static final int height = 600;
    private String currentSize = "";
    private String currentDrink = "";
    private boolean filled = false;
    JButton cup = new JButton();
    JLabel drop = new JLabel();
    Animation dropAnimation;

    CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);
    ImageLoader imageLoader;

    public DrinkFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("Drink machine");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.imageLoader = imageLoader;

        JLabel contentPane = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (dropAnimation != null) {
                    if (!dropAnimation.next()) dropAnimation = null;
                    if (drop != null && cup != null && drop.getBounds().intersects(cup.getBounds())) {
                        dropAnimation.stop();
                        dropAnimation = null;
                        audioLoader.get("soda").loop(1);

                        cm.setIcon(cup, imageLoader.getIcon(currentDrink + "-" + currentSize));
                        cm.recalculateIcon(cup);

                        drop.setVisible(false);
                        filled = true;
                    }
                    this.repaint();
                }
            }
        };
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("Bgdrink"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton fantaBtn = new JButton();
        cm.setIcon(fantaBtn, imageLoader.getIcon("Fanta"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(fantaBtn, CM.grid(43.5, 10, CM.size(20, CMFlag.BY_H)));
        fantaBtn.setBounds(20, 10, 100, 200);
        fantaBtn.setOpaque(false);
        fantaBtn.setContentAreaFilled(false);
        fantaBtn.setBorderPainted(false);
        contentPane.add(fantaBtn);

        fantaBtn.addActionListener(e -> changeDrink("fanta"));

        JButton PepsiBtn = new JButton();
        cm.setIcon(PepsiBtn, imageLoader.getIcon("Pepsi"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(PepsiBtn, CM.grid(26.5, 10, CM.size(20, CMFlag.BY_H)));
        PepsiBtn.setBounds(20, 10, 100, 200);
        PepsiBtn.setOpaque(false);
        PepsiBtn.setContentAreaFilled(false);
        PepsiBtn.setBorderPainted(false);
        contentPane.add(PepsiBtn);

        PepsiBtn.addActionListener(e -> changeDrink("pepsi"));

        JButton SpriteBtn = new JButton();
        cm.setIcon(SpriteBtn, imageLoader.getIcon("Sprite"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(SpriteBtn, CM.grid(59.5, 10, CM.size(20, CMFlag.BY_H)));
        SpriteBtn.setOpaque(false);
        SpriteBtn.setContentAreaFilled(false);
        SpriteBtn.setBorderPainted(false);
        contentPane.add(SpriteBtn);

        SpriteBtn.addActionListener(e -> changeDrink("sprite"));

        JButton DrinkLBtn = new JButton();
        cm.setIcon(DrinkLBtn, imageLoader.getIcon("DrinkL"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkLBtn, CM.grid(80, 10, CM.size(20, CMFlag.BY_H)));
        DrinkLBtn.setOpaque(false);
        DrinkLBtn.setContentAreaFilled(false);
        DrinkLBtn.setBorderPainted(false);
        contentPane.add(DrinkLBtn);

        DrinkLBtn.addActionListener(e -> changeCup("l"));

        JButton DrinkMBtn = new JButton();
        cm.setIcon(DrinkMBtn, imageLoader.getIcon("DrinkM"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkMBtn, CM.grid(80, 30 + 5, CM.size(20, CMFlag.BY_H)));
        DrinkMBtn.setOpaque(false);
        DrinkMBtn.setContentAreaFilled(false);
        DrinkMBtn.setBorderPainted(false);
        contentPane.add(DrinkMBtn);

        DrinkMBtn.addActionListener(e -> changeCup("m"));

        JButton DrinkSBtn = new JButton();
        cm.setIcon(DrinkSBtn, imageLoader.getIcon("DrinkS"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkSBtn, CM.grid(80, 50 + 10, CM.size(20, CMFlag.BY_H)));
        DrinkSBtn.setOpaque(false);
        DrinkSBtn.setContentAreaFilled(false);
        DrinkSBtn.setBorderPainted(false);
        contentPane.add(DrinkSBtn);

        DrinkSBtn.addActionListener(e -> changeCup("s"));

        cm.setBounds(drop, CM.grid(47, 40, 6, 9));
        drop.setVisible(false);
        drop.setOpaque(false);
        contentPane.add(drop);

        cup.setVisible(false);
        cup.setOpaque(false);
        cup.setContentAreaFilled(false);
        cup.setBorderPainted(false);
        contentPane.add(cup);

        cup.addActionListener(e -> {
            if (filled) {
                getItemListener.accept("drink-" + currentDrink + "-" + currentSize);
                dispose();
            }
        });

        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }

    private void changeCup(String size) {
        currentSize = size;
        switch (currentSize) {
            case "s":
                cm.setBounds(cup, CM.grid(45, 80, 10, 20));
                cm.setIcon(cup, imageLoader.getIcon("cup-s"), CM.size(10, 20));
                break;
            case "m":
                cm.setBounds(cup, CM.grid(42.5, 70, 15, 30));
                cm.setIcon(cup, imageLoader.getIcon("cup-m"), CM.size(15, 30));
                break;
            case "l":
                cm.setBounds(cup, CM.grid(40, 60, 20, 40));
                cm.setIcon(cup, imageLoader.getIcon("cup-l"), CM.size(20, 40));
                break;
        }

        cm.recalculate(cup);
        cup.setVisible(true);
    }

    private void changeDrink(String drink) {
        if (currentSize.equals("")) return;
        if (!currentDrink.equals("")) return;

        currentDrink = drink;

        cm.setIcon(drop, imageLoader.getIcon("drop-" + currentDrink), CM.size(6, 9));
        cm.recalculate(drop);

        drop.setVisible(true);

        new Animation(
                (Animation animation) -> dropAnimation = animation,
                y -> drop.setLocation(drop.getX(), (int) Math.floor(y)),
                drop::getY,
                AnimationMap.EASE_OUT_SINE, getHeight(), 1000
        );
    }
}

