package board;

public class MoveNotAvailableException extends Exception{
    public MoveNotAvailableException(String message) {
        super(message);
    }
}
