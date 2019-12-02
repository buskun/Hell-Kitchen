package game.scenes;

import base.Controller;
import base.Scene;
import base.Window;
import utility.*;

public class LoadingScene extends Scene {
    Animator<String> animator;

    public LoadingScene(Window window, Controller controller) { super(window, controller); }

    @Override
    public void init() {
        StateManager<Integer> iconState = Utility.useState(0);

        animator = new Animator<>(this::changeBackground);

        animator.setAnimationTime(200);
        animator.setAnimateSequence(new String[]{"resources/background.jpg", "resources/images/base/fallback.png"});

        new Thread(() -> {
            iconState.set(5);
            iconState.get();
        }).start();

        Utility.setInterval(() -> {
            // do Sth
            return true;
        }, 1000);

        Utility.setTimeout(() -> {
            // do Sth
        }, 1000);

        StateManager<Double> value = Utility.useState(0.0);

        new Animation(this, (val) -> {
            System.out.println(val);
            value.set(val);
        }, value::get, AnimationMap.EASE_IN_ELASTIC, 100, 1000);

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
