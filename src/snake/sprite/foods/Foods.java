package snake.sprite.foods;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import snake.sprite.Sprite;
import snake.sprite.foods.danger.Danger;
import snake.sprite.foods.food.*;
import snake.sprite.life.Life;
import snake.sprite.worm.Worm;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Foods {
    public static final String EAT_SOUND = "assets/sounds/eat.mp3";
    private final ArrayList<Sprite> foods = new ArrayList<>();
    private int afterCountTrash = 1;
    private int count = 3;
    private final int maxW = 1000;
    private final int minW = 50;
    private final int maxH = 600;
    private final int minH = 200;

    public Foods() {
        foods.add(new Food1(random(minW, maxW), random(minH, maxH)));
        foods.add(new Food2(random(minW, maxW), random(minH, maxH)));
        foods.add(new Food3(random(minW, maxW), random(minH, maxH)));
        foods.add(new Danger(random(minW, maxW), random(minH, maxH)));
    }

    public int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public void showFood(GraphicsContext gc) {
        for (Sprite s: foods) s.drawObj(gc);
    }

    public boolean blockCollision(Worm w) {
        for (int i = 0; i< foods.size(); i++) {
            if (foods.get(i).collision(w)) {
                if (foods.get(i) instanceof Danger) Life.putOffLife();
                foods.remove(foods.get(i));
                addFood();
                Media sound = new Media(new File(EAT_SOUND).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                return true;
            }
        }
        return false;
    }

    private void addFood() {
        int countTrash = 0;
        for (Sprite s:foods) if (s instanceof Danger) countTrash++;
        if (afterCountTrash < countTrash) {
            afterCountTrash = countTrash;
            count++;
        }
        for (int i = count; i >= foods.size(); i--) {
            int addFood = random(1, 4);
            if (addFood == 1) foods.add(new Food1(random(minW, maxW), random(minH, maxH)));
            if (addFood == 2) foods.add(new Food2(random(minW, maxW), random(minH, maxH)));
            if (addFood == 3) foods.add(new Food3(random(minW, maxW), random(minH, maxH)));
            if (addFood == 4) foods.add(new Danger(random(minW, maxW), random(minH, maxH)));
        }
    }
}
