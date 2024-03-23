package game.strategy;

import board.Board;
import game.move.Move;
import game.move.MoveType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SmartMovesSeeker {
    public static Move seekForAdjacentFreeSmartMove(Board board, Move lastMove) {
        int i = lastMove.getRow();
        int j = lastMove.getColumn();
        int max = board.getBoardSize();
        List<int[]> indexes = new ArrayList<>();
        for (int k = i - 1; k < i + 2; k++) {
            for (int l = j - 1; l < j + 2; l++) {
                indexes.add(new int[]{k, l});
            }
        }
        Optional<int[]> smartIndex = indexes.stream()
                .filter(index -> index[0] >= 0 && index[1] >= 0 && index[0] < max && index[1] < max && index[0] != i && index[1] != j)
                .filter(index -> board.isFieldFree(index[0], index[1]))
                .findAny();

        return smartIndex.map(ints -> new Move(ints[0], ints[1], seekForSmartMoveType(lastMove))).orElseGet(() -> seekForAnyFreeSmartMove(board, lastMove));
    }

    public static Move seekForAnyFreeSmartMove(Board board, Move lastMove) {
        Random random = new Random();
        while (true) {
            Move move = new Move(
                    random.nextInt(board.getBoardSize()),
                    random.nextInt(board.getBoardSize()),
                    seekForSmartMoveType(lastMove)
            );

            if (board.isFieldFree(move.getRow(), move.getColumn())) {
                return move;
            }
        }
    }

    public static MoveType seekForSmartMoveType(Move lastMove) {
        return lastMove.getMoveType().equals(MoveType.CIRCLE) ? MoveType.CROSS : MoveType.CIRCLE;
    }
}
