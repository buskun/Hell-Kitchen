package game.frames;

import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class RefrigeratorFrame extends JFrame {
    static final int width = 800;
    static final int height = 600;

    public RefrigeratorFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("Refrigerator");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel character = new JLabel();

        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("backgroundFrame"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton Burger = new JButton();
        cm.setIcon(Burger, imageLoader.getIcon("item-bread"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Burger, CM.grid(29, 5, CM.size(20, CMFlag.BY_H)));
        Burger.setBounds(0, 0, 100, 200);
        Burger.setOpaque(false);
        Burger.setContentAreaFilled(false);
        Burger.setBorderPainted(false);
        contentPane.add(Burger);
        Burger.addActionListener(e -> {
            getItemListener.accept("item-bread");
            dispose();
        });

        JButton Fish = new JButton();
        cm.setIcon(Fish, imageLoader.getIcon("item-fish"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Fish, CM.grid(47, 5, CM.size(20, CMFlag.BY_H)));
        Fish.setOpaque(false);
        Fish.setContentAreaFilled(false);
        Fish.setBorderPainted(false);
        contentPane.add(Fish);
        Fish.addActionListener(e -> {
            getItemListener.accept("item-fish");
            dispose();
        });

        JButton Ketchup = new JButton();
        cm.setIcon(Ketchup, imageLoader.getIcon("item-tomato"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Ketchup, CM.grid(29, 32, CM.size(20, CMFlag.BY_H)));
        Ketchup.setOpaque(false);
        Ketchup.setContentAreaFilled(false);
        Ketchup.setBorderPainted(false);
        contentPane.add(Ketchup);
        Ketchup.addActionListener(e -> {
            getItemListener.accept("item-tomato");
            dispose();
        });

        JButton Lettuce = new JButton();
        cm.setIcon(Lettuce, imageLoader.getIcon("item-lettuce"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Lettuce, CM.grid(47, 32, CM.size(20, CMFlag.BY_H)));
        Lettuce.setOpaque(false);
        Lettuce.setContentAreaFilled(false);
        Lettuce.setBorderPainted(false);
        contentPane.add(Lettuce);
        Lettuce.addActionListener(e -> {
            getItemListener.accept("item-lettuce");
            dispose();
        });

        JButton Meat = new JButton();
        cm.setIcon(Meat, imageLoader.getIcon("item-meat"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Meat, CM.grid(29, 60, CM.size(20, CMFlag.BY_H)));
        Meat.setOpaque(false);
        Meat.setContentAreaFilled(false);
        Meat.setBorderPainted(false);
        contentPane.add(Meat);
        Meat.addActionListener(e -> {
            getItemListener.accept("item-meat");
            dispose();
        });

        JButton Potato = new JButton();
        cm.setIcon(Potato, imageLoader.getIcon("item-potato"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Potato, CM.grid(47, 60, CM.size(20, CMFlag.BY_H)));
        Potato.setOpaque(false);
        Potato.setContentAreaFilled(false);
        Potato.setBorderPainted(false);
        contentPane.add(Potato);
        Potato.addActionListener(e -> {
            getItemListener.accept("item-potato");
            dispose();
        });

        JButton Rice = new JButton();
        cm.setIcon(Rice, imageLoader.getIcon("item-rice"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(Rice, CM.grid(67, 60, CM.size(20, CMFlag.BY_H)));
        Rice.setOpaque(false);
        Rice.setContentAreaFilled(false);
        Rice.setBorderPainted(false);
        contentPane.add(Rice);
        Rice.addActionListener(e -> {
            getItemListener.accept("item-rice");
            dispose();
        });

        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}
