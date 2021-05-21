package snake.scenes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import snake.main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Welcome extends Window {
    public static final String BACKGROUND = "assets/images/map/map.png";
    public static final String BACKGROUND_SOUND = "assets/sounds/bgWelcome.mp3";
    private final Image background;

    @Override
    public void draw() {
        sound = new Media(new File(BACKGROUND_SOUND).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        activeKeys.clear();
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(background, 0, 0);
        showCreditMessage();
    }

    public Welcome() throws IOException {
        super();
        background = new Image(Files.newInputStream(Paths.get(BACKGROUND)));
        addElements();
    }

    private void showCreditMessage() {
        Font myFont = Font.font("Arial", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.RED);
        gc.fillText("Welcome scene", WIDTH/2-120, 200);
        gc.fillText("Name: ", WIDTH/2-100, 320);
    }

    private void addElements() {
        Button play = new Button();
        txtName.setMaxSize(200, 10);
        txtName.setText("Anonymous");

        play.setMaxSize(60, 10);
        play.setTranslateX(135);
        play.setTranslateY(0);
        play.setText("PLAY");
        play.setOnMouseClicked(mouseEvent -> {
            try {
                mediaPlayer.stop();
                Main.scenes[1] = new Game();
                Main.setScene(Main.GAME_SCENE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().addAll(txtName, play);
    }
}
