import base.Controller;
import base.Scene;
import base.WindowFrame;
import game.scenes.*;

import javax.swing.*;
import java.awt.*;

public class GameController extends Controller {
    private static final Dimension mainWindowSize = new Dimension(1200, 675);
    private static final Point mainWindowPosition = new Point(50, 50);
    private static final String mainWindowTitle = "Hell Kitchen";
    private static final int gameTickPerSec = 10;

    public GameController() { super(gameTickPerSec); }

    public void init() {
        WindowFrame mainWindow = new WindowFrame(mainWindowTitle, mainWindowSize, mainWindowPosition);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        setWindow(mainWindow);

        Scene loadingScene = new LoadingScene(mainWindow, this);
        loadingScene.init();
        setLoadingScene(loadingScene);

        loadScene();
    }
}
