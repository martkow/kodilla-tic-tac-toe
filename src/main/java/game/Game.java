package game;

import board.Board;
import board.BoardSizeNotAvailableException;
import board.MoveNotAvailableException;
import game.move.Move;
import game.move.MoveType;
import game.strategy.SmartMovesSeeker;
import lombok.Getter;
import message.ExceptionMessage;
import player.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
    private Board board;
    private Move lastPlayerMove;
    private List<Player> players = new ArrayList<>();
    private int level;

    public void setBoard(int i) throws BoardSizeNotAvailableException {
        board = new Board(i);
    }

    public void setDifficultyLevel(int i) throws DifficultyLevelNotAvailableException {
        if (i != 0 && i != 1) {
            throw new DifficultyLevelNotAvailableException(ExceptionMessage.getDifficultyLevelNotAvailableMessage());
        } else {
            level = i;
        }
    }

    public void setPlayer(Player player) throws PlayerNameIsEmptyException {
        if (player.getPlayerName().isEmpty()) {
            throw  new PlayerNameIsEmptyException(ExceptionMessage.getPlayerNameCannotBeEmptyMessage());
        } else {
            players.add(player);
        }
    }

    public void selectMove(Move move) throws MoveNotAvailableException {
        if (move != null) {
            lastPlayerMove = move;
            setMove(move);
        } else {
            setSystemMove();
        }
    }

    public int[] mapNumberToBoardElement(int i) throws IllegalArgumentException {
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
}
