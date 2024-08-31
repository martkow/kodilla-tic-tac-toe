package player;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class Player {
    private String playerName;
    private boolean systemPlayer;

    @Override
    public String toString() {
        return playerName;
    }
}
