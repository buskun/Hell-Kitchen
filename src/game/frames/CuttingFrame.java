package game.frames;

import utility.StateManager;
import utility.Utility;
import utility.cm.CM;
import utility.cm.CMFlag;
import utility.loader.AudioLoader;
import utility.loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.function.Consumer;

public class CuttingFrame extends JFrame {
    static final int width = 1400;
    static final int height = 900;

    public CuttingFrame(ImageLoader imageLoader, AudioLoader audioLoader, Consumer<String> getItemListener) {
        JLabel barTime = new JLabel();
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

        JLabel food = new JLabel();
        cm.setIcon(food, imageLoader.getIcon("Ketchup"), CM.size(20, CMFlag.BY_H));
        cm.setBounds(food, CM.grid(40, 70, CM.size(20, CMFlag.BY_H)));
        contentPane.add(food);

        JLabel cutDisplay = new JLabel();
        cm.setBounds(cutDisplay, CM.grid(90, 50, CM.size(48, CMFlag.BY_H)));
        contentPane.add(cutDisplay);
        //setComponentZOrder(cutDisplay,1);

        cm.setIcon(barTime, imageLoader.getIcon("cuttingBar"), CM.size(21, 14));
        cm.setBounds(barTime, CM.grid(78, 80, CM.size(21, 14)));
        add(barTime);


        StateManager<Point> dragStartingPosition = Utility.useState(Point.class);
        StateManager<Point> dragStoppingPosition = Utility.useState(Point.class);
        StateManager<Boolean> validCut = Utility.useState(false);
        StateManager<Integer> cutCount = Utility.useState(0);

        cutDisplay.setText(cutCount.get().toString());

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if (e.getX() <= food.getLocation().getX()
                        && e.getX() >= food.getLocation().getX() + food.getWidth()) {
                    validCut.set(false);
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                validCut.set(true);
                dragStartingPosition.set(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                dragStoppingPosition.set(e.getPoint());

                if (dragStartingPosition.get() != null || dragStoppingPosition.get() != null) {
                    Point startingPosition = dragStartingPosition.get();
                    Point stoppingPosition = dragStoppingPosition.get();

                    if (
                            startingPosition.getY() <= food.getLocation().getY()
                                    && stoppingPosition.getY() >= food.getLocation().getY() + food.getHeight()
                                    && startingPosition.getX() >= food.getLocation().getX()
                                    && stoppingPosition.getX() <= food.getLocation().getX() + food.getWidth()
                    ) {
                        cutCount.set(cutCount.get() + 1);
                        cutDisplay.setText(cutCount.get().toString());
                        if (cutCount.get() >= 4) dispose();
                    }

                    dragStartingPosition.set(null);
                    dragStoppingPosition.set(null);
                }
            }
        });

        cm.recalculate();

        contentPane.revalidate();
        contentPane.repaint();

        revalidate();
        repaint();
        setVisible(true);
    }
}
