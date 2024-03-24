package ranking;

import message.ExceptionMessage;
import player.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class RankingHistory {
    private static final Path path = Paths.get("src/main/resources/ranking.txt");
    private static final String TITLE = "Game ranking:";
    private static final String NEWLINE = "\n";
    private static final String SEPARATOR = ",";
    private static final String ONE = "1";
    private static final String ZERO = "0";

    public void savePlayerToRanking(List<Player> players, Player winner) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Player p : players) {
                try {
                    writer.write(p.toString() + SEPARATOR +  (p.equals(winner) ? ONE : ZERO) + NEWLINE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            ExceptionMessage.printRankingNotAvailableMessage();
        }
    }

    public void readRanking() {
        try {
            System.out.println(TITLE);
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            ExceptionMessage.printRankingNotAvailableMessage();
        }
    }
}