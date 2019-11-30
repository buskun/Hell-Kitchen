package base;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(String title, Dimension size, Point position) {
        setTitle(title);
        setBounds(position.x, position.y, size.width, size.height);

        getContentPane().setLayout(new BorderLayout());

        repaint();
    }
}
