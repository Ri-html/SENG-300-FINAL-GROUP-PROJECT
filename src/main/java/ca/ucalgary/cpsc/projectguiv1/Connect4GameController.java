package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
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

    // Initialization method
    @FXML
    public void initialize() {
        // Initializing players
        playerOne = HelloApplication.usrDb.searchByUsername("aaronsheikh");
        playerTwo = HelloApplication.usrDb.searchByUsername("SndUsr");
        if (playerTwo == null) {
            playerTwo = new User("SndUsr", "sndUsr@gmail.com", "12346578");
            HelloApplication.usrDb.addUser(playerTwo);
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
    private void sendBtnFunc() {
        infoLabel.setText("Chat feature not implemented yet.");
    }

    public void saveEndData(char result, int player) { // Might have to change
        playerTwo = HelloApplication.usrDb.searchByUsername(playerTwo.getUsername());

        if ((result == 'W') && (player == 1)) {
            playerOne.getPlayerProfile().getConnectFourProfile().setTotalWins(playerOne.getPlayerProfile().getConnectFourProfile().getTotalWins() + 1);
            playerOne.getPlayerProfile().getConnectFourProfile().setTotalLosses(playerTwo.getPlayerProfile().getConnectFourProfile().getTotalLosses() + 1);
            playerOne.getPlayerProfile().getConnectFourProfile().updateRanking(playerOne.getPlayerProfile().getConnectFourProfile().getScoreRank(), playerOne.getPlayerProfile().getConnectFourProfile().getWinRateRank());
            playerOne.getPlayerProfile().getConnectFourProfile().updateGameHistory(playerOne.getUsername(), "W", 1);

        } else if ((result == 'W') && (player == 2)) {
            playerTwo.getPlayerProfile().getConnectFourProfile().setTotalWins(playerTwo.getPlayerProfile().getConnectFourProfile().getTotalWins() + 1);
            playerTwo.getPlayerProfile().getConnectFourProfile().setTotalLosses(playerOne.getPlayerProfile().getConnectFourProfile().getTotalLosses() + 1);
            playerTwo.getPlayerProfile().getConnectFourProfile().updateRanking(playerTwo.getPlayerProfile().getConnectFourProfile().getScoreRank(), playerTwo.getPlayerProfile().getConnectFourProfile().getWinRateRank());
            playerTwo.getPlayerProfile().getConnectFourProfile().updateGameHistory(playerTwo.getUsername(), "W", 1);
        }
    }
}
