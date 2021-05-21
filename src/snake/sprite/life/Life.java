package snake.sprite.life;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import snake.sprite.Sprite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Life extends Sprite {
    public static final int LIFE_WIDTH = 40;
    public static final int LIFE_HEIGHT = 40;
    private static final String IMAGE_PATH = "assets/images/life/heart.png";
    private static final ArrayList<Life> lifes = new ArrayList<>();

    /**
     * In this constructor we will assign the corresponding image
     * @param x - Position X of the object
     * @param y - Position Y of the object
     */
    public Life(int x, int y) {
        super(LIFE_WIDTH, LIFE_HEIGHT, x, y);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * In this constructor we initialize the array lives to 3 lives
     */
    private Life() {
        super();
        lifes.add(new Life(890, 20));
        lifes.add(new Life(940, 20));
        lifes.add(new Life(990, 20));
    }

    /**
     * In this function we draw all the lives
     * @param gc - Object GraphicsContext to be able to draw the object
     */
    public static void drawLives(GraphicsContext gc) { lifes.forEach(l -> l.drawObj(gc)); }

    /**
     * In this function we remove one life
     */
    public static void putOffLife() {
        lifes.remove(0);
    }

    /**
     * @return - Total lives in the array
     */
    public static int getLife() {
        return lifes.size();
    }

    /**
     * We initialize the object life to use the statics methods in the others classes
     */
    public static void initialize() {
        lifes.clear();
        new Life();}
}
