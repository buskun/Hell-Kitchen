package game.frames;

import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.util.function.Consumer;

public class cuttingFrame extends JFrame {
    static final int width = 1400;
    static final int height = 900;

    public cuttingFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        setTitle("cutting");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(contentPane, imageLoader.getIcon("Bgcutting"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JLabel tomato = new JLabel();
        cm.setIcon(tomato,imageLoader.getIcon("Ketchup"),CM.size(20,CMFlag.BY_H));
        cm.setBounds(tomato,CM.grid(40,70,CM.size(20,CMFlag.BY_H)));
        contentPane.add(tomato);


        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}
