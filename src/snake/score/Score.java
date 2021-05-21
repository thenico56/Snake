package snake.score;

import snake.data.Data;

import java.util.ArrayList;

public class Score implements Comparable<Score> {
    private static ArrayList<Score> scores = new ArrayList<>();
    private String points;
    private String name;
    private String date;

    public static ArrayList<Score> getScores() {
        return scores;
    }

    public Score(String name, String points, String date) {
        this.points = points;
        this.name = name;
        this.date = date;
    }

    public Score() {
        scores = Data.loadStatus();
    }

    public static void initialize() {
        new Score();
    }

    @Override
    public String toString() {
        return name + ";" + points + ";" + date;
    }

    @Override
    public int compareTo(Score c) {
        return this.points.compareTo(c.points);
    }

    public String getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
