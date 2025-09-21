public class CharacterMove {

    //moves the dog to the selected cell
    public static void dogMove(Stage stage) {
        for (Actor actor : stage.userActor) {
            if (actor instanceof Dog) {
                actor.move(stage.selectedCell);
            }
        }
    }

    //moves the cat to the selected cell
    public static void catMove(Stage stage) {
        for (Actor actor : stage.userActor) {
            if (actor instanceof Cat) {
                actor.move(stage.selectedCell);
            }
        }
    }

    //moves the bird to the next cell in its patrol route
    public static void birdMove(Stage stage) {
        for (Actor actor : stage.enemyActor) {
            if (actor instanceof Bird) {
                actor.move(stage.selectedCell);
            }
        }
    }  
}