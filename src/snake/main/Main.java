/**
 * The Snake is a javafx game where we can move a worm that can move and eat random
 * apples that appear. You lose if it touches the map limits. When the
 * worm eats an apple, it gives score.
 * @author Andrei Nicolae Depner
 * @version 1.3
 * @see https://iessanvicente.com
 * @since 21/05/2021
 */
package snake.main;

import javafx.application.Application;
import javafx.stage.Stage;
import snake.scenes.*;

import java.io.IOException;

public class Main extends Application {
    //Create variable for change the scenes
    public static final int SCENES = 3;
    public static final int WELCOME_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int CREDITS_SCENE = 2;
    public static final Window[] scenes = new Window[SCENES];

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        //Initialize array and set the first scene
        Main.stage = stage;
        scenes[0] = new Welcome();
        scenes[1] = new Game();
        scenes[2] = new Credits();

        stage.setTitle("Hell Worm");
        setScene(WELCOME_SCENE);
        stage.show();
    }

    //In this function we change the scene
    public static void setScene(int numScene) throws IOException {
        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
