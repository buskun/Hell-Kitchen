package utility.cm;

import base.Scene;
import components.CustomImageIcon;
import utility.StateManager;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    private Supplier<JFrame> getWindow;
    private Supplier<Scene> getLoadingScene;
    private Consumer<Boolean> setVisible;
    private final ArrayList<JComponent> calculationComponentList = new ArrayList<>();
    private ArrayList<CMData<PointStore, ?>> pointCalculationList = new ArrayList<>();
    private ArrayList<CMData<DimensionStore, ?>> dimensionCalculationList = new ArrayList<>();
    private ArrayList<CMData<DimensionStore, CustomImageIcon>> iconCalculationList = new ArrayList<>();
    private boolean ready = false;
    private ArrayList<Runnable> recalculationQueue = new ArrayList<>();

    public CM(Scene scene) {
        getWindow = scene::getWindow;
        setVisible = scene::setVisible;
        getLoadingScene = () -> scene.getController().getLoadingScene();

        scene.addComponentListener(this);
    }

    public CM(Supplier<JFrame> windowGetter, Consumer<Boolean> visibilitySetter, Supplier<Scene> loadingSceneGetter, Consumer<ComponentListener> addListener) {
        getWindow = windowGetter;
        setVisible = visibilitySetter;
        getLoadingScene = loadingSceneGetter;

        addListener.accept(this);
    }

    public void setBounds(JComponent component, RectangleStore bounds) {
        setLocation(component, bounds.getLocation());
        setSize(component, bounds.getSize());
    }

    public void setSize(JComponent component, DimensionStore dimension) {
        boolean foundDim = false;
        for (CMData<DimensionStore, ?> dimData : dimensionCalculationList) {
            if (dimData.is(component)) {
                foundDim = true;
                dimData.setStore(dimension);
                break;
            }
        }

        if (!calculationComponentList.contains(component)) calculationComponentList.add(component);

        if (!foundDim) {
            dimensionCalculationList.add(new CMData<>(component, dimension));
        }
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
        boolean foundPoint = false;
        for (CMData<PointStore, ?> pointData : pointCalculationList) {
            if (pointData.is(component)) {
                foundPoint = true;
                pointData.setStore(location);
                break;
            }
        }

        if (!calculationComponentList.contains(component)) calculationComponentList.add(component);

        if (!foundPoint) {
            pointCalculationList.add(new CMData<>(component, location));
        }
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
        if (scaledIconSize == null) return;

        setIcon(component, icon, scaledIconSize);
    }

    public void setIcon(JComponent component, DimensionStore size) {
        CustomImageIcon icon = getIcon(component);
        if (icon == null) return;

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

    public void setHoverIcon(JComponent component, CustomImageIcon normalIcon, CustomImageIcon hoverIcon) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                synchronized (component) {
                    setIcon(component, hoverIcon);
                    recalculate(component);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                synchronized (component) {
                    setIcon(component, normalIcon);
                    recalculate(component);
                }
            }
        });
    }

    public void setActiveIcon(JComponent component, CustomImageIcon normalIcon, CustomImageIcon activeIcon) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                synchronized (component) {
                    setIcon(component, activeIcon);
                    recalculate(component);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                synchronized (component) {
                    setIcon(component, normalIcon);
                    recalculate(component);
                }
            }
        });
    }

    synchronized public void recalculate(JComponent component) {
        ready = false;
        int width = getWindow.get().getContentPane().getWidth();
        int height = getWindow.get().getContentPane().getHeight();

        Rectangle newComponentBounds = component.getBounds();

        pointCalculationList.stream().filter(pc -> pc.is(component)).findFirst().ifPresent(pc -> newComponentBounds.setLocation(pc.getStore().calculate(width, height)));

        dimensionCalculationList.stream().filter(dc -> dc.is(component)).findFirst().ifPresent(dc -> newComponentBounds.setSize(dc.getStore().calculate(width, height)));

        iconCalculationList.stream().filter(ic -> ic.is(component)).findFirst().ifPresent(ic -> {
            try {
                component.getClass().getMethod("setIcon", Icon.class)
                        .invoke(component, ic.getOtherData().resize(
                                ic.getStore().calculate(width, height)
                        ));
            } catch (Exception ignored) {}
        });

        component.setBounds(newComponentBounds);
        ready = true;
    }

    synchronized public void recalculateLocation(JComponent component) {
        ready = false;
        int width = getWindow.get().getContentPane().getWidth();
        int height = getWindow.get().getContentPane().getHeight();

        Rectangle newComponentBounds = component.getBounds();

        pointCalculationList.stream().filter(pc -> pc.is(component)).findFirst().ifPresent(pc -> newComponentBounds.setLocation(pc.getStore().calculate(width, height)));

        component.setBounds(newComponentBounds);
        ready = true;
    }

    synchronized public void recalculateSize(JComponent component) {
        ready = false;
        int width = getWindow.get().getContentPane().getWidth();
        int height = getWindow.get().getContentPane().getHeight();

        Rectangle newComponentBounds = component.getBounds();

        dimensionCalculationList.stream().filter(dc -> dc.is(component)).findFirst().ifPresent(dc -> newComponentBounds.setSize(dc.getStore().calculate(width, height)));

        component.setBounds(newComponentBounds);
        ready = true;
    }

    synchronized public void recalculateIcon(JComponent component) {
        ready = false;
        int width = getWindow.get().getContentPane().getWidth();
        int height = getWindow.get().getContentPane().getHeight();

        iconCalculationList.stream().filter(ic -> ic.is(component)).findFirst().ifPresent(ic -> {
            try {
                component.getClass().getMethod("setIcon", Icon.class)
                        .invoke(component, ic.getOtherData().resize(
                                ic.getStore().calculate(width, height)
                        ));
            } catch (Exception ignored) {}
        });

        ready = true;
    }

    synchronized public void recalculate() {
        ready = false;
        int width = getWindow.get().getContentPane().getWidth();
        int height = getWindow.get().getContentPane().getHeight();

        ArrayList<Runnable> queue = new ArrayList<>();

        synchronized (calculationComponentList) {
            for (JComponent component : (ArrayList<JComponent>) calculationComponentList.clone()) {
                Rectangle bound = component.getBounds();

                StateManager<Boolean> edited = Utility.useState(false);
                dimensionCalculationList.stream().filter(cm -> cm.getComponent() == component).findFirst().ifPresent(cm -> {
                    Dimension newSize = cm.getStore().calculate(width, height);

                    if (bound.getWidth() != newSize.getWidth() || bound.getHeight() != newSize.getHeight()) {
                        bound.setSize(newSize);
                        edited.set(true);
                    }
                });

                pointCalculationList.stream().filter(cm -> cm.getComponent() == component).findFirst().ifPresent(cm -> {
                    Point newLocation = cm.getStore().calculate(width, height);

                    if (bound.getY() != newLocation.getX() || bound.getX() != newLocation.getY()) {
                        bound.setLocation(newLocation);
                        edited.set(true);
                    }
                });

                if (edited.get()) queue.add(() -> component.setBounds(bound));

                iconCalculationList.stream().filter(cm -> cm.getComponent() == component).findFirst().ifPresent(cm -> {
                    Dimension newSize = cm.getStore().calculate(width, height);

                    try {
                        Icon icon = (Icon) cm.getComponent().getClass().getMethod("getIcon").invoke(cm.getComponent());
                        if (icon == null || icon.getIconHeight() != newSize.getHeight() || icon.getIconWidth() != newSize.getWidth()) {
                            queue.add(() -> {
                                try {
                                    cm.getComponent().getClass().getMethod("setIcon", Icon.class)
                                            .invoke(cm.getComponent(), cm.getOtherData().resize(newSize));
                                } catch (Exception ignored) {}
                            });
                        }
                    } catch (Exception ignored) { }
                });
            }
        }

        setVisible.accept(false);
        queue.forEach(Runnable::run);
        setVisible.accept(true);

        ready = true;
    }

    public boolean isReady() {
        return ready;
    }

    synchronized public void remove(JComponent component) {
        calculationComponentList.remove(component);
        pointCalculationList.removeIf(cm -> cm.getComponent() == component);
        dimensionCalculationList.removeIf(cm -> cm.getComponent() == component);
        iconCalculationList.removeIf(cm -> cm.getComponent() == component);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);

        if (!recalculationQueue.isEmpty()) {
            recalculationQueue.forEach(Runnable::run);
            recalculationQueue = new ArrayList<>();
        }

        recalculationQueue.add(Utility.setTimeout(() -> {
            Scene loadingScene = getLoadingScene.get();
            setVisible.accept(false);
            if (loadingScene != null) loadingScene.start();

            recalculate();

            if (loadingScene != null) loadingScene.stop();
            setVisible.accept(true);
        }, 100));
    }
}
