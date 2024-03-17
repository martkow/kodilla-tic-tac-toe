package game;

import board.BoardSizeNotAvailableException;
import board.MoveNotAvailableException;
import message.ExceptionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for Game class")
public class GameTestSuite {
    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 1st row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInFirstRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 0);
        game.getBoard().setValueForField(0,1, 0);
        game.getBoard().setValueForField(0,2, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 2nd row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInSecondRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(1,0, 0);
        game.getBoard().setValueForField(1,1, 0);
        game.getBoard().setValueForField(1,2, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 3rd row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInThirdRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(2,0, 0);
        game.getBoard().setValueForField(2,1, 0);
        game.getBoard().setValueForField(2,2, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 1st column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInFirstColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 0);
        game.getBoard().setValueForField(1,0, 0);
        game.getBoard().setValueForField(2,0, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 2nd column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInSecondColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,1, 0);
        game.getBoard().setValueForField(1,1, 0);
        game.getBoard().setValueForField(2,1, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 3rd column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInThirdColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,2, 0);
        game.getBoard().setValueForField(1,2, 0);
        game.getBoard().setValueForField(2,2, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 1st diagonal")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInFirstDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 0);
        game.getBoard().setValueForField(1,1, 0);
        game.getBoard().setValueForField(2,2, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CIRCLES in 2nd diagonal")
    @Test
    void testCase3x3BoardForIsGameWonMethodCirclesInSecondDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,2, 0);
        game.getBoard().setValueForField(1,1, 0);
        game.getBoard().setValueForField(2,0, 0);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 1st row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInFirstRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 1);
        game.getBoard().setValueForField(0,1, 1);
        game.getBoard().setValueForField(0,2, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 2nd row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInSecondRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(1,0, 1);
        game.getBoard().setValueForField(1,1, 1);
        game.getBoard().setValueForField(1,2, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 3rd row")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInThirdRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(2,0, 1);
        game.getBoard().setValueForField(2,1, 1);
        game.getBoard().setValueForField(2,2, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 1st column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInFirstColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 1);
        game.getBoard().setValueForField(1,0, 1);
        game.getBoard().setValueForField(2,0, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 2nd column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInSecondColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,1, 1);
        game.getBoard().setValueForField(1,1, 1);
        game.getBoard().setValueForField(2,1, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 3rd column")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInThirdColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,2, 1);
        game.getBoard().setValueForField(1,2, 1);
        game.getBoard().setValueForField(2,2, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 1st diagonal")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInFirstDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 1);
        game.getBoard().setValueForField(1,1, 1);
        game.getBoard().setValueForField(2,2, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for isGameWon method: CROSSES in 2nd diagonal")
    @Test
    void testCase3x3BoardForIsGameWonMethodCrossesInSecondDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,2, 1);
        game.getBoard().setValueForField(1,1, 1);
        game.getBoard().setValueForField(2,0, 1);
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("3x3 Board. Test case for draw: isGamePossibleToWin and isGameWon methods")
    @Test
    void testCase3x3BoardForIsGamePossibleToWinAndIsGameWonMethods() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 0);
        game.getBoard().setValueForField(0,1, 1);
        game.getBoard().setValueForField(0,2, 0);
        game.getBoard().setValueForField(1,0, 1);
        game.getBoard().setValueForField(1,1, 0);
        game.getBoard().setValueForField(1,2, 1);
        game.getBoard().setValueForField(2,0, 1);
        game.getBoard().setValueForField(2,1, 0);
        game.getBoard().setValueForField(2,2, 1);
        // When
        boolean resultIsGamePossibleToWin = game.isGamePossibleToWin();
        boolean resultIsGameWon = game.isGameWon();
        // Then
        Assertions.assertFalse(resultIsGamePossibleToWin);
        Assertions.assertFalse(resultIsGameWon);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 1st row")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesInFirstRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int j = 0; j < 5; j++) {
            game.getBoard().setValueForField(0, j, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 5th row")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesIn5thRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int j = 0; j < 5; j++) {
            game.getBoard().setValueForField(4, j, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 10th row")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesIn10thRow() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int j = 0; j < 5; j++) {
            game.getBoard().setValueForField(9, j, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 1st column")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesInFirstColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int i = 0; i < 5; i++) {
            game.getBoard().setValueForField(i, 0, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 5th column")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesIn5thColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int i = 0; i < 5; i++) {
            game.getBoard().setValueForField(i, 4, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 10th column")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesIn10thColumn() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int i = 0; i < 5; i++) {
            game.getBoard().setValueForField(i, 9, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 1st diagonal")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesInFirstDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int i = 0; i < 5; i++) {
            game.getBoard().setValueForField(i, i, 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("10x10 Board. Test case for isGameWon method: CIRCLES in 2nd diagonal")
    @Test
    void testCase10x10BoardForIsGameWonMethodCirclesInSecondDiagonal() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(10);
        for (int i = 0; i < 5; i++) {
            game.getBoard().setValueForField(i, 9 - i , 0);
        }
        // When
        boolean result = game.isGameWon();
        // Then
        Assertions.assertTrue(result);
    }

    @DisplayName("Test case for setValue method: move is not available exception")
    @Test
    void testCaseForSetValueMethodMoveIsNotAvailableException() throws MoveNotAvailableException, BoardSizeNotAvailableException {
        // Given
        Game game = new Game();
        game.setBoard(3);
        game.getBoard().setValueForField(0,0, 1);
        // When && Then
        MoveNotAvailableException thrown = Assertions.assertThrows(MoveNotAvailableException.class, () -> game.getBoard().setValueForField(0, 0, 1));
        Assertions.assertTrue(thrown.getMessage().equals(ExceptionMessage.getMoveNotAvailableMessage()));

    }
}
