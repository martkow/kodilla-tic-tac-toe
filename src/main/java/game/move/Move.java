package game.move;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    private int row;
    private int column;
    private MoveType moveType;
}
