package tic.tac.toe.console;

import board.Board;
import message.UserMessage;
import player.Player;

import java.util.List;

public class ConsoleGamePrinter {
    private static final String TITLE = "Game ranking:";

    public void printGameRanking(List<String> list) {
        System.out.println(TITLE);
        list.forEach(System.out::println);
    }

    public void printBoard(Board board) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            printRow(i, board);
        }
    }

    private void printRow(int row, Board board) {
        System.out.print("|");
        for (int j = 0; j < board.getBoardSize(); j++) {
            System.out.print(" " + printFieldValue(row, j, board) + " |");
        }
        System.out.print("\n");
    }

    private String printFieldValue(int i, int j, Board board) {
        return board.getFieldValue(i, j) == 0 ? " " : (board.getFieldValue(i, j) == -1 ? "O" : "X");
    }

    public void printGameEndingWithWinner(Player player) {
        UserMessage.printEndWinnerMessage(player);
    }

    public void printGameEndingWithDraw() {
        UserMessage.printEndDrawMessage();
    }
}
