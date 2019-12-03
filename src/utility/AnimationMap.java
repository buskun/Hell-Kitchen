package utility;

import java.util.Arrays;

class CubicBezier {
    private double cx;
    private double bx;
    private double ax;

    private double cy;
    private double by;
    private double ay;

    private final double epsilon = 1e-6;

    CubicBezier(double p1x, double p1y, double p2x, double p2y) {
        cx = 3 * p1x;
        bx = 3 * (p2x - p1x) - cx;
        ax = 1 - cx - bx;

        cy = 3 * p1y;
        by = 3 * (p2y - p1y) - cy;
        ay = 1 - cy - by;
    }

    private double sampleCurveX(double t) {
        return ((ax * t + bx) * t + cx) * t;
    }

    private double sampleCurveY(double t) {
        return ((ay * t + by) * t + cy) * t;
    }

    private double sampleCurveDerivativeX(double t) {
        return (3 * ax * t + 2 * bx) * t + cx;
    }

    private double solveCurveX(double x) {
        double t0;
        double t1;
        double t2;
        double x2;
        double d2;
        double i;

        for (t2 = x, i = 0; i < 8; i++) {
            x2 = sampleCurveX(t2) - x;
            if (Math.abs(x2) < epsilon) return t2;
            d2 = sampleCurveDerivativeX(t2);
            if (Math.abs(d2) < epsilon) break;
            t2 = t2 - x2 / d2;
        }

        t0 = 0;
        t1 = 1;
        t2 = x;

        if (t2 < t0) return t0;
        if (t2 > t1) return t1;

        while (t0 < t1) {
            x2 = sampleCurveX(t2);
            if (Math.abs(x2 - x) < epsilon) return t2;
            if (x > x2) t0 = t2;
            else t1 = t2;

            t2 = (t1 - t0) * 0.5 + t0;
        }

        return t2;
    }

    double getValueScale(double percentage) {
        double t = percentage / 100;
        return 100 * sampleCurveY(solveCurveX(t));
    }
}

class AData implements Comparable<AData> {
    private double _percentage;
    private double _valuePercentage;

    AData(double percentage, double valuePercentage) {
        _percentage = percentage;
        _valuePercentage = valuePercentage;
    }

    double getPercentage() {
        return _percentage;
    }

    double getValuePercentage() {
        return _valuePercentage;
    }

    @Override
    public int compareTo(AData anotherData) {
        return Double.compare(_percentage, anotherData._percentage);
    }
}

public enum AnimationMap {
    EASE_IN_SINE(0.47, 0, 0.745, 0.715),
    EASE_OUT_SINE(0.39, 0.575, 0.565, 1),
    EASE_IN_OUT_SINE(0.445, 0.05, 0.55, 0.95),

    EASE_IN_QUAD(0.55, 0.085, 0.68, 0.53),
    EASE_OUT_QUAD(0.25, 0.46, 0.45, 0.94),
    EASE_IN_OUT_QUAD(0.455, 0.03, 0.515, 0.955),

    EASE_IN_CUBIC(0.55, 0.055, 0.675, 0.19),
    EASE_OUT_CUBIC(0.215, 0.61, 0.355, 1),
    EASE_IN_OUT_CUBIC(0.645, 0.045, 0.355, 1),

    EASE_IN_QUART(0.895, 0.03, 0.685, 0.22),
    EASE_OUT_QUART(0.165, 0.84, 0.44, 1),
    EASE_IN_OUT_QUART(0.77, 0, 0.175, 1),

    EASE_IN_QUINT(0.755, 0.05, 0.855, 0.06),
    EASE_OUT_QUINT(0.23, 1, 0.32, 1),
    EASE_IN_OUT_QUINT(0.86, 0, 0.07, 1),

    EASE_IN_EXPO(0.95, 0.05, 0.795, 0.035),
    EASE_OUT_EXPO(0.19, 1, 0.22, 1),
    EASE_IN_OUT_EXPO(1, 0, 0, 1),

    EASE_IN_CIRC(0.6, 0.04, 0.98, 0.335),
    EASE_OUT_CIRC(0.075, 0.82, 0.165, 1),
    EASE_IN_OUT_CIRC(0.785, 0.135, 0.15, 0.86),

