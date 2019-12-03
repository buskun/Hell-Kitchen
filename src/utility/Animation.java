package utility;

import base.Scene;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Animation {
    private Consumer<Double> valueSetter;
    private long lastAnimatedTime;
    private long startTime;
    private int totalAnimateTime;
    private double endValue;
    private double startValue;
    private AnimationMap easingEffect;

    public Animation(Scene scene, Consumer<Double> setter, Double initValue, AnimationMap easing, double to, int time) {
        valueSetter = setter;
        totalAnimateTime = time;
        endValue = to;
        startValue = initValue;
        easingEffect = easing;

        lastAnimatedTime = -1;
        startTime = -1;

        scene.addAnimation(this);
    }

    public boolean next() {
        lastAnimatedTime = System.currentTimeMillis();

        if (startTime == -1) {
            startTime = lastAnimatedTime;
            return true;
        }

        long remainingTime = (totalAnimateTime - (lastAnimatedTime - startTime));


        if (remainingTime > 0) {
            double newValue = ((endValue - startValue) * easingEffect.getValueScale((double) 100 * (lastAnimatedTime - startTime)/ totalAnimateTime)) / 100;
            valueSetter.accept(newValue);
            return true;
        } else {
            valueSetter.accept(endValue);
            return false;
        }
    }
}
