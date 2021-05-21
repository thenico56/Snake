package snake.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import snake.data.Data;
import snake.main.Main;
import snake.score.Score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Credits extends Window {
    public static final String BACKGROUND = "assets/images/map/map.png";
    private final ListView<String> listView = new ListView<>();
    private Image background;
    private int backGroundY = -260;
    private boolean addElem;
    @Override
    public void draw() {
        activeKeys.clear();
        checkScore();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTimer) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, WIDTH, HEIGHT);
                gc.drawImage(background, 0, backGroundY);
                if (backGroundY == 0){
                    showCreditMessage();
                } else backGroundY +=1;
            }
        }.start();
    }

    public Credits() {
        super();
        try {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND)));
        } catch (Exception e ) {
            e.printStackTrace();
        }
        addElem = true;
    }

    private void showCreditMessage() {
        Font myFont = Font.font("Arial", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.YELLOW);
        gc.fillText("Credit scene", 275, 200);

        myFont = Font.font("Arial", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.RED);
        gc.fillText("Press spacebar to go back to welcome scene", 275, 300);

        if (addElem) {
            addElem = false;
            addElements();
        }
    }

    private void addElements() {
        Button restart = new Button();

        //Create Button play
        restart.setMaxSize(100, 10);
        restart.setTranslateX(80);
        restart.setTranslateY(0);
        restart.setText("RESTART");
        restart.setOnMouseClicked(mouseEvent -> {
            try {
                Main.scenes[2] = new Credits();
                Main.scenes[1] = new Game();
                Main.setScene(Main.WELCOME_SCENE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Create ListView
        listView.setMaxSize(300, 245);
        listView.setTranslateX(360);
        listView.setTranslateY(-230);
        listView.setStyle("-fx-background-color: transparent");

        listView.getItems().clear();
        Score.getScores().forEach(s -> listView.getItems().add("Name: " + s.getName() + ", Score: " + s.getPoints() + ", Date: " + s.getDate()));

        root.getChildren().addAll(restart, listView);
    }

    private void checkScore() {
        ArrayList<Score> scores = Score.getScores();
        Date d = new Date();
        DateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
        String date = formatDate.format(d);
        int min = 0;

        for (int i=0; i<scores.size(); i++) {
            if (i == 0) min = Integer.parseInt(scores.get(i).getPoints());
            if (Integer.parseInt(scores.get(i).getPoints()) < min)
                min = Integer.parseInt(scores.get(i).getPoints());
        }
        if (points > min || scores.size() < 10) scores.add(new Score("Anonymous", String.valueOf(points), date));

        Data.saveStatus(scores);
        listView.getItems().clear();
        Score.getScores().forEach(s -> listView.getItems().add("Name: " + s.getName() + ", Score: " + s.getPoints() + ", Date: " + s.getDate()));
    }
}
