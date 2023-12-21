package game;

import javax.swing.*;

public class Order {
    private JComponent orderComponent;
    private String orderName;

    public Order(String name, JComponent component) {
        orderName = name;
        orderComponent = component;
    }

    public boolean is(String name) {
        return orderName.equals("order-" + name);
    }

    public JComponent getComponent() {
        return orderComponent;
    }

    public String getName() {
        return orderName;
    }
}
