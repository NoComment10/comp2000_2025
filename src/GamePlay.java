public class GamePlay {
    Stage stage = new Stage();

    public GamePlay(Stage stage) {
        this.stage = stage;
    }

    public void update() {
        if(stage.gameOver()) {
            System.out.println("Game Over!");
        }
    }   
}