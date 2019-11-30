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

    private ArrayList<Scene> sceneList = new ArrayList<>();
    private HashMap<String, String> sceneMap = new HashMap<>();

    public GameController() { super(gameTickPerSec); }

    public void init() {
        Window mainWindow = new Window(mainWindowTitle, mainWindowSize, mainWindowPosition);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        Scene loadingScene = new LoadingScene(mainWindow, this);
        loadingScene.init();
        setLoadingScene(loadingScene);

        for (String name : sceneMap.keySet()) {
            try {
                Scene scene = (Scene) Class.forName("game.scenes." + sceneMap.get(name)).getDeclaredConstructor(Window.class, Controller.class).newInstance(mainWindow, this);
                sceneList.add(scene);
                addScene(name, scene);
            } catch (ClassNotFoundException exception) {
                System.err.println("Cannot find class: " + sceneMap.get(name));
            } catch (IllegalAccessException exception) {
                System.err.println("Cannot access class: " + sceneMap.get(name));
            }  catch (NoSuchMethodException | InvocationTargetException | InstantiationException exception) {
                System.err.println("Cannot init class: " + sceneMap.get(name));
            }
        }

        changeScene("first");
    }

    public GameController addSceneMap(String name, String className) {
        sceneMap.put(name, className);

        return this;
    }
}
