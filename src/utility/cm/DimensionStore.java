package utility.cm;

import java.awt.*;

public class DimensionStore extends CMStore<Dimension> {
    private double w;
    private double h;
    private CMFlag calFlag;

    public DimensionStore(double WRatio, double HRatio, CMFlag flag) {
        w = WRatio;
        h = HRatio;
        calFlag = flag;
    }

    public DimensionStore(double WRatio, double HRatio) {
        this(WRatio, HRatio, CMFlag.NORMAL);
    }

    public void setW(double WRatio) {
        w = WRatio;
    }

    public void setH(double HRatio) {
        h = HRatio;
    }

    public void setFlag(CMFlag flag) {
        calFlag = flag;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public CMFlag getFlag() {
        return calFlag;
    }

    @Override
    public Dimension calculate(double width, double height) {
        return new Dimension(
                (int) (calFlag == CMFlag.BY_H ? height : width * w),
                (int) (calFlag == CMFlag.BY_W ? width : height * h)
        );
    }
}
