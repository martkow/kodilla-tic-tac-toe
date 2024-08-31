package tic.tac.toe.console;

import board.BoardSizeNotAvailableException;
import board.MoveNotAvailableException;
import game.Game;
import game.DifficultyLevelNotAvailableException;
import game.PlayerNameIsEmptyException;
import game.move.Move;
import game.move.MoveType;
import message.ExceptionMessage;
import message.UserMessage;
import player.Player;
import ranking.RankingHistory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleGameRunner {
    private final static String SYSTEM_PLAYER = "System";
    private final static String SEPARATOR = " ";
    private Scanner scanner = new Scanner(System.in);
    private Game game = new Game();
    private ConsoleGamePrinter consoleGamePrinter = new ConsoleGamePrinter();
    private int roundCounter = 1;
    private RankingHistory rankingHistory = new RankingHistory();

    public void startGame() {
        UserMessage.printWelcomeMessage();
        showRanking();
        setBoardSize();
        setDifficultyLevel();
        setPlayers();
        play();
    }

    public void showRanking() {
        try {
            consoleGamePrinter.printGameRanking(rankingHistory.getRanking());
        } catch (IOException e) {
            ExceptionMessage.printRankingNotAvailableMessage();
        }
    }

    public void setBoardSize() {
        while (true) {
            try {
                UserMessage.printSetBoardSizeMessage();
                game.setBoard(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (Exception e) {
                if (e instanceof BoardSizeNotAvailableException) {
                    System.out.println(e.getMessage());
                } else {
                    ExceptionMessage.printWrongDataMessage();
                }
            }
        }
    }

    public void setDifficultyLevel() {
        while (true) {
            try {
                UserMessage.printSetDifficultyLevelMessage();
                int i = Integer.parseInt(scanner.nextLine());

                game.setDifficultyLevel(i);
                break;
            } catch (Exception e) {
                if (e instanceof DifficultyLevelNotAvailableException) {
                    System.out.println(e.getMessage());
                } else {
                    ExceptionMessage.printWrongDataMessage();
                }
            }
        }
    }

    public void setPlayers() {
        UserMessage.printSetPlayersMessage();
        for (int i = 1; i < 2; i++) {
            setPlayer(i);
        }

        try {
            game.setPlayer(new Player(SYSTEM_PLAYER, true));
        } catch (PlayerNameIsEmptyException pniee) {
            //
        }
        UserMessage.printPlayersMessage(game.getPlayers());
    }

    public void setPlayer(int i) {
        while (true) {
            try {
                UserMessage.printEnterPlayerMessage(i);
                game.setPlayer(new Player(scanner.nextLine(), false));
                break;
            } catch (PlayerNameIsEmptyException pniee) {
                System.out.println(pniee.getMessage());
            }
        }
    }

    public void play() {
        while (true) {
            ++roundCounter;
            if (!game.isGamePossibleToWin() && !game.isGameWon()) {
                consoleGamePrinter.printGameEndingWithDraw();
                consoleGamePrinter.printBoard(game.getBoard());
                savePlayersToRanking(game.getPlayers(), null);
                break;
            } else if (game.isGamePossibleToWin() && !game.isGameWon()) {
                Player player = game.getPlayers().get(roundCounter % 2);
                selectMove(player);
                consoleGamePrinter.printBoard(game.getBoard());
            } else {
                Player winner = game.getPlayers().get((roundCounter - 1) % 2);
                consoleGamePrinter.printGameEndingWithWinner(winner);
                consoleGamePrinter.printBoard(game.getBoard());
               savePlayersToRanking(game.getPlayers(), winner);
                break;
            }
        }
    }

    public void savePlayersToRanking(List<Player> players, Player winner) {
        try {
            rankingHistory.savePlayersToRanking(players, winner);
        } catch (IOException e) {
            //
        }
    }

    public void selectMove(Player player) {
        UserMessage.printSelectMoveMessage(player);
        if (!player.isSystemPlayer()) {
            while (true) {
                try {
                    String userMove = getUserMove();
                    Move move = processUserMove(userMove);

                    game.selectMove(move);
                    break;
                } catch (Exception e) {
                    if (e instanceof MoveNotAvailableException) {
                        System.out.println(e.getMessage());
                    } else {
                        ExceptionMessage.printWrongDataMessage();
                    }
                }
            }
        } else {
            try {
                game.selectMove(null);
            } catch (MoveNotAvailableException e) {
                //
            }
        }
    }

    private String getUserMove() {
        UserMessage.printInstructionForUSerMoveMessage(game.getBoard().getBoardSize() == 3 ? 9 : 100);
        return scanner.nextLine();
    }

    private Move processUserMove(String userMove) {
        int fieldNumber = Integer.parseInt(userMove.substring(0, userMove.indexOf(SEPARATOR)));
        int[] element = game.mapNumberToBoardElement(fieldNumber);

        MoveType moveType = MoveType.valueOf(userMove.substring(userMove.indexOf(SEPARATOR)).trim().toUpperCase());

        return new Move(element[0], element[1], moveType);
    }

}
