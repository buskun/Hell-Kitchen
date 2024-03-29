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
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

public class CuttingFrame extends JFrame implements MouseMotionListener {
    static final int width = 700;
    static final int height = 450;

    JLabel cursor = new JLabel();

    public CuttingFrame(ImageLoader imageLoader, AudioLoader audioLoader, String holdingItem, Consumer<String> getItemListener) {
        setTitle("cutting");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        CM cm = new CM(() -> this, visible -> {}, () -> null, this::addComponentListener);

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageLoader.get("transparent"), new Point(0, 0), "null"));

        addMouseMotionListener(this);

        JLabel contentPane = new JLabel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        cm.setIcon(cursor, imageLoader.getIcon("knife"), CM.size(15, CMFlag.BY_H));
        cm.setSize(cursor, CM.size(15, CMFlag.BY_H));
        contentPane.add(cursor);

        cm.setIcon(contentPane, imageLoader.getIcon("Bgcutting"), CM.size(100, 100));
        cm.setBounds(contentPane, CM.grid(0, 0, 100, 100));
        contentPane.setLayout(null);

        JLabel food = new JLabel();
        cm.setIcon(food, imageLoader.getIcon("cutting-" + holdingItem), CM.size(20, CMFlag.BY_H));
        cm.setBounds(food, CM.grid(40, 70, CM.size(20, CMFlag.BY_H)));
        contentPane.add(food);

        JLabel cuttingBar = new JLabel();
        cm.setIcon(cuttingBar, imageLoader.getIcon("Cuttingbar"), CM.size(22, 10));
        cm.setBounds(cuttingBar, CM.grid(76, 70, CM.size(22, 10)));
        contentPane.add(cuttingBar);


        JLabel cutDisplay = new JLabel();
        cm.setBounds(cutDisplay, CM.grid(94.5, 50.7, CM.size(48, CMFlag.BY_H)));
        contentPane.add(cutDisplay);
        contentPane.setComponentZOrder(cutDisplay, 0);


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
                audioLoader.get("cut").loop(1);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                audioLoader.get("cut").stop();
                dragStoppingPosition.set(e.getPoint());

                if (dragStartingPosition.get() != null && dragStoppingPosition.get() != null) {
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
                        if (cutCount.get() >= 4) {
                            cm.setIcon(food, imageLoader.getIcon("cutting-" + holdingItem + "-cut"));
                            cm.recalculateIcon(food);
                            getItemListener.accept(holdingItem + "-cut");
                            Utility.setTimeout(CuttingFrame.this::dispose, 1000);
                        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        cursor.setLocation(e.getX() - 10, e.getY() - 10);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cursor.setLocation(e.getX() - 10, e.getY() - 10);
        repaint();
    }
}
