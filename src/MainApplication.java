import game.scenes.FirstSceneTest;
import game.scenes.MenuScene;
import game.scenes.SeletionLv;
import game.scenes.GameScene;
import game.scenes.Result;
import utility.Utility;

public class MainApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.addScene("first", FirstSceneTest.class);
        gameController.addScene("menu", MenuScene.class);
        gameController.addScene("SeletionLv", SeletionLv.class);
        gameController.addScene("gameScene", GameScene.class);
        gameController.addScene("Result",Result.class);

        Utility.addCustomFont("Dimbo", "resources/font/Dimbo.ttf");
        Utility.setDefaultFont(Utility.getFont("Dimbo").deriveFont(20f));

        gameController.init();
        gameController.changeScene("gameScene");
        gameController.start();
    }
}
