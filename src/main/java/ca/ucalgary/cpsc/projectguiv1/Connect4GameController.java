package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Connect4GameController {

    @FXML
    private GridPane gamePane;

    @FXML
    TextField chatTxtField;

    @FXML
    VBox chatBox;

    @FXML
    ScrollPane chatScrlPane;

    @FXML
    private Button exitBtn;

    @FXML
    private Label player1Name, player2Name, winLabelP1, winLabelP2, rankLabelP1, rankLabelP2, infoLabel;

    private final int rows = 6;
    private final int columns = 7;
    private final Circle[][] board = new Circle[rows][columns];
    private boolean playerOneTurn = true;

    @FXML
    public void initialize() {
        // Initialize the board with empty cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Circle cell = new Circle(35, Color.WHITE);
                cell.setStroke(Color.BLACK);
                cell.setOnMouseClicked(event -> handleCellClick(cell));
                gamePane.add(cell, col, row);
                board[row][col] = cell;
            }
        }
        infoLabel.setText("Player 1's Turn (Yellow)");
    }

    private void handleCellClick(Circle cell) {
        int col = GridPane.getColumnIndex(cell);
        int row = getAvailableRow(col);

        if (row == -1) {
            infoLabel.setText("Column is full. Try a different column.");
            return;
        }

        // Place the token
        board[row][col].setFill(playerOneTurn ? Color.YELLOW : Color.RED);

        // Check for a winner
        if (checkWinner(row, col)) {
            infoLabel.setText((playerOneTurn ? "Player 1" : "Player 2") + " Wins!");
            gamePane.setDisable(true); // Disable further moves
            return;
        }

        // Switch turns
        playerOneTurn = !playerOneTurn;
        infoLabel.setText(playerOneTurn ? "Player 1's Turn (Yellow)" : "Player 2's Turn (Red)");
    }

    private int getAvailableRow(int col) {
        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][col].getFill().equals(Color.WHITE)) {
                return row;
            }
        }
        return -1; // Column is full
    }

    private boolean checkWinner(int row, int col) {
        Color playerColor = (Color) board[row][col].getFill();
        return checkDirection(row, col, playerColor, 1, 0) // Horizontal
                || checkDirection(row, col, playerColor, 0, 1) // Vertical
                || checkDirection(row, col, playerColor, 1, 1) // Diagonal \
                || checkDirection(row, col, playerColor, 1, -1); // Diagonal /
    }

    private boolean checkDirection(int row, int col, Color color, int rowDir, int colDir) {
        int count = 1;
        count += countConsecutive(row, col, color, rowDir, colDir);
        count += countConsecutive(row, col, color, -rowDir, -colDir);
        return count >= 4;
    }

    private int countConsecutive(int row, int col, Color color, int rowDir, int colDir) {
        int count = 0;
        int r = row + rowDir, c = col + colDir;
        while (r >= 0 && r < rows && c >= 0 && c < columns && board[r][c].getFill().equals(color)) {
            count++;
            r += rowDir;
            c += colDir;
        }
        return count;
    }

    @FXML
    private void exitBtnFunc() {
        System.exit(0);
    }


    public void sendBtnFunc() {
        // Get the text from the input field
        String message = this.chatTxtField.getText();

        // Check if the message is not empty
        if (message.isEmpty()) {
            infoLabel.setText("Cannot send an empty message!");
            return;
        }

        // Create a new Label for the chat message
        //Label chatMessage = new Label("Player 1: " + message);

        String chatTxt = "";
        chatTxt += "Player 1: " + message;

        // Add the message to the chatBox
        this.chatBox.getChildren().add(new Label(chatTxt));

        // Scroll to the bottom of the chat box
        this.chatScrlPane.setContent(this.chatBox);

        // Clear the input field
        chatTxtField.clear();
    }

}
