package utility.animation;

import base.Scene;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Animation {
    private Consumer<Double> valueSetter;
    private Supplier<Number> valueGetter;
    private long lastAnimatedTime;
    private long startTime;
    private int totalAnimateTime;
    private double endValue;
    private double startValue;
    private AnimationMap easingEffect;
    private boolean stopFlag;

    public Animation(Consumer<Animation> addAnimation, Consumer<Double> setter, Supplier<Number> getter, AnimationMap easing, double to, int time) {
        valueSetter = setter;
        valueGetter = getter;
        totalAnimateTime = time;
        endValue = to;
        easingEffect = easing;
        stopFlag = false;

        lastAnimatedTime = -1;
        startTime = -1;

        addAnimation.accept(this);
    }

    public Animation(Scene scene, Consumer<Double> setter, Supplier<Number> getter, AnimationMap easing, double to, int time) {
        valueSetter = setter;
        valueGetter = getter;
        totalAnimateTime = time;
        endValue = to;
        easingEffect = easing;
        stopFlag = false;

        lastAnimatedTime = -1;
        startTime = -1;

        scene.addAnimation(this);
    }

    public boolean next() {
        if (stopFlag) return false;
        lastAnimatedTime = System.currentTimeMillis();

        if (startTime == -1) {
            startValue = valueGetter.get().doubleValue();
            startTime = lastAnimatedTime;
            return true;
        }

        long remainingTime = (totalAnimateTime - (lastAnimatedTime - startTime));
        double newValue = endValue;

        if (remainingTime > 0) {
            newValue = startValue + ((endValue - startValue) * easingEffect.getValueScale((double) 100 * (lastAnimatedTime - startTime) / totalAnimateTime)) / 100;
        }

        valueSetter.accept(newValue);

        return remainingTime > 0;
    }

    public void stop() { stopFlag = true; }
}
