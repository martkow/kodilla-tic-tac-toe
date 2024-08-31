package message;

import player.Player;

import java.util.List;

public class UserMessage {
    private static final String GAME_TITLE = "Tic Tac Toe";
    private static final String WELCOME_MESSAGE = """
                ************************************************************
                                    TIC-TAC-TOE GAME
                ************************************************************
            """;
    private static final String SET_BOARD_MESSAGE = """
            Enter board size:
            3 -> 3x3 board
            10 -> 10x10 board""";

    private static final String SET_DIFFICULTY_LEVEL_MESSAGE = """
            Set difficulty level:
            0 -> Easy
            1 -> Medium""";
    private static final String PLAYERS_MESSAGE = "We have the following players:";
    private static final String SET_PLAYER_MESSAGE = "Set players:";
    private static final String END_WINNER_MESSAGE = "End of the game!... Winner is... ";
    private static final String END_DRAW_MESSAGE = "End of the game!...It's a draw...";
    private static final String END_GAME_TITLE_MESSAGE = "Game over";

    public static String getGameTitle() {
        return GAME_TITLE;
    }

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printPlayersMessage(List<Player> players) {
        System.out.println(PLAYERS_MESSAGE);
        players.forEach(System.out::println);
    }

    public static void printSetPlayersMessage() {
        System.out.println(SET_PLAYER_MESSAGE);
    }

    public static void printEnterPlayerMessage(int i) {
        System.out.println("Enter player " + i + " name:");
    }

    public static void printSelectMoveMessage(Player player) {
        System.out.printf("********************** %s move **********************************\n",player);
    }

    public static void printInstructionForUSerMoveMessage(int n) {
        System.out.printf("Enter field number [1-%d] and move type [CIRCLE/CROSS], e.g. 9 CROSS:\n", n);
    }

    public static void printEndWinnerMessage(Player player) {
        System.out.println(END_WINNER_MESSAGE + player);
    }

    public static String getEndWinnerMessage() {
        return END_WINNER_MESSAGE;
    }

    public static void printEndDrawMessage() {
        System.out.println(END_DRAW_MESSAGE);
    }

    public static String getEndDrawMessage() {
        return END_DRAW_MESSAGE;
    }

    public static String getEndGameTitleMessage() {
        return END_GAME_TITLE_MESSAGE;
    }

    public static void printSetBoardSizeMessage() {
        System.out.println(SET_BOARD_MESSAGE);
    }

    public static void printSetDifficultyLevelMessage() {
        System.out.println(SET_DIFFICULTY_LEVEL_MESSAGE);
    }
}
