package message;

public class ExceptionMessage {
    private static final String wrongDataMessage = "Wrong data entered!";

    private static final String moveNotAvailableMessage = "Field is already selected!";

    private static final String boardSizeNotAvailableMessage = "Board size not available: ";

    public static void printWrongDataMessage() {
        System.out.println(wrongDataMessage);
    }

    public static String getMoveNotAvailableMessage() {
        return moveNotAvailableMessage;
    }

    public static String getBoardSizeNotAvailableMessage(int i) {
        return boardSizeNotAvailableMessage + i + "x" + i;
    }
}
