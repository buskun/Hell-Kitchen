package game.frames;

import components.CustomImageIcon;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class RefrigeratorFrame extends JFrame {
    static final int width = 1200;
    static final int height = 900;

    public RefrigeratorFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("Refrigerator");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("backgroundFrame"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton Burger = new JButton();
        cm.setIcon(Burger, imageLoader.getIcon("Burger"), CM.size(30, CMFlag.BY_H));
        cm.setBounds(Burger, CM.grid(30, 50, CM.size(30, CMFlag.BY_H)));
        Burger.setBounds(0, 0, 100, 200);
        Burger.setOpaque(false);
        Burger.setContentAreaFilled(false);
        Burger.setBorderPainted(false);
        contentPane.add(Burger);
        Burger.addActionListener(e -> getItemListener.accept("Burger"));

        JButton Fish = new JButton();
        cm.setIcon(Fish, imageLoader.getIcon("Fish"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Fish, CM.grid(48, 5, CM.size(20, CMFlag.BY_H)));
        Fish.setOpaque(false);
        Fish.setContentAreaFilled(false);
        Fish.setBorderPainted(false);
        contentPane.add(Fish);
        Fish.addActionListener(e -> getItemListener.accept("Fish"));

        JButton Ketchup = new JButton();
        cm.setIcon(Ketchup, imageLoader.getIcon("Ketchup"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Ketchup, CM.grid(30, 32, CM.size(20, CMFlag.BY_H)));
        Ketchup.setOpaque(false);
        Ketchup.setContentAreaFilled(false);
        Ketchup.setBorderPainted(false);
        contentPane.add(Ketchup);
        Ketchup.addActionListener(e -> getItemListener.accept("Ketchup"));

        JButton Lettuce = new JButton();
        cm.setIcon(Lettuce, imageLoader.getIcon("Lettuce"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Lettuce, CM.grid(48, 32, CM.size(20, CMFlag.BY_H)));
        Lettuce.setOpaque(false);
        Lettuce.setContentAreaFilled(false);
        Lettuce.setBorderPainted(false);
        contentPane.add(Lettuce);
        Lettuce.addActionListener(e -> getItemListener.accept("Lettuce"));

        JButton Meat = new JButton();
        cm.setIcon(Meat, imageLoader.getIcon("Meat"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Meat, CM.grid(30, 60, CM.size(20, CMFlag.BY_H)));
        Meat.setOpaque(false);
        Meat.setContentAreaFilled(false);
        Meat.setBorderPainted(false);
        contentPane.add(Meat);
        Meat.addActionListener(e -> getItemListener.accept("Meat"));

        JButton Potato = new JButton();
        cm.setIcon(Potato, imageLoader.getIcon("Potato"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Potato, CM.grid(48, 60, CM.size(20, CMFlag.BY_H)));
        Potato.setOpaque(false);
        Potato.setContentAreaFilled(false);
        Potato.setBorderPainted(false);
        contentPane.add(Potato);
        Potato.addActionListener(e -> getItemListener.accept("Potato"));

        JButton Rice = new JButton();
        cm.setIcon(Rice, imageLoader.getIcon("Rice"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Rice, CM.grid(68, 60, CM.size(20, CMFlag.BY_H)));
        Rice.setOpaque(false);
        Rice.setContentAreaFilled(false);
        Rice.setBorderPainted(false);
        contentPane.add(Rice);
        Rice.addActionListener(e -> getItemListener.accept("Rice"));

        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}
