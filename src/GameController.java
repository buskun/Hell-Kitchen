import base.Controller;
import base.Scene;
import base.Window;
import game.scenes.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameController extends Controller {
    private static final Dimension mainWindowSize = new Dimension(1600, 900);
    private static final Point mainWindowPosition = new Point(50, 50);
    private static final String mainWindowTitle = "Hell Kitchen";
    private static final int gameTickPerSec = 10;

    public GameController() { super(gameTickPerSec); }

    public void init() {
        Window mainWindow = new Window(mainWindowTitle, mainWindowSize, mainWindowPosition);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        setWindow(mainWindow);

        Scene loadingScene = new LoadingScene(mainWindow, this);
        loadingScene.init();
        setLoadingScene(loadingScene);

        loadScene();
    }
}
