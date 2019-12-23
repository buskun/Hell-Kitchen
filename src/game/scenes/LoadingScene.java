package game.scenes;

import base.Controller;
import base.Scene;
import base.WindowFrame;

public class LoadingScene extends Scene {
    public LoadingScene(WindowFrame window, Controller controller) {
        super(window, controller);
    }

    @Override
    public void init() {
        changeBackground("resources/Loadingnarak.png");
        ready();
    }

    @Override
    public void tick() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