    EASE_IN_BACK(0.6, -0.28, 0.735, 0.045),
    EASE_OUT_BACK(0.175, 0.885, 0.32, 1.275),
    EASE_IN_OUT_BACK(0.68, -0.55, 0.265, 1.55),

    EASE_IN_ELASTIC(new AData[]{
            new AData(0, 0),
            new AData(4, 0.04),
            new AData(8, 0.16),
            new AData(14, 0.17),
            new AData(18, -0.04),
            new AData(26, -0.58),
            new AData(28, -0.55),
            new AData(40, 1.56),
            new AData(42, 1.64),
            new AData(56, -4.63),
            new AData(58, -4.4),
            new AData(72, 13.12),
            new AData(86, -37.06),
            new AData(100, 100),
    }),
    EASE_OUT_ELASTIC(new AData[]{
            new AData(0, 0),
            new AData(16, 132.27),
            new AData(28, 86.88),
            new AData(44, 104.63),
            new AData(59, 98.36),
            new AData(73, 100.58),
            new AData(88, 99.8),
            new AData(100, 100),
    }),
    EASE_IN_OUT_ELASTIC(new AData[]{
            new AData(0, 0),
            new AData(4, 0.08),
            new AData(8, 0.1),
            new AData(18, -0.52),
            new AData(20, -0.39),
            new AData(28, 2.35),
            new AData(30, 2.39),
            new AData(38, -9.27),
            new AData(40, -11.75),
            new AData(60, 111.75),
            new AData(62, 109.27),
            new AData(70, 97.61),
            new AData(72, 97.65),
            new AData(80, 100.39),
            new AData(82, 100.52),
            new AData(90, 99.97),
            new AData(92, 99.9),
            new AData(100, 100),
    }),

    EASE_IN_BOUNCE(new AData[]{
            new AData(0, 0),
            new AData(4, 1.54),
            new AData(8, 0.66),
            new AData(18, 6.25),
            new AData(26, 1.63),
            new AData(46, 24.98),
            new AData(64, 1.99),
            new AData(76, 56.44),
            new AData(88, 89.11),
            new AData(100, 100),
    }),
    EASE_OUT_BOUNCE(new AData[]{
            new AData(0, 0),
            new AData(12, 10.89),
            new AData(24, 43.56),
            new AData(36, 98.01),
            new AData(54, 75.02),
            new AData(74, 98.37),
            new AData(82, 93.75),
            new AData(92, 99.34),
            new AData(96, 98.46),
            new AData(100, 100),
    }),
    EASE_IN_OUT_BOUNCE(new AData[]{
            new AData(0, 0),
            new AData(2, 0.77),
            new AData(4, 0.33),
            new AData(10, 3),
            new AData(14, 0.98),
            new AData(22, 12.42),
            new AData(32, 1),
            new AData(42, 40.32),
            new AData(50, 50),
            new AData(58, 59.68),
            new AData(68, 99.01),
            new AData(78, 87.58),
            new AData(86, 90.02),
            new AData(90, 97),
            new AData(96, 99.67),
            new AData(98, 99.23),
            new AData(100, 100),
    });

    private AData[] animationData;
    private CubicBezier cubicBezier;

    AnimationMap(AData[] data) {
        animationData = data;
        Arrays.sort(animationData);

        cubicBezier = null;
    }

    AnimationMap(double p1x, double p1y, double p2x, double p2y) {
        animationData = null;
        cubicBezier = new CubicBezier(p1x, p1y, p2x, p2y);
    }

    public double getValueScale(double percentage) {
        if (animationData == null) {
            if (cubicBezier == null) return percentage;

            return cubicBezier.getValueScale(percentage);
        }

        AData leftBound = new AData(0, 0);
        AData rightBound = new AData(100, 100);

        for (int i = 1; i < animationData.length; i++) {
            if (animationData[i - 1].getPercentage() <= percentage && percentage <= animationData[i].getPercentage()) {
                leftBound = animationData[i - 1];
                rightBound = animationData[i];
                break;
            }
        }

        double leftPercentage = leftBound.getPercentage();
        double rightPercentage = rightBound.getPercentage();
        double leftValue = leftBound.getValuePercentage();
        double rightValue = rightBound.getValuePercentage();

        return leftValue + ((percentage - leftPercentage) * (rightValue - leftValue) / (rightPercentage - leftPercentage));
    }
}
