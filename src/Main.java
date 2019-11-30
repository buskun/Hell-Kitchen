public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.addScene("first", "FirstSceneTest");

        gameController.init();
        gameController.changeScene("first");
        gameController.start();
    }
}
