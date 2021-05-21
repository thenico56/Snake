package snake.data;

import snake.score.Score;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Data {
    //Variables dir and path to storage file
    private static final String filePath = "files/score.txt";
    private static final String dirPath = "files";

    //In this function we read the lines in the file and we add new scores for each line in the array and
    //and when we finish return the array
    public static ArrayList<Score> loadStatus() {
        ArrayList<Score> scores = new ArrayList<>();
        String line;

        //Try to read the file and catch exceptions
        try (BufferedReader inputFile = new BufferedReader(new FileReader(filePath))) {
            while ((line = inputFile.readLine()) != null) {
                String[] data = line.split(";");
                scores.add( new Score(data[0], data[1], data[2]));
            }

            //We will only take 10 scores and delete the last ones
            for (int i = 9; i<scores.size(); i++) scores.remove(i);
            return scores;
        } catch (IOException e) {
            System.err.println("Error, file not found. I create the file");
            createFiles();
        }
        return scores;
    }

    //We take the score array and save with toString method in the file ordered by points
    public static void saveStatus(ArrayList<Score> scores) {
        scores.sort((c1, c2) -> Integer.compare(Integer.parseInt(c2.getPoints()), Integer.parseInt(c1.getPoints())));
        //Save in the file the scores
        try (PrintWriter file = new PrintWriter("files/score.txt")) {
            for (Score s : scores) file.println(s.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //In this function, the program checks the directory and the file. If they do not exist, they will be created
    private static void createFiles() {
        File dir = new File(dirPath);
        File file = new File(filePath);
        if (!dir.isDirectory()) {
            try {
                Files.createDirectory(Path.of(dirPath));
                if (!file.isFile()) Files.createFile((file).toPath());
            } catch (IOException e) {
                System.err.println("Error in the created file");
            }
        }
    }
}
