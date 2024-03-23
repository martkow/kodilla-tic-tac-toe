package game;

import board.Board;
import board.BoardSizeNotAvailableException;
import board.MoveNotAvailableException;
import game.exception.DifficultyLevelNotAvailableException;
import game.move.Move;
import game.move.MoveType;
import game.strategy.SmartMovesSeeker;
import message.ExceptionMessage;
import message.UserMessage;
import player.Player;
import ranking.RankingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private Move lastPlayerMove;
    private int level;
    private final String SYSTEM_PLAYER = "System";

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

    public void setDifficultyLevel() {
        while (true) {
            try {
                UserMessage.printSetDifficultyLevelMessage();
                int i = Integer.parseInt(scanner.nextLine());

                if (i != 0 && i != 1) {
                    throw new DifficultyLevelNotAvailableException(ExceptionMessage.getDifficultyLevelNotAvailableMessage());
                } else {
                    level = i;
                }
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

    public List<Player> setPlayers() {
        List<Player> players = new ArrayList<>();

        UserMessage.printSetPlayersMessage();
        for (int i = 1; i < 2; i++) {
            UserMessage.printEnterPlayerMessage(i);
            players.add(new Player(scanner.nextLine(), false));
        }

        players.add(new Player(SYSTEM_PLAYER, true));

        UserMessage.printPlayersMessage(players);
        return players;
    }

    public void selectMove(Player player) {
        UserMessage.printSelectMoveMessage(player);
        if (!player.isSystemPlayer()) {
            while (true) {
                try {
                    String userMove = getUserMove();
                    Move move = processUserMove(userMove);

                    lastPlayerMove = move;
                    setMove(move);
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

    private Move processUserMove(String userMove) {
        int fieldNumber = Integer.parseInt(userMove.substring(0, userMove.indexOf(" ")));
        int[] element = mapNumberToBoardElement(fieldNumber);

        MoveType moveType = MoveType.valueOf(userMove.substring(userMove.indexOf(" ")).trim().toUpperCase());

        return new Move(element[0], element[1], moveType);
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
        if (level == 0) {
            setFreeMove(SmartMovesSeeker.seekForAnyFreeSmartMove(board, lastPlayerMove));
        } else {
            setFreeMove(SmartMovesSeeker.seekForAdjacentFreeSmartMove(board, lastPlayerMove));
        }
    }

    private void setFreeMove(Move move) {
        board.setValueForFreeField(move.getRow(), move.getColumn(), move.getMoveType().equals(MoveType.CIRCLE) ? -1 : 1);
    }

    private void setMove(Move move) throws MoveNotAvailableException {
        board.setValueForField(move.getRow(), move.getColumn(), move.getMoveType().equals(MoveType.CIRCLE) ? -1 : 1);
    }

    public boolean isGameWon() {
        return board.isLine();
    }

    public boolean isGamePossibleToWin() {
        return board.hasAnyMatch(0);
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
