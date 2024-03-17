package game;

import message.UserMessage;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private int roundCounter = 1;
    private List<Player> players = new ArrayList<>();
    public void start() {
        UserMessage.printWelcomeMessage();

        Game game = new Game();
        game.setBoardSize();
        players = game.setPlayers();

        while (true) {
            ++roundCounter;
            if (!game.isGamePossibleToWin()) {
                game.finishGameWithDraw();
                break;
            } else if (!game.isGameWon()) {
                Player player = players.get(roundCounter % 2);
                game.selectMove(player);
            } else {
                Player winner = players.get((roundCounter -1) % 2);
                game.finishGameWithWinner(winner);
                break;
            }
        }
    }
}
