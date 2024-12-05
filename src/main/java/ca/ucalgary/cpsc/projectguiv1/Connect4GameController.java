package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import gameLogic.ConnectFour;
import network.Network;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
    private boolean oneAlert = false;

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
    @FXML
    TextField chatTxtField;
    @FXML
    VBox chatBox;
    @FXML
    ScrollPane chatScrlPane;

    // Initialization method
    @FXML
    public void initialize() {
        // Initializing players

        playerOne = HelloApplication.usrDb.getCurrentUser();
        List<User> allPlayers = HelloApplication.usrDb.getAllUsers();
        Network network = new Network(allPlayers);
        List<User> connect4Players = network.findConnectFourRank(playerOne.getPlayerProfile().getConnectFourProfile().getScoreRank());


        Collections.shuffle(connect4Players);

        playerTwo = connect4Players.getFirst();
        if (playerTwo.getUsername().equals(playerOne.getUsername())) {
            playerTwo = connect4Players.get(1);
        }

        connectFourGame = new ConnectFour(2);
        connectFourGame.addPlayer(playerOne.getUsername());
        connectFourGame.addPlayer(playerTwo.getUsername());

        Random random = new Random();
        connectFourGame.setCurrentPlayer(random.nextInt(2));

        infoLabel.setText("Click to start!");


        // Initializing the board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int x = col;
                int y = row;
                Circle cell = new Circle(35, Color.WHITE);
                cell.setStroke(Color.BLACK);
                cell.setOnMouseClicked(event -> handleCellClick(x));
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
    private void handleCellClick(int col) {

        int row = getAvailableRow(col);
        int[] moves = {col, row};
        boolean isValid = connectFourGame.validateMove(moves);
        if (!isValid) {
            infoLabel.setText("Column is full. Try a different column.");
            return;
        }

        infoLabel.setText("");


        // Place the token for the current player
        // Determine the color to set based on whose turn it is
        Color playerColor;
        if (connectFourGame.getCurrentPlayer().equals(playerOne.getUsername())) {
            playerColor = Color.YELLOW; // Player 1's color (Yellow)
            connectFourGame.makeMove(moves);
            connectFourGame.switchCurrentPlayer();
            board[row][col].setFill(playerColor);

            checkEndCon();
            makeRandMove();
            checkEndCon();

        } else {
            makeRandMove();
            checkEndCon();
        }


    }

    private void makeRandMove() {
        Random rand = new Random();
        int col = rand.nextInt(COLUMNS);
        int row = getAvailableRow(col);
        int[] moves = {col, row};
        boolean isValid = connectFourGame.validateMove(moves);

        if (!isValid) {
            infoLabel.setText("Column is full. Try a different column.");
            return;
        }

        Color playerColor;
        if (connectFourGame.getCurrentPlayer().equals(playerOne.getUsername())) {
            playerColor = Color.YELLOW; // Player 1's color (Yellow)

            connectFourGame.makeMove(moves);
            connectFourGame.switchCurrentPlayer();
            board[row][col].setFill(playerColor);

            checkEndCon();

        } else {
            playerColor = Color.RED; // Player 1's color (Yellow)

            connectFourGame.makeMove(moves);
            connectFourGame.switchCurrentPlayer();
            board[row][col].setFill(playerColor);

            checkEndCon();
        }

    }

    public void checkEndCon() {
        if(!this.oneAlert) {
            if (connectFourGame.validateGameEnds() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("Red Wins!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 1);
                    try {
                        exitBtnFunc();
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });

                this.oneAlert = true;
            } else if (connectFourGame.validateGameEnds() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("Yellow Wins!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 2);
                    try {
                        exitBtnFunc();
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });
                this.oneAlert = true;
            }
        }
    }


//    private void checkEndCon() {
//        if (gameEnded) return; // Prevent further checks once the game has ended
//
//        if (connectFourGame.checkWin() == 1 || connectFourGame.checkWin() == 2) {
//            gameEnded = true; // Mark the game as ended
//            showWinnerAlert();
//        }
//    }

    // Get the available row for a specific column
    private int getAvailableRow(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col].getFill().equals(Color.WHITE)) {
                return row;
            }
        }
        return -1; // Column is full
    }


    // Show an alert when a player wins
//    private void showWinnerAlert() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Winner!");
//        alert.setHeaderText((connectFourGame.getCurrentPlayer().equals(playerOne.getUsername()) ? "Player 1" : "Player 2") + " won!");
//        alert.showAndWait().ifPresent(response -> {
//            if (response == ButtonType.OK) {
//                try {
//                    exitBtnFunc();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

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

    public void saveEndData(char result, int player) { // Might have to change

        if ((result == 'W') && (player == 1)) {
            playerOne.getPlayerProfile().getConnectFourProfile().setTotalWins(playerOne.getPlayerProfile().getConnectFourProfile().getTotalWins() + 1);
            playerTwo.getPlayerProfile().getConnectFourProfile().setTotalLosses(playerTwo.getPlayerProfile().getConnectFourProfile().getTotalLosses() + 1);
            playerOne.getPlayerProfile().getConnectFourProfile().updateRanking(playerOne.getPlayerProfile().getConnectFourProfile().getScoreRank(), playerOne.getPlayerProfile().getConnectFourProfile().getWinRateRank());
            playerTwo.getPlayerProfile().getConnectFourProfile().updateGameHistory(playerTwo.getUsername(), "W", 1);

        } else if ((result == 'W') && (player == 2)) {
            playerTwo.getPlayerProfile().getConnectFourProfile().setTotalWins(playerTwo.getPlayerProfile().getConnectFourProfile().getTotalWins() + 1);
            playerOne.getPlayerProfile().getConnectFourProfile().setTotalLosses(playerOne.getPlayerProfile().getConnectFourProfile().getTotalLosses() + 1);
            playerTwo.getPlayerProfile().getConnectFourProfile().updateRanking(playerTwo.getPlayerProfile().getConnectFourProfile().getScoreRank(), playerTwo.getPlayerProfile().getConnectFourProfile().getWinRateRank());
            playerOne.getPlayerProfile().getConnectFourProfile().updateGameHistory(playerOne.getUsername(), "W", 1);
        }
    }
}
