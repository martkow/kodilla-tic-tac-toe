package message;

public class ExceptionMessage {
    private static final String WRONG_DATA_ENTERED_MESSAGE = "Wrong data entered!";
    private static final String MOVE_NOT_AVAILABLE_MESSAGE = "Field is already selected!";
    private static final String BOARD_SIZE_NOT_AVAILABLE_MESSAGE = "Board size not available: ";
    private static final String DIFFICULTY_LEVEL_NOT_AVAILABLE_MESSAGE = "Difficulty level not available!";
    private static final String PLAYER_NAME_CANNOT_BE_EMPTY_MESSAGE = "Player name cannot be empty!";
    private static final String RANKING_NOT_AVAILABLE_MESSAGE = "Ranking not available";
    private static final String ERROR_OCCURRED_MESSAGE = "An error occurred while starting the game!";
    private static final String ERROR_TITLE_MESSAGE = "Error";

    public static void printWrongDataMessage() {
        System.out.println(WRONG_DATA_ENTERED_MESSAGE);
    }

    public static String getMoveNotAvailableMessage() {
        return MOVE_NOT_AVAILABLE_MESSAGE;
    }

    public static String getBoardSizeNotAvailableMessage(int i) {
        return BOARD_SIZE_NOT_AVAILABLE_MESSAGE + i + "x" + i;
    }

    public static String getDifficultyLevelNotAvailableMessage() {
        return DIFFICULTY_LEVEL_NOT_AVAILABLE_MESSAGE;
    }

    public static void printRankingNotAvailableMessage() {
        System.out.println(RANKING_NOT_AVAILABLE_MESSAGE);
    }

    public static String getRankingNotAvailableMessage() {
        return RANKING_NOT_AVAILABLE_MESSAGE;
    }

    public static String getPlayerNameCannotBeEmptyMessage() {
        return PLAYER_NAME_CANNOT_BE_EMPTY_MESSAGE;
    }

    public static String getErrorOccurredMessage() {
        return ERROR_OCCURRED_MESSAGE;
    }

    public static String getErrorTitleMessage() {
        return ERROR_TITLE_MESSAGE;
    }
}
