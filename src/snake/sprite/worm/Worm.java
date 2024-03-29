package snake.sprite.worm;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import snake.sprite.Sprite;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Worm extends Sprite {
    public static final int BODY_WIDTH = 112;
    public static final int BODY_HEIGHT = 112;
    private static final String IMAGE_PATH = "assets/images/worm/worm.png";
    private int actualMove = 0;
    private final int[] spriteX = new int[]{0,0,0,0};
    private final int[] spriteY = new int[]{330,110,220,0};

    /**
     * In this constructor we will assign the corresponding image
     * @param x - Position X of the object
     * @param y - Position Y of the object
     */
    public Worm(int x, int y) {
        super(BODY_WIDTH, BODY_HEIGHT, x, y);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Move the worm with the correspondent position image
     * @param movement - Actual move
     * @param gc - Object GraphicsContext to be able to draw the object
     */
    public void animateMove(int movement, GraphicsContext gc) {
        if (actualMove != movement) {
            actualMove = movement;
        }
        else {
            move(movement, gc, spriteX[actualMove], spriteY[actualMove]);
        }
    }
}
