package base;

import java.util.HashMap;

abstract public class Controller {
    private Scene loadingScene = null;
    private HashMap<String, Scene> sceneList = new HashMap<>();
    private Scene currentScene;
    private boolean run = false;
    private int gameTick;

    public final void setLoadingScene(Scene newLoadingScene) {
        loadingScene = newLoadingScene;
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
                while (!currentScene.isReady()) Thread.currentThread().interrupt();
                currentScene.tick();

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

    public final void addScene(String name, Scene scene) { sceneList.put(name, scene); }

    public final void removeScene(String name) { sceneList.remove(name); }

    public final void changeScene(String name) {
        if (sceneList.get(name) == null) return;
        if (sceneList.get(name) == currentScene) return;

        if (currentScene != null) currentScene.stop();
        currentScene = sceneList.get(name);
        currentScene.start();
    }
}
