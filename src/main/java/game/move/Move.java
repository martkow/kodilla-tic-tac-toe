package game.move;

public class Move {
    private int row;
    private int column;
    private MoveType moveType;

    public Move() {
    }

    public Move(int row, int column, MoveType moveType) {
        this.row = row;
        this.column = column;
        this.moveType = moveType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public MoveType getMoveType() {
        return moveType;
    }
}
