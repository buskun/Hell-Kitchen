package utility;

import javax.swing.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class Animator<T> {
    private Consumer<T> callbackMethod;
    private T[] animateSequence;
    private int[] animateTimeSequence;
    private boolean running = false;
    private Thread animatorThread;
    private int state = 0;
    private int animateTime = 0;

    public Animator(Consumer<T> method) {
        callbackMethod = method;
        animatorThread = new Thread(() -> {
            while (running) {
                int delay = animateTime;

                if (animateTimeSequence.length > state) {
                    delay = animateTimeSequence[state];
                }

                try { Thread.sleep(delay); } catch (Exception ignored) { }

                callbackMethod.accept(animateSequence[state]);

                state = (state + 1) % animateSequence.length;
            }
        });
    }

    public void setAnimateSequence(T[] sequence) {
        animateSequence = sequence;

        if (animateTimeSequence == null) setAnimationTime(animateTime);
    }

    public void setState(int newState) { state = newState % animateSequence.length; }

    public void setAnimationTime(int time) {
        animateTime = time;

        if (animateSequence == null) {
            animateTimeSequence = null;
            return;
        }

        animateTimeSequence = new int[animateSequence.length];

        Arrays.fill(animateTimeSequence, 0, animateTimeSequence.length, animateTime);
    }

    public void setAnimationTimeSequence(int[] timeSequence) { animateTimeSequence = timeSequence; }

    public void start() {
        running = true;
        animatorThread.start();
    }

    public void stop() { running = false; }
}
