package game.frames;

import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class waterFrame extends JFrame {
    static final int width = 1200;
    static final int height = 800;

    public waterFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("cutting");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
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

        JButton PepsiBtn = new JButton();
        cm.setIcon(PepsiBtn, imageLoader.getIcon("Pepsi"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(PepsiBtn, CM.grid(26.5, 10, CM.size(20, CMFlag.BY_H)));
        PepsiBtn.setBounds(20, 10, 100, 200);
        PepsiBtn.setOpaque(false);
        PepsiBtn.setContentAreaFilled(false);
        PepsiBtn.setBorderPainted(false);
        contentPane.add(PepsiBtn);

        JButton SpriteBtn = new JButton();
        cm.setIcon(SpriteBtn, imageLoader.getIcon("Sprite"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(SpriteBtn, CM.grid(59.5, 10, CM.size(20, CMFlag.BY_H)));
        SpriteBtn.setBounds(20, 10, 100, 200);
        SpriteBtn.setOpaque(false);
        SpriteBtn.setContentAreaFilled(false);
        SpriteBtn.setBorderPainted(false);
        contentPane.add(SpriteBtn);

        JButton DrinkLBtn = new JButton();
        cm.setIcon(DrinkLBtn, imageLoader.getIcon("DrinkL"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkLBtn, CM.grid(80, 10, CM.size(20, CMFlag.BY_H)));
        DrinkLBtn.setBounds(20, 10, 100, 200);
        DrinkLBtn.setOpaque(false);
        DrinkLBtn.setContentAreaFilled(false);
        DrinkLBtn.setBorderPainted(false);
        contentPane.add(DrinkLBtn);

        JButton DrinkMBtn = new JButton();
        cm.setIcon(DrinkMBtn, imageLoader.getIcon("DrinkM"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkMBtn, CM.grid(80, 30+5, CM.size(20, CMFlag.BY_H)));
        DrinkMBtn.setBounds(20, 10, 100, 200);
        DrinkMBtn.setOpaque(false);
        DrinkMBtn.setContentAreaFilled(false);
        DrinkMBtn.setBorderPainted(false);
        contentPane.add(DrinkMBtn);

        JButton DrinkSBtn = new JButton();
        cm.setIcon(DrinkSBtn, imageLoader.getIcon("DrinkS"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(DrinkSBtn, CM.grid(80, 50+10, CM.size(20, CMFlag.BY_H)));
        DrinkSBtn.setBounds(20, 10, 100, 200);
        DrinkSBtn.setOpaque(false);
        DrinkSBtn.setContentAreaFilled(false);
        DrinkSBtn.setBorderPainted(false);
        contentPane.add(DrinkSBtn);





        cm.recalculate();



        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}

