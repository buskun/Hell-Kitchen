package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.Animator;
import utility.StateManager;
import utility.Utility;

public class LoadingScene extends Scene {
    public LoadingScene(Window window, Controller controller) { super(window, controller); }

    @Override
    public void init() {
        changeBackground("resources/background.jpg");

        StateManager<Integer> iconState = Utility.useState(0);

        Animator<String> animator = new Animator<>(this::changeBackground);

        animator.setAnimationTime(1000);
        animator.setAnimateSequence(new String[]{"resources/images/base/fallback.png", "resources/background.jpg"});

        animator.start();

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() { }

    @Override
    public void onStop() { }
}
