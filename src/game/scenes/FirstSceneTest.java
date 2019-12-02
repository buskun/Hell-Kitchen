package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.Animation;
import utility.AnimationMap;

import javax.swing.*;

public class FirstSceneTest extends Scene {
    JButton button;

    public FirstSceneTest(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void init() {
        getImageLoader().addImage("bg", "resources/n.jpg");
        getImageLoader().load();

        button = new JButton(getImageLoader().getIcon("bg"));
//        changeBackground(getImageLoader().getIcon("bg"));
//        setLayout(new BorderLayout());
        button.setBounds(0, 0, 300, 150);
        add(button);

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() {
        new Animation(this,
                (val) -> button.setLocation((int) Math.round(val), button.getY()),
                () -> (double) button.getX(),
                AnimationMap.EASE_IN_BACK, 300, 1000);
    }

    @Override
    public void onStop() { }
}
