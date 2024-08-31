package game;

public class PlayerNameIsEmptyException extends Exception{
    public PlayerNameIsEmptyException(String message) {
        super(message);
    }
}