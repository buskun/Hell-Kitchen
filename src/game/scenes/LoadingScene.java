package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.StateManager;
import utility.Utility;

public class LoadingScene extends Scene {
    public LoadingScene(Window window, Controller controller) { super(window, controller); }

    @Override
    public void init() {
        changeBackground("resources/background.jpg");

        StateManager<Integer> iconState = Utility.useState(0);

        Utility.setInterval(() -> {
            if (iconState.get() == 0) {
                changeBackground("resources/images/base/fallback.png");
                iconState.set(1);
            } else if (iconState.get() == 1) {
                changeBackground("resources/background.jpg");
                iconState.set(0);
            }

            return true;
        }, 1000);

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() { }

    @Override
    public void onStop() { }
}
