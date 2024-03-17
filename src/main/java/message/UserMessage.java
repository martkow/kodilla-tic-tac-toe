package message;

import player.Player;

import java.util.List;

public class UserMessage {
    private static final String welcomeMessage = """
                ************************************************************
                                    TIC-TAC-TOE GAME
                ************************************************************
            """;
    private static final String setBoardMessage = """
            Enter board size:
            3 -> 3x3 board
            10 -> 10x10 board""";
    private static final String playersMessage = "We have the following players:";
    private static final String setPlayerMessage = "Set players:";
    private static final String endWinnerMessage = "End of the game!... Winner is... ";
    private static final String endDrawMessage = "End of the game!...No winner...";
    public static void printWelcomeMessage() {
        System.out.println(welcomeMessage);
    }

    public static void printPlayersMessage(List<Player> players) {
        System.out.println(playersMessage);
        players.forEach(System.out::println);
    }

    public static void printSetPlayersMessage() {
        System.out.println(setPlayerMessage);
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
        System.out.println(endWinnerMessage + player);
    }

    public static void printEndDrawMessage() {
        System.out.println(endDrawMessage);
    }

    public static void printSetBoardSizeMessage() {
        System.out.println(setBoardMessage);
    }
}
