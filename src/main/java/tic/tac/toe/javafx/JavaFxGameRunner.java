package tic.tac.toe.javafx;

import board.MoveNotAvailableException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import game.Game;
import game.move.Move;
import game.move.MoveType;
import message.ExceptionMessage;
import message.UserMessage;
import player.Player;
import ranking.RankingHistory;

import java.io.IOException;
import java.util.List;

public class JavaFxGameRunner extends Application {
    private static final int BOARD_SIZE_3 = 3;
    private static final int BOARD_SIZE_10 = 10;
    private static final String START_GAME_BUTTON_LABEL = "Start Game";
    private static final String SHOW_RANKING_BUTTON_LABEL = "Show Ranking";
    private static final String PLAYER_NAME_FIELD_PROMPT = "Enter player name";
    private static final String PLAYER_NAME_FIELD_LABEL = "Player Name:";
    private static final String BOARD_SIZE_CHOICE_BOX_LABEL = "Board Size:";
    private static final String DIFFICULTY_CHOICE_BOX_LABEL = "Difficulty Level:";
    private static final String SYSTEM_PLAYER_NAME = "System";
    private static final String X = "X";
    private static final String O = "O";
    private static final String RANKING_TITLE = "Tic Tac Toe Ranking";
    private static final String RANKING_NOT_AVAILABLE_MESSAGE = "No ranking data available.";
    private Game game;
    private boolean playerXTurn = true; // true if player X's turn, false if player O's turn
    private TextField playerNameField;
    private ChoiceBox<Integer> boardSizeChoiceBox;
    private ChoiceBox<Integer> difficultyChoiceBox;
    private Button[][] buttons;
    private RankingHistory rankingHistory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(UserMessage.getGameTitle());
        rankingHistory = new RankingHistory(); // Inicjalizacja klasy RankingHistory

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10; -fx-background-color: #E0C3C3;");

        // Input field player name
        playerNameField = new TextField();
        playerNameField.setPromptText(PLAYER_NAME_FIELD_PROMPT);
        // Input field board size
        boardSizeChoiceBox = new ChoiceBox<>();
        boardSizeChoiceBox.getItems().addAll(BOARD_SIZE_3, BOARD_SIZE_10);
        boardSizeChoiceBox.setValue(BOARD_SIZE_3);
        // Input field difficulty level
        difficultyChoiceBox = new ChoiceBox<>();
        difficultyChoiceBox.getItems().addAll(0, 1);
        difficultyChoiceBox.setValue(0);

        Button startButton = new Button(START_GAME_BUTTON_LABEL);
        startButton.setOnAction(e -> startGame());

        Button showRankingButton = new Button(SHOW_RANKING_BUTTON_LABEL);
        showRankingButton.setOnAction(e -> showRanking());

        root.getChildren().addAll(
                new Label(PLAYER_NAME_FIELD_LABEL),
                playerNameField,
                new Label(BOARD_SIZE_CHOICE_BOX_LABEL),
                boardSizeChoiceBox,
                new Label(DIFFICULTY_CHOICE_BOX_LABEL),
                difficultyChoiceBox,
                startButton,
                showRankingButton
        );

        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame() {
        String humanPlayerName = playerNameField.getText().trim();
        int boardSize = boardSizeChoiceBox.getValue();
        int difficultyLevel = difficultyChoiceBox.getValue();

        if (humanPlayerName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, ExceptionMessage.getErrorTitleMessage(), ExceptionMessage.getPlayerNameCannotBeEmptyMessage());
            return;
        }

