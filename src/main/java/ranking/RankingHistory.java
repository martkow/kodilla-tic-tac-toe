package ranking;

import player.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RankingHistory {
    private static final Path path = Paths.get("src/main/resources/ranking.txt");
    private static final String NEWLINE = "\n";
    private static final String SEPARATOR = ",";
    private static final String ONE = "1";
    private static final String ZERO = "0";

    public void savePlayersToRanking(List<Player> players, Player winner) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
        for (Player p : players) {
            writer.write(p.toString() + SEPARATOR + (p.equals(winner) ? ONE : ZERO) + NEWLINE);
        }
    }

    public List<String> getRanking() throws IOException {
        List<String> rankingList = new ArrayList<>();

        List<String> lines = Files.readAllLines(path);
        rankingList.addAll(lines);
        return rankingList;
    }
}
