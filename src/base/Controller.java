package base;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

abstract public class Controller {
    private Scene loadingScene = null;
    private HashMap<String, Scene> sceneList = new HashMap<>();
    private HashMap<String, Class<Scene>> sceneMap = new HashMap<>();
    private Window window;

    private Scene currentScene;

    private boolean run = false;
    private int gameTick;

    public final void setLoadingScene(Scene newLoadingScene) {
        if (loadingScene != null) {
            window.remove(loadingScene);
            window.removeComponentListener(loadingScene);
        }
        loadingScene = newLoadingScene;
        window.add(loadingScene);
    }

    public final Scene getLoadingScene() { return loadingScene; }

    public Controller(int gameTickPerSec) {
        gameTick = gameTickPerSec;
    }

    public abstract void init();

    public final void start() {
        run = true;
        new Thread(() -> {
            while (run) {
                if (currentScene == null) continue;
                if (!currentScene.isReady()) {
                    getLoadingScene().tick();
                } else {
                    currentScene.tick();
                }

                try { Thread.sleep(1000 / gameTick); } catch (InterruptedException ignored) { }
            }
        }).start();
    }

    public final void stop() {
        if (currentScene != null) {
            currentScene.stop();
            currentScene = null;
        }
        run = false;
    }

    public final void setWindow(Window _window) { window = _window; }

    public final Window getWindow() { return window; }

    public final void removeScene(String name) { sceneList.remove(name); }

    public final void changeScene(String name) {
        if (sceneList.get(name) == null) return;
        if (sceneList.get(name) == currentScene) return;

        new Thread(() -> {
            if (currentScene != null) currentScene.stop();
            currentScene = sceneList.get(name);
            currentScene.start();
        }).start();
    }

    public final <SC extends Scene> void addScene(String name, Class<SC> sceneClass) { sceneMap.put(name, (Class<Scene>) sceneClass); }

    public final void loadScene() {
        if (window == null) return;
        for (String name : sceneMap.keySet()) {
            try {
                sceneList.put(name, sceneMap.get(name).getDeclaredConstructor(Window.class, Controller.class).newInstance(window, this));
            } catch (IllegalAccessException exception) {
                System.err.println("Cannot access class: " + sceneMap.get(name));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException exception) {
                System.err.println("Cannot init class: " + sceneMap.get(name));
            }
        }
    }

    public HashMap<String, Scene> getSceneList() {
        return sceneList;
    }
}
