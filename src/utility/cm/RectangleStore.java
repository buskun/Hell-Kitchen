package utility.cm;

import java.awt.*;

public class RectangleStore extends CMStore<Rectangle> {
    private double x;
    private double y;
    private double w;
    private double h;
    private CMFlag calLocationFlag;
    private CMFlag calSizeFlag;

    public RectangleStore(double XRatio, double YRatio, CMFlag locationFlag, double WRatio, double HRatio, CMFlag sizeFlag) {
        x = XRatio;
        y = YRatio;
        w = WRatio;
        h = HRatio;
        calLocationFlag = locationFlag;
        calSizeFlag = sizeFlag;
    }

    public RectangleStore(double XRatio, double YRatio, double WRatio, double HRatio, CMFlag sizeFlag) {
        this(XRatio, YRatio, CMFlag.NORMAL, WRatio, HRatio, sizeFlag);
    }

    public RectangleStore(double XRatio, double YRatio, CMFlag locationFlag, double WRatio, double HRatio) {
        this(XRatio, YRatio, locationFlag, WRatio, HRatio, CMFlag.NORMAL);
    }

    public RectangleStore(double XRatio, double YRatio, double WRatio, double HRatio) {
        this(XRatio, YRatio, CMFlag.NORMAL, WRatio, HRatio, CMFlag.NORMAL);
    }

    public void setX(double XRatio) {
        x = XRatio;
    }

    public void setY(double YRatio) {
        y = YRatio;
    }

    public void setLocationFlag(CMFlag locationFlag) {
        calLocationFlag = locationFlag;
    }

    public void setW(double WRatio) {
        w = WRatio;
    }

    public void setH(double HRatio) {
        h = HRatio;
    }

    public void setSizeFlag(CMFlag sizeFlag) {
        calSizeFlag = sizeFlag;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public CMFlag getLocationFlag() {
        return calLocationFlag;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public CMFlag getSizeFlag() {
        return calSizeFlag;
    }

    public PointStore getLocation() {
        return new PointStore(x, y, calLocationFlag);
    }

    public DimensionStore getSize() {
        return new DimensionStore(w, h, calSizeFlag);
    }

    @Override
    public Rectangle calculate(double width, double height) {
        return new Rectangle(
                (int) (calLocationFlag == CMFlag.BY_H ? height : width * x),
                (int) (calLocationFlag == CMFlag.BY_W ? width : height * y),
                (int) (calSizeFlag == CMFlag.BY_H ? height : width * w),
                (int) (calSizeFlag == CMFlag.BY_W ? width : height * h)
        );
    }
}
