package utility.cm;

import base.Scene;
import components.CustomImageIcon;

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
    private ArrayList<CMData<PointStore, ?>> pointCalculationList = new ArrayList<>();
    private ArrayList<CMData<DimensionStore, ?>> dimensionCalculationList = new ArrayList<>();
    private ArrayList<CMData<DimensionStore, CustomImageIcon>> iconCalculationList = new ArrayList<>();

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
        for (CMData<DimensionStore, ?> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                foundDim = true;
                dimData.setStore(dimension);
                break;
            }
        }

        if (!foundDim) dimensionCalculationList.add(new CMData<>(component, dimension));
    }

    public DimensionStore getScaledSize(JComponent component) {
        for (CMData<DimensionStore, ?> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                return dimData.getStore();
            }
        }

        return null;
    }

    public void setLocation(JComponent component, PointStore location) {
        component.setLocation(location.calculate(cScene.getWidth(), cScene.getHeight()));

        boolean foundPoint = false;
        for (CMData<PointStore, ?> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                foundPoint = true;
                pointData.setStore(location);
                break;
            }
        }

        if (!foundPoint) pointCalculationList.add(new CMData<>(component, location));
    }

    public PointStore getScaledLocation(JComponent component) {
        for (CMData<PointStore, ?> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                return pointData.getStore();
            }
        }

        return null;
    }

    public void setIcon(JComponent component, CustomImageIcon icon, DimensionStore size) {
        try {
            component.getClass().getMethod("setIcon", Icon.class).invoke(component, icon.scaleToFill(component.getSize()));
        } catch (Exception ignored) {}

        boolean foundIcon = false;
        for (CMData<DimensionStore, CustomImageIcon> iconData : iconCalculationList) {
            if (iconData.is(component)) {
                foundIcon = true;
                iconData.setStore(size);
                iconData.setOtherData(icon);
                break;
            }
        }

        if (!foundIcon) iconCalculationList.add(new CMData<>(component, size, icon));
    }

    public void setIcon(JComponent component, CustomImageIcon icon) {
        DimensionStore scaledIconSize = getScaledIconSize(component);
        if(scaledIconSize == null) return;

        setIcon(component, icon, scaledIconSize);
    }

    public void setIcon(JComponent component, DimensionStore size) {
        CustomImageIcon icon = getIcon(component);
        if(icon == null) return;

        setIcon(component, icon, size);
    }

    public DimensionStore getScaledIconSize(JComponent component) {
        for (CMData<DimensionStore, ?> iconData : iconCalculationList) {
            if (iconData.is(component)) {
                return iconData.getStore();
            }
        }

        return null;
    }

    public CustomImageIcon getIcon(JComponent component) {
        for (CMData<DimensionStore, CustomImageIcon> iconData : iconCalculationList) {
            if (iconData.is(component)) {
                return iconData.getOtherData();
            }
        }

        return null;
    }

    public void recalculate(JComponent component) {
        int width = cScene.getWidth();
        int height = cScene.getHeight();

        for (CMData<PointStore, ?> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                pointData.getComponent().setLocation(pointData.getStore().calculate(width, height));
                break;
            }
        }

        for (CMData<DimensionStore, ?> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                dimData.getComponent().setSize(dimData.getStore().calculate(width, height));
                break;
            }
        }

        for (CMData<DimensionStore, CustomImageIcon> iconData : iconCalculationList) {
            if (iconData.is(component)) {
                try {
                    component.getClass().getMethod("setIcon", Icon.class)
                            .invoke(component, iconData.getOtherData().resize(
                                    iconData.getStore().calculate(width, height)
                            ));
                } catch (Exception ignored) {}
                break;
            }
        }
    }

    public void recalculate() {
        int width = cScene.getWidth();
        int height = cScene.getHeight();

        pointCalculationList.forEach(pointData -> pointData.getComponent().setLocation(pointData.getStore().calculate(width, height)));
        dimensionCalculationList.forEach(dimData -> dimData.getComponent().setSize(dimData.getStore().calculate(width, height)));
        iconCalculationList.forEach(iconData -> {
            try {
                iconData.getComponent().getClass().getMethod("setIcon", Icon.class)
                        .invoke(iconData.getComponent(), iconData.getOtherData().resize(
                                iconData.getStore().calculate(width, height)
                        ));
            } catch (Exception ignored) {}
        });
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);

        recalculate();
    }
}
