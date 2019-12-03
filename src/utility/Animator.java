package utility;

import java.util.function.Consumer;

public class Animator<T> {
    private Consumer<T> callbackMethod;
    private T[] animateSequence;
    private int[] animateTimeSequence;
    private boolean running = false;
    private int state = 0;
    private int animateTime = 0;

    public Animator(Consumer<T> method) {
        callbackMethod = method;
    }

    public void setAnimateSequence(T[] sequence) {
        animateSequence = sequence;
        setState(state);
    }

    public void setState(int newState) { state = newState % animateSequence.length; }

    public void setAnimationTime(int time) {
        animateTime = time;
        animateTimeSequence = null;
    }

    public void setAnimationTime(int[] timeSequence) {
        animateTime = 0;
        animateTimeSequence = timeSequence;
    }

    public void start() {
        running = true;
        new Thread(() -> {
            while (running) {
                callbackMethod.accept(animateSequence[state]);

                int delay = animateTime;

                if (animateTimeSequence != null && animateTimeSequence.length > state) {
                    delay = animateTimeSequence[state];
                }

                state = (state + 1) % animateSequence.length;

                try { Thread.sleep(delay); } catch (Exception ignored) { }
            }
        }).start();
    }

    public void stop() { running = false; }
}
