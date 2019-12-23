package game.frames;

import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class potFrame extends JFrame {
    static final int width = 1200;
    static final int height = 700;

    public potFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("cutting");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("Bgpot"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JButton onBtn = new JButton();
        cm.setIcon(onBtn, imageLoader.getIcon("onBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(onBtn, CM.grid(80, 70, CM.size(20, CMFlag.BY_H)));
        onBtn.setBounds(0, 0, 100, 200);
        onBtn.setOpaque(false);
        onBtn.setContentAreaFilled(false);
        onBtn.setBorderPainted(false);
        contentPane.add(onBtn);

        JButton offBtn = new JButton();
        cm.setIcon(offBtn, imageLoader.getIcon("offBtn"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(offBtn, CM.grid(10, 70, CM.size(20, CMFlag.BY_H)));
        offBtn.setBounds(0, 0, 100, 200);
        offBtn.setOpaque(false);
        offBtn.setContentAreaFilled(false);
        offBtn.setBorderPainted(false);
        contentPane.add(offBtn);






        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}