        try {
            game = new Game();
            game.setBoard(boardSize);
            game.setDifficultyLevel(difficultyLevel);

            Player humanPlayer = new Player(humanPlayerName, false);
            game.setPlayer(humanPlayer);

            Player systemPlayer = new Player(SYSTEM_PLAYER_NAME, true);
            game.setPlayer(systemPlayer);

            createBoardUI(boardSize);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, ExceptionMessage.getErrorTitleMessage(), ExceptionMessage.getErrorOccurredMessage());
        }
    }

    private void createBoardUI(int size) {
        Stage boardStage = new Stage();
        boardStage.setTitle(UserMessage.getGameTitle());

        double boardSize = size < 5 ? 300 : 600;
        double buttonSize = boardSize / size;
        double fontSize = Math.max(10, buttonSize / 3);

        GridPane grid = new GridPane();
        grid.setVgap(2);
        grid.setHgap(2);
        grid.setStyle("-fx-background-color: #E0C3C3;");

        buttons = new Button[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = new Button();
                button.setPrefSize(buttonSize, buttonSize);
                button.setStyle("-fx-font-size: " + fontSize + "; -fx-background-color: lightgrey;");
                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> handleButtonClick(button, finalI, finalJ));
                buttons[i][j] = button;
                grid.add(button, j, i);
            }
        }

        Scene scene = new Scene(grid, boardSize, boardSize, Color.BLACK);
        boardStage.setScene(scene);
        boardStage.show();

        // Start the game loop
        gameLoop();
    }

    private void handleButtonClick(Button button, int row, int col) {
        if (button.getText().isEmpty() && playerXTurn) {
            button.setText(X);
            makeMove(row, col, MoveType.CROSS);
            playerXTurn = false;
            if (!game.isGameWon() && game.isGamePossibleToWin()) {
                // System move after player move
                try {
                    game.selectMove(null);
                } catch (MoveNotAvailableException e) {
                    //
                }
                updateUI();
                playerXTurn = true;
            }
        }
    }

    private void makeMove(int row, int col, MoveType moveType) {
        try {
            Move move = new Move(row, col, moveType);
            game.selectMove(move);

            if (game.isGameWon()) {
                endGame(moveType);
            } else if (!game.isGamePossibleToWin()) {
                showAlert(Alert.AlertType.INFORMATION, UserMessage.getEndGameTitleMessage(), UserMessage.getEndDrawMessage());
                saveRanking(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, ExceptionMessage.getErrorTitleMessage(), ExceptionMessage.getErrorOccurredMessage());
        }
    }

    private void updateUI() {
        // Update UI after system move
        for (int i = 0; i < game.getBoard().getBoardSize(); i++) {
            for (int j = 0; j < game.getBoard().getBoardSize(); j++) {
                int value = game.getBoard().getFieldValue(i, j);
                if (value == 1) {
                    buttons[i][j].setText(X);
                } else if (value == -1) {
                    buttons[i][j].setText(O);
                }
            }
        }
        if (game.isGameWon()) {
            endGame(MoveType.CIRCLE); // Assuming the system plays as O
        }
    }

    private void endGame(MoveType winnerType) {
        Player winner = winnerType == MoveType.CIRCLE ? new Player(SYSTEM_PLAYER_NAME, true) : new Player(playerNameField.getText().trim(), false);
        showAlert(Alert.AlertType.INFORMATION, UserMessage.getEndGameTitleMessage(), UserMessage.getEndWinnerMessage() + (winnerType == MoveType.CIRCLE ? SYSTEM_PLAYER_NAME : playerNameField.getText()));
        saveRanking(winner);
    }

    private void saveRanking(Player winner) {
        try {
            rankingHistory.savePlayersToRanking(game.getPlayers(), winner);
        } catch (IOException e) {
            //
        }
    }

    private void showRanking() {
        try {
            List<String> ranking = rankingHistory.getRanking();

            if (ranking.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, RANKING_TITLE, RANKING_NOT_AVAILABLE_MESSAGE);
            } else {
                StringBuilder rankingText = new StringBuilder();
                for (String line : ranking) {
                    rankingText.append(line).append("\n");
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(RANKING_TITLE);
                alert.setHeaderText(null);
                alert.setContentText(rankingText.toString());
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.INFORMATION, RANKING_TITLE, RANKING_NOT_AVAILABLE_MESSAGE);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void gameLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    if (!game.isGamePossibleToWin() && !game.isGameWon()) {
                        showAlert(Alert.AlertType.INFORMATION, UserMessage.getEndGameTitleMessage(), UserMessage.getEndDrawMessage());
                        saveRanking(null);
                        break;
                    } else if (game.isGamePossibleToWin() && !game.isGameWon()) {
                        // System move
                        game.selectMove(null);
                        updateUI();
                        if (game.isGameWon()) {
                            endGame(MoveType.CIRCLE);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
