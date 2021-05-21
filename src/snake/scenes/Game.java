package snake.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import snake.main.Main;
import snake.score.Score;
import snake.sprite.foods.Foods;
import snake.sprite.life.Life;
import snake.sprite.worm.Worm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game extends Window{
    //hell worm
    public static final String BACKGROUND = "assets/images/map/map.png";
    public static final String BACKGROUND_SOUND = "assets/sounds/bgGame.mp3";
    private final Worm worm = new Worm(400, 444);
    private final Foods foods = new Foods();
    public Label score = new Label();

    private Image background;
    private int move = 3, backGrouY = 0;

    @Override
    public void draw() {
        sound = new Media(new File(BACKGROUND_SOUND).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        worm.moveTo(400, 444);
        backGrouY = 0;
        activeKeys.clear();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTimer) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, WIDTH, HEIGHT);
                gc.drawImage(background, 0, backGrouY);
                if (backGrouY == -260){
                    //KEYS
                    try {
                        if (activeKeys.contains(KeyCode.LEFT)) move = 0;
                        else if(activeKeys.contains(KeyCode.RIGHT)) move = 1;
                        else if(activeKeys.contains(KeyCode.DOWN)) move = 2;
                        else if(activeKeys.contains(KeyCode.UP)) move = 3;
                        Life.drawLifes(gc);
                        foods.showFood(gc);
                        worm.animateMove(move, gc);
                        if (worm.collisionMap() || Life.getLife() == 0) {
                            this.stop();
                            mediaPlayer.stop();
                            Main.setScene(Main.CREDITS_SCENE);
                        }
                        if (foods.blockCollision(worm)) points+=10;
                        score.setText(String.valueOf(points));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } else backGrouY -=1;
            }
        }.start();
    }

    public void addElements() {
        Font myFont = Font.font("Helvetic", FontWeight.BLACK, 20);
        score.setFont(myFont);
        score.setStyle("-fx-text-fill: white");
        score.setTranslateX(-370);
        score.setTranslateY(-290);
        score.setRotate(-5.4);
        score.setMaxSize(100, 50);
        root.getChildren().addAll(score);
    }

    public Game() {
        super();
        try {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND)));
        } catch (Exception e ) {
            e.printStackTrace();
        }
        Life.initialize();
        Score.initialize();
        addElements();
        points = 0;
    }
}
