package base;

public enum CursorHotSpotPosition {
    MIDDLE(0.5, 0.5),
    TOP_CENTER(0.5, 0),
    TOP_LEFT(0, 0),
    TOP_RIGHT(1, 0),
    BOTTOM_CENTER(0.5, 1),
    BOTTOM_LEFT(0, 1),
    BOTTOM_RIGHT(1, 1),
    CENTER_LEFT(0, 0.5),
    CENTER_RIGHT(1, 0.5);

    private final double xp;
    private final double yp;

    CursorHotSpotPosition(double x, double y) {
        xp = x;
        yp = y;
    }

    double getX() { return xp; }

    double getY() { return yp; }
}
