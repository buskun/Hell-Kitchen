package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;

public class LoadingScene extends Scene {
    public LoadingScene(Window window, Controller controller) { super(window, controller); }

    @Override
    public void init() {
        changeBackground("resources/background.jpg");

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() { }

    @Override
    public void onStop() { }
}
