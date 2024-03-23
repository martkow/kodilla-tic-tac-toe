package board;

import message.ExceptionMessage;

public class Board {

    private int[][] board;
    private int boardSize;
    private int minNoOfElements;

    public Board(int i) throws BoardSizeNotAvailableException {
        if (i != 3 && i != 10) {
            throw new BoardSizeNotAvailableException(ExceptionMessage.getBoardSizeNotAvailableMessage(i));
        } else {
            board = new int[i][i];

            if (i == 3) {
                boardSize = 3;
                minNoOfElements = 3;
            } else {
                boardSize = 10;
                minNoOfElements = 5;
            }
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setValueForField(int i, int j, int value) throws MoveNotAvailableException {
        if (!isFieldFree(i, j)) {
            throw new MoveNotAvailableException(ExceptionMessage.getMoveNotAvailableMessage());
        } else {
            setValueForFreeField(i, j, value);
        }
    }

    public void setValueForFreeField(int i, int j, int value) {
        board[i][j] = value;
    }

    public boolean isFieldFree(int i, int j) {
        return board[i][j] == 0;
    }

    public boolean isLine() {
        return isNElementsInLine(minNoOfElements);
    }

    private boolean isNElementsInLine(int n) {
        int max = boardSize - n + 1;

        for (int j = 0; j < max; j++) {
            for (int i = 0; i < boardSize; i++) {
                if (isNextNAdjacentElementsEqualAndSet(i, j, n, true, false, false) ||
                        isNextNAdjacentElementsEqualAndSet(j, i, n, false, true, false)) {
                    return true;
                }
            }

            for (int i = 0; i < max; i++) {
                if (isNextNAdjacentElementsEqualAndSet(i, j, n, false, false, false) ||
                        isNextNAdjacentElementsEqualAndSet(i, j + n - 1, n, false, false, true)) {
                    return true;
                }
            }

        }

        return false;
    }

    private boolean isNextNAdjacentElementsEqualAndSet(int i, int j, int n, boolean inRow, boolean inColumn, boolean reverseDiagonal) {
        if (board[i][j] != 0) {
            for (int k = 1; k < n; k++) {
                if (board[i][j] != board[inRow ? i : i + k][inColumn ? j : (reverseDiagonal ? j - k : j + k)]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean hasAnyMatch(int value) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == value) return true;
            }
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            printRow(i);
        }
    }

    private void printRow(int row) {
        System.out.print("|");
        for (int j = 0; j < boardSize; j++) {
            System.out.print(" " + printFieldValue(row, j) + " |");
        }
        System.out.print("\n");
    }

    private String printFieldValue(int i, int j) {
        return board[i][j] == 0 ? " " : (board[i][j] == -1 ? "O" : "X");
    }
}
