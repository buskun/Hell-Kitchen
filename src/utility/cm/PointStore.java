package utility.cm;

import java.awt.*;

public class PointStore extends CMStore<Point> {
    private double x;
    private double y;
    private CMFlag calFlag;

    public PointStore(double XRatio, double YRatio, CMFlag flag) {
        x = XRatio;
        y = YRatio;
        calFlag = flag;
    }

    public PointStore(double XRatio, double YRatio) {
        this(XRatio, YRatio, CMFlag.NORMAL);
    }

    public PointStore setX(double xRatio) {
        x = xRatio;
        return this;
    }

    public PointStore setY(double yRatio) {
        y = yRatio;
        return this;
    }

    public PointStore setFlag(CMFlag flag) {
        calFlag = flag;
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public CMFlag getFlag() {
        return calFlag;
    }

    @Override
    public Point calculate(double width, double height) {
        return new Point(
                (int) ((calFlag == CMFlag.BY_H ? height : width) * x / 100),
                (int) ((calFlag == CMFlag.BY_W ? width : height) * y / 100)
        );
    }
}
