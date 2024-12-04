package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import gameLogic.ConnectFour;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Connect4GameController {

    // Constants for board size
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    // Instance variables
    private final Circle[][] board = new Circle[ROWS][COLUMNS];
    private ConnectFour connectFourGame;
    private User playerOne;
    private User playerTwo;
    private boolean isPlayerOneTurn = true;

    // FXML Components
    @FXML
    private GridPane gamePane;
    @FXML
    private Button exitBtn;
    @FXML
    private Pane identity;
    @FXML
    private Label infoLabel;
    @FXML
    private Label player1Name;
    @FXML
    private Label winLabelP1;
    @FXML
    private Label rankLabelP1;
    @FXML
    private Label player2Name;
    @FXML
    private Label winLabelP2;
    @FXML
    private Label rankLabelP2;

    // Initialization method
    @FXML
    public void initialize() {
        // Initializing players
        playerOne = new User("1", "firsstUsr", "email@google.com");
        playerTwo = new User("2", "scndUsr", "otheremail@google.com");
        connectFourGame = new ConnectFour(2);
        connectFourGame.addPlayer(playerOne.getUsername());
        connectFourGame.addPlayer(playerTwo.getUsername());

        Random random = new Random();
        connectFourGame.setCurrentPlayer(random.nextInt(2));

        if (connectFourGame.getCurrentPlayer().equals(playerOne.getUsername())) {
            infoLabel.setText("Click to start!");
        } else {
            infoLabel.setText("Click to start!");
        }

        // Initializing the board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle cell = new Circle(35, Color.WHITE);
                cell.setStroke(Color.BLACK);
                cell.setOnMouseClicked(event -> handleCellClick(cell));
                gamePane.add(cell, col, row);
                board[row][col] = cell;
            }
        }

        // Initializing the player data
        player1Name.setText(playerOne.getUsername());
        player2Name.setText(playerTwo.getUsername());
        winLabelP1.setText("Win: " + playerOne.getPlayerProfile().getConnectFourProfile().getTotalWins());
        winLabelP2.setText("Win: " + playerTwo.getPlayerProfile().getConnectFourProfile().getTotalWins());
        rankLabelP1.setText("Rank: " + playerOne.getPlayerProfile().getConnectFourProfile().getScoreRank());
        rankLabelP2.setText("Rank: " + playerTwo.getPlayerProfile().getConnectFourProfile().getScoreRank());

    }


    // Handle cell click event (when a player places a token)
    private void handleCellClick(Circle cell) {
        int col = GridPane.getColumnIndex(cell);
        int row = getAvailableRow(col);

        if (row == -1) {
            infoLabel.setText("Column is full. Try a different column.");
            return;
        }

        // Place the token for the current player
        // Determine the color to set based on whose turn it is
        Color playerColor;
        if (isPlayerOneTurn) {
            playerColor = Color.YELLOW; // Player 1's color (Yellow)
        } else {
            playerColor = Color.RED; // Player 2's color (Red)
        }

        // Set the color of the token at the given position (row, col)
        board[row][col].setFill(playerColor);

        // Check for a winner
        if (checkWinner(row, col)) {
            showWinnerAlert();
        } else {
            switchTurn();
        }
    }

    // Switch turns between players
    private void switchTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;
        // Declare a message string to hold the information about whose turn it is
        String turnMessage;

        // Check if it's Player 1's turn
        if (isPlayerOneTurn) {
            // It's Player 1's turn
            turnMessage = "Player 1's Turn (Yellow)";
        } else {
            // It's Player 2's turn
            turnMessage = "Player 2's Turn (Red)";
        }

        // Update the infoLabel with the turn message
        infoLabel.setText(turnMessage);


        // Automatically make a move for player 2 if it's their turn
        if (!isPlayerOneTurn) {
            makeRandomMove();
        }
    }

    // Make a random move for Player 2 (AI)
    private void makeRandomMove() {
        Random rand = new Random();
        int col;
        int row;

        do {
            col = rand.nextInt(COLUMNS);
            row = getAvailableRow(col);
        } while (row == -1);

        // Check if it's Player 1's turn
        if (isPlayerOneTurn) {
            // It's Player 1's turn, so fill the cell with yellow
            board[row][col].setFill(Color.YELLOW);
        } else {
            // It's Player 2's turn, so fill the cell with red
            board[row][col].setFill(Color.RED);
        }

        if (checkWinner(row, col)) {
            showWinnerAlert();
        } else {
            switchTurn();
        }
    }

    // Get the available row for a specific column
    private int getAvailableRow(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col].getFill().equals(Color.WHITE)) {
                return row;
            }
        }
        return -1; // Column is full
    }

    // Check if the current player has won after placing a token
    private boolean checkWinner(int row, int col) {
        Color playerColor = (Color) board[row][col].getFill();
        return checkDirection(row, col, playerColor, 1, 0) // Horizontal
                || checkDirection(row, col, playerColor, 0, 1) // Vertical
                || checkDirection(row, col, playerColor, 1, 1) // Diagonal \
                || checkDirection(row, col, playerColor, 1, -1); // Diagonal /
    }

    // Check for consecutive tokens in a specific direction
    private boolean checkDirection(int row, int col, Color color, int rowDir, int colDir) {
        int count = 1;
        count += countConsecutive(row, col, color, rowDir, colDir);
        count += countConsecutive(row, col, color, -rowDir, -colDir);
        return count >= 4;
    }

    // Count the consecutive tokens in one direction
    private int countConsecutive(int row, int col, Color color, int rowDir, int colDir) {
        int count = 0;
        int r = row + rowDir, c = col + colDir;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c].getFill().equals(color)) {
            count++;
            r += rowDir;
            c += colDir;
        }
        return count;
    }

    // Show an alert when a player wins
    private void showWinnerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner!");
        alert.setHeaderText((isPlayerOneTurn ? "Player 1" : "Player 2") + " won!");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    exitBtnFunc();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Exit the game and return to the main menu
    @FXML
    private void exitBtnFunc() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Connect_4_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStage = new Stage();
        newStage.sizeToScene();
        newStage.setTitle("Main Menu Connect4");
        newStage.setScene(scene);
        newStage.show();
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.close();
    }

    // Placeholder for chat feature (not implemented)
    @FXML
    private void sendBtnFunc() {
        infoLabel.setText("Chat feature not implemented yet.");
    }
}
