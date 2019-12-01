package utility;

import base.Scene;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Animation {
    Consumer<Double> valueSetter;
    Supplier<Double> valueGetter;
    long lastAnimatedTime;
    long startTime;
    int totalAnimateTime;
    double endValue;
    Scene currentScene;

    public Animation(Scene scene, Consumer<Double> setter, Supplier<Double> getter, double to, int time) {
        valueSetter = setter;
        valueGetter = getter;
        totalAnimateTime = time;
        endValue = to;
        currentScene = scene;

        lastAnimatedTime = -1;
        startTime = -1;

        currentScene.addAnimation(this);
    }

    public boolean next() {
        lastAnimatedTime = System.currentTimeMillis();

        if (startTime == -1) {
            startTime = lastAnimatedTime;
            return true;
        }

        double currentValue = valueGetter.get();

        long remainingTime = (totalAnimateTime - (lastAnimatedTime - startTime));


        if (remainingTime > 0) {
            double newValue = ((endValue - currentValue) * (lastAnimatedTime - startTime) / totalAnimateTime) + currentValue;
            valueSetter.accept(newValue);
            currentScene.repaint();
            return true;
        } else {
            valueSetter.accept(endValue);
            currentScene.repaint();
            return false;
        }
    }
}
