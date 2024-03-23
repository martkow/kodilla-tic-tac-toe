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
    private static final Path path = Paths.get("/Users/markow/IdeaProjects/Module9/kodilla-tic-tac-toe/ranking.txt");

    public void savePlayerToRanking(List<Player> players, Player winner) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Player p : players) {
                try {
                    writer.write(p.toString() + (p.equals(winner) ? " 1" : " 0") + "\n");
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
            System.out.println("Game ranking:");
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            ExceptionMessage.printRankingNotAvailableMessage();
        }
    }
}