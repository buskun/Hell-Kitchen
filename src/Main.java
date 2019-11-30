public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.addSceneMap("first", "FirstSceneTest");

        gameController.init();
    }
}
