import game.scenes.FirstSceneTest;

public class MainApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.addScene("first", FirstSceneTest.class);

        gameController.init();
        gameController.changeScene("first");
        gameController.start();
    }
}
