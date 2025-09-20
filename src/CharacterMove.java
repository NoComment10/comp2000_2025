public class CharacterMove {

    public static void dogMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Dog) {
                actor.move(stage.selectedCell);
            }
        }
    }

    public static void catMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Cat) {
                actor.move(stage.selectedCell);
            }
        }
    }

    public static void birdMove(Stage stage) {
        for (Actor actor : stage.actors) {
            if (actor instanceof Bird) {
                actor.move(stage.selectedCell);
            }
        }
    }
}