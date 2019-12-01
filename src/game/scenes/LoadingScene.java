package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.Animator;
import utility.StateManager;
import utility.Utility;

public class LoadingScene extends Scene {
    Animator<String> animator;
    public LoadingScene(Window window, Controller controller) { super(window, controller); }

    @Override
    public void init() {
        StateManager<Integer> iconState = Utility.useState(0);

        animator = new Animator<>(this::changeBackground);

        animator.setAnimationTime(1000);
        animator.setAnimateSequence(new String[]{"resources/background.jpg", "resources/images/base/fallback.png"});

        ready();
    }

    @Override
    public void tick() { }

    @Override
    public void onStart() {
        animator.start();
    }

    @Override
    public void onStop() {
        animator.stop();
    }
}
