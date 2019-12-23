package base;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Window extends JFrame {
    private HashMap<Image, Cursor> cursorList = new HashMap<>();

    public Window(String title, Dimension size, Point position) {
        setTitle(title);
        setBounds(position.x, position.y, size.width, size.height);

        getContentPane().setLayout(new BorderLayout());

        revalidate();
        repaint();
    }

    public void setCursor(Image cursorImage, Point hotSpot, String name) {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, hotSpot, name));
    }

    public void setCursor(Image cursorImage, CursorHotSpotPosition position, String name) {
        int imageWidth = cursorImage.getWidth(null);
        int imageHeight = cursorImage.getHeight(null);

        setCursor(cursorImage, new Point((int) Math.round(position.getX() * imageWidth), (int) Math.round(position.getY() * imageHeight)), name);
    }

    public void setCursor(Image cursorImage, CursorHotSpotPosition position) {
        setCursor(cursorImage, position, "Custom cursor");
    }

    public void setCursor(Image cursorImage) {
        setCursor(cursorImage, CursorHotSpotPosition.TOP_LEFT, "Custom cursor");
    }

    public void setCursor(Image cursorImage, String name) {
        setCursor(cursorImage, CursorHotSpotPosition.TOP_LEFT, name);
    }

    public void setCursor(Image cursorImage, Point hotSpot) {
        setCursor(cursorImage, hotSpot, "Custom cursor");
    }
}

