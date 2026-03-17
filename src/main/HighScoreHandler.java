package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreHandler {
    private static final String FILE_NAME = "scores.txt";

    public void save(int score) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(String.valueOf(score));
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> loadScores() {
        List<Integer> scores = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return scores;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores;
    }

    public int getHighScore() {
        List<Integer> scores = loadScores();

        return scores.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }
}
