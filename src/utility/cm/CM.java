package utility.cm;

import base.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class CM extends ComponentAdapter {
    public static RectangleStore grid(double x, double y, CMFlag locationFlag, double width, double height, CMFlag sizeFlag) {
        return new RectangleStore(
                x, y, locationFlag,
                width, height, sizeFlag
        );
    }

    public static RectangleStore grid(double x, double y, CMFlag locationFlag, double width, double height) {
        return grid(
                x, y, locationFlag,
                width, height, CMFlag.NORMAL
        );
    }

    public static RectangleStore grid(double x, double y, double width, double height, CMFlag sizeFlag) {
        return grid(
                x, y, CMFlag.NORMAL,
                width, height, sizeFlag
        );
    }

    public static RectangleStore grid(double x, double y, double width, double height) {
        return grid(
                x, y, CMFlag.NORMAL,
                width, height, CMFlag.NORMAL
        );
    }

    public static RectangleStore grid(double x, double y, DimensionStore size) {
        return grid(
                x, y, CMFlag.NORMAL,
                size.getW(), size.getH(), size.getFlag()
        );
    }

    public static RectangleStore grid(PointStore pos, double width, double height) {
        return grid(
                pos.getX(), pos.getY(), pos.getFlag(),
                width, height, CMFlag.NORMAL
        );
    }

    public static RectangleStore grid(PointStore pos, DimensionStore size) {
        return grid(
                pos.getX(), pos.getY(), pos.getFlag(),
                size.getW(), size.getH(), size.getFlag()
        );
    }

    public static RectangleStore grid(RectangleStore bound) {
        return grid(bound.getLocation(), bound.getSize());
    }

    public static DimensionStore size(double width, double height, CMFlag flag) {
        return new DimensionStore(width, height, flag);
    }

    public static DimensionStore size(double width, double height) {
        return size(width, height, CMFlag.NORMAL);
    }

    public static DimensionStore size(double val, CMFlag flag) {
        return size(val, val, flag);
    }

    public static DimensionStore size(double val) {
        return size(val, val, CMFlag.NORMAL);
    }

    public static PointStore position(double x, double y, CMFlag flag) {
        return new PointStore(x, y, flag);
    }

    public static PointStore position(double x, double y) {
        return position(x, y, CMFlag.NORMAL);
    }

    public static PointStore position(double val, CMFlag flag) {
        return position(val, val, flag);
    }

    public static PointStore position(double val) {
        return position(val, val, CMFlag.NORMAL);
    }

    private Scene cScene;
    private ArrayList<CMData<Point>> pointCalculationList = new ArrayList<>();
    private ArrayList<CMData<Dimension>> dimensionCalculationList = new ArrayList<>();

    public CM(Scene scene) {
        cScene = scene;

        cScene.addComponentListener(this);
    }

    public void setBounds(JComponent component, RectangleStore bounds) {
        setLocation(component, bounds.getLocation());
        setSize(component, bounds.getSize());
    }

    public void setSize(JComponent component, DimensionStore dimension) {
        component.setSize(dimension.calculate(cScene.getWidth(), cScene.getHeight()));

        boolean foundDim = false;
        for (CMData<Dimension> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                foundDim = true;
                dimData.setStore(dimension);
                break;
            }
        }

        if (!foundDim) dimensionCalculationList.add(new CMData<>(component, dimension));
    }

    public void setLocation(JComponent component, PointStore location) {
        component.setLocation(location.calculate(cScene.getWidth(), cScene.getHeight()));

        boolean foundPoint = false;
        for (CMData<Point> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                foundPoint = true;
                pointData.setStore(location);
                break;
            }
        }

        if (!foundPoint) pointCalculationList.add(new CMData<>(component, location));
    }
    public void recalculate(JComponent component) {
        int width = cScene.getWidth();
        int height = cScene.getHeight();

        for (CMData<Point> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                pointData.getComponent().setLocation(pointData.getStore().calculate(width, height));
                break;
            }
        }

        for (CMData<Dimension> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                dimData.getComponent().setSize(dimData.getStore().calculate(width, height));
                break;
            }
        }
    }

    public void recalculate() {
        int width = cScene.getWidth();
        int height = cScene.getHeight();

        pointCalculationList.forEach(pointData -> pointData.getComponent().setLocation(pointData.getStore().calculate(width, height)));
        dimensionCalculationList.forEach(dimData -> dimData.getComponent().setSize(dimData.getStore().calculate(width, height)));
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);

        recalculate();
    }
}
