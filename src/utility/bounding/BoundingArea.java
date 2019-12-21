package utility.bounding;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class BoundingArea {
    HashMap<String, JComponent> entities = new HashMap<>();
    ArrayList<Function<String, Boolean>> intersectionListener = new ArrayList<>();

    public void add(String name, JComponent entity) { entities.put(name, entity); }

    public void remove(String name) { entities.remove(name); }

    public void remove(JComponent entity) {
        for (String name : entities.keySet()) {
            if (entities.get(name) == entity) {
                remove(name);
                break;
            }
        }
    }

    public void addIntersectionListener(Function<String, Boolean> listener) { intersectionListener.add(listener); }

    public void removeIntersectionListener(Function<String, Boolean> listener) { intersectionListener.remove(listener); }

    public boolean intersects(Rectangle rectangle) {
        boolean intersect = false;
        for (String name : entities.keySet()) {
            if (entities.get(name).getBounds().intersects(rectangle.getBounds())) {
                emitIntersectionEvent(name);
                intersect = true;
            }
        }

        return intersect;
    }

    public boolean intersects(JComponent component) {
        return intersects(component.getBounds());
    }

    public boolean intersects(BoundingArea boundingArea) {
        boolean intersect = false;
        for (String name : boundingArea.entities.keySet()) {
            if (intersects(boundingArea.entities.get(name))) {
                boundingArea.emitIntersectionEvent(name);
                intersect = true;
            }
        }

        return intersect;
    }

    private void emitIntersectionEvent(String name) {
        ArrayList<Function<String, Boolean>> toBeRemoved = new ArrayList<>();

        intersectionListener.forEach(listener -> {
            if (!listener.apply(name)) toBeRemoved.add(listener);
        });

        intersectionListener.removeAll(toBeRemoved);
    }
}
