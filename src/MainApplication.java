import game.scenes.*;
import utility.Utility;

public class MainApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.addScene("menu", MenuScene.class);
        gameController.addScene("SeletionLv", SeletionLv.class);
        gameController.addScene("gameScene", GameScene.class);
        gameController.addScene("Result", Result.class);
        gameController.addScene("settingScene", SettingScene.class);

        Utility.addCustomFont("Dimbo", "resources/font/Dimbo.ttf");
        Utility.setDefaultFont(Utility.getFont("Dimbo").deriveFont(20f));

        gameController.changeState("level", 1);
        gameController.changeState("difficulty", 1);
        gameController.changeState("volume", 50);
        gameController.changeState("song", 0);
        gameController.changeState("nick_name", "Bird");

        gameController.init();
        gameController.changeScene("menu");
        gameController.start();
    }
}
