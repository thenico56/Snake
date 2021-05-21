package snake.sprite.foods.food;

import javafx.scene.image.Image;
import snake.sprite.Sprite;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Food3 extends Sprite {
    public static final int TRASH_WIDTH = 40;
    public static final int TRASH_HEIGHT = 40;
    private static final String IMAGE_PATH = "assets/images/food/food3.png";

    public Food3(int x, int y) {
        super(TRASH_WIDTH, TRASH_HEIGHT, x, y);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
