package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;

public class FirstSceneTest extends Scene {
    public FirstSceneTest(Window _window, Controller _controller) {
        super(_window, _controller);
    }

    @Override
    public void init() {
        getImageLoader().addImage("bg", "resources/n.jpg");
        getImageLoader().load();

        changeBackground(getImageLoader().getIcon("bg"));

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() { }

    @Override
    public void onStop() { }
}
