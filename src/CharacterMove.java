public class CharacterMove {

    public void dogMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Dog) {
                actor.move(stage.selectedCell);
            }
        }
    }

    public void catMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Cat) {
                actor.move(stage.selectedCell);
            }
        }
    }

    public void birdMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Bird) {
                actor.move(stage.selectedCell);
            }
        }
    }
    
}