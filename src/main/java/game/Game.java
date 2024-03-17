package game;

import board.Board;
import board.BoardSizeNotAvailableException;
import board.MoveNotAvailableException;
import message.ExceptionMessage;
import message.UserMessage;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Board board;

    public Board getBoard() {
        return board;
    }

    protected void setBoard(int i) throws BoardSizeNotAvailableException {
        board = new Board(i);
    }

    public void setBoardSize() {
        while (true) {
            try {
                UserMessage.printSetBoardSizeMessage();
                board = new Board(Integer.parseInt(scanner.nextLine()));
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

    public List<Player> setPlayers() {
        List<Player> players = new ArrayList<>();

        UserMessage.printSetPlayersMessage();
        for (int i = 1; i < 2; i++) {
            UserMessage.printEnterPlayerMessage(i);
            players.add(new Player(scanner.nextLine(), false));
        }

        players.add(new Player("System", true));

        UserMessage.printPlayersMessage(players);
        return players;
    }

    public void selectMove(Player player) {
        UserMessage.printSelectMoveMessage(player);
        if (!player.isSystemPlayer()) {
            while (true) {
                try {
                    String userMove = getUserMove();
                    int[] move = processUserMove(userMove);

                    board.setValueForField(move[0], move[1], move[2]);
                    board.printBoard();
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
            setSystemMove();
            board.printBoard();
        }
    }

    private String getUserMove() {
        UserMessage.printInstructionForUSerMoveMessage(board.getBoardSize() == 3 ? 9 : 100);
        return scanner.nextLine();
    }

    private int[] processUserMove(String userMove) {
        int fieldNumber = Integer.parseInt(userMove.substring(0, userMove.indexOf(" ")));
        int[] element = mapNumberToBoardElement(fieldNumber);
        int type = MoveType.valueOf(userMove.substring(userMove.indexOf(" ")).trim().toUpperCase()).equals(MoveType.CIRCLE) ? 0 : 1;

        return new int[]{element[0], element[1], type};
    }

    private int[] mapNumberToBoardElement(int i) throws IllegalArgumentException {
        int[] element = new int[2];

        if (i % board.getBoardSize() == 0) {
            element[0] = (i / board.getBoardSize()) - 1;
            element[1] = getBoard().getBoardSize() - 1;
        } else {
            element[0] = i / board.getBoardSize();
            element[1] = (i % board.getBoardSize()) - 1;
        }

        return element;
    }

    private void setSystemMove() {
        Random random = new Random();
        while (true) {
            int i = random.nextInt(board.getBoardSize());
            int j = random.nextInt(board.getBoardSize());
            int value = random.nextInt(2);

            if (board.isFieldFree(i, j)) {
                board.setValueForFreeField(i, j , value);
                break;
            }
        }
    }

    public boolean isGameWon() {
        return board.isLine();
    }

    public boolean isGamePossibleToWin() {
        return board.hasAnyMatch(-1);
    }

    public void finishGameWithWinner(Player player) {
        UserMessage.printEndWinnerMessage(player);
        board.printBoard();
    }

    public void finishGameWithDraw() {
        UserMessage.printEndDrawMessage();
        board.printBoard();
    }
}
