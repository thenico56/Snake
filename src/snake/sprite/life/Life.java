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

    public Life(int x, int y) {
        super(LIFE_WIDTH, LIFE_HEIGHT, x, y);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Life() {
        super();
        lifes.add(new Life(890, 20));
        lifes.add(new Life(940, 20));
        lifes.add(new Life(990, 20));
    }

    public static void drawLifes(GraphicsContext gc) { lifes.forEach(l -> l.drawObj(gc)); }

    public static void putOffLife() {
        lifes.remove(0);
    }

    public static int getLife() {
        return lifes.size();
    }

    public static void initialize() { new Life();}
}
