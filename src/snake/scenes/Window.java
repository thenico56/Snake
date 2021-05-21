package snake.scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashSet;
import java.util.Set;
//implemets Initializable
public abstract class Window extends Scene {
    public static final int WIDTH = 1030;
    public static final int HEIGHT = 720;

    protected static int points;
    protected static TextField txtName;
    protected StackPane root;
    protected GraphicsContext gc;
    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;
    protected MediaPlayer mediaPlayer;
    protected Media sound;
    public Window() {
        super(new StackPane(), WIDTH, HEIGHT);

        //Change scene`s root to our own stack pane
        root = new StackPane();
        txtName = new TextField();
        this.setRoot(root);

        //Initialize canvas and graphics context
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        //Initialize set of currently pressed and released keys
        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();

        this.setOnKeyPressed(e -> activeKeys.add(e.getCode()));

        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());
        });
    }


    public abstract void draw();
}
