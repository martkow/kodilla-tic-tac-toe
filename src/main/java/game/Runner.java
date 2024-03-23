package game;

import message.UserMessage;
import player.Player;
import ranking.RankingHistory;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private int roundCounter = 1;
    private List<Player> players = new ArrayList<>();
    private RankingHistory rankingHistory = new RankingHistory();

    public void start() {
        UserMessage.printWelcomeMessage();
        rankingHistory.readRanking();

        Game game = new Game();
        game.setBoardSize();
        game.setDifficultyLevel();
        players = game.setPlayers();

        while (true) {
            ++roundCounter;
            if (!game.isGamePossibleToWin() && !game.isGameWon()) {
                game.finishGameWithDraw();
                rankingHistory.savePlayerToRanking(players, null);
                break;
            } else if (game.isGamePossibleToWin() && !game.isGameWon()) {
                Player player = players.get(roundCounter % 2);
                game.selectMove(player);
            } else {
                Player winner = players.get((roundCounter -1) % 2);
                game.finishGameWithWinner(winner);
                rankingHistory.savePlayerToRanking(players, winner);
                break;
            }
        }
    }
}
