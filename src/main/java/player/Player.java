package player;

public class Player {
    private String playerName;
    private boolean systemPlayer;

    public Player(String playerName, boolean systemPlayer) {
        this.playerName = playerName;
        this.systemPlayer = systemPlayer;
    }

    public boolean isSystemPlayer() {
        return systemPlayer;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
