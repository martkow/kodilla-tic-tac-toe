package player;

public class Player {
    private String playerName;
    private boolean isSystemPlayer;

    public Player(String playerName, boolean isSystemPlayer) {
        this.playerName = playerName;
        this.isSystemPlayer = isSystemPlayer;
    }

    public boolean isSystemPlayer() {
        return isSystemPlayer;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
