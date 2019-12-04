package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.animation.Animation;
import utility.animation.AnimationMap;
import utility.loader.ImageLoader;
import utility.Utility;

import javax.swing.*;

public class FirstSceneTest extends Scene {
    JButton button;

    public FirstSceneTest(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void loadImage(ImageLoader imageLoader) {
        imageLoader.add("bg", "resources/n.jpg");
    }

    @Override
    public void init() {
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
        Utility.setTimeout(this, () -> {
            new Animation(this,
                    val -> button.setLocation(val.intValue(), button.getY()),
                    button::getX,
                    AnimationMap.EASE_IN_ELASTIC, 300, 1000);
        }, 1000);
    }

    @Override
    public void onStop() { }
}
