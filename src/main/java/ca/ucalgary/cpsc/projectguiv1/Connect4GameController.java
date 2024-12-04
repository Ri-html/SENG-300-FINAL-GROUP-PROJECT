package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import gameLogic.ConnectFour;
import gameLogic.boardGames.AbstractBoardGame;
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
import gameLogic.piece.ConnectFourPiece;
import gameLogic.side.ConnectFourSide;

import java.io.IOException;
import java.util.Random;


public class Connect4GameController{

    private ConnectFour gameConnect4;
    @FXML
    Circle circle00;

    @FXML
    Pane identity;

    @FXML
    Button exitBtn;

    @FXML
    Label player1Name;

    @FXML
    Label winLabelP1;

    @FXML
    Label rankLabelP1;

    @FXML
    Label player2Name;

    @FXML
    Label winLabelP2;

    @FXML
    Label rankLabelP2;

    @FXML
    ScrollPane chatScrlPane;

    @FXML
    VBox chatBox;

    @FXML
    TextField chatTxtField;

    @FXML
    Button sendBtn;

    @FXML
    Label infoLabel;

    @FXML
    GridPane gamePane;

    private ConnectFour game;

    private static final int ROWS = 6;
    private static final int COLS = 7;
    private User user1;
    private User user2;

//    @FXML
//    public void initialize() {
//        circle00.setFill(Color.RED);
//        this.user1 = new User("1", "firsstUsr", "email@google.com");
//        this.user2 = new User("2", "scndUsr", "otheremail@google.com");
//        // Initialize the game
//        game = new ConnectFour(2);
//        game.addPlayer(this.user1.getUsername());
//        game.addPlayer(this.user2.getUsername());
//
//        // Create the board UI
//        initializeBoard();
//    }
//
//    /**
//     * Initialize the board with interactive columns for dropping pieces.
//     */
//    private void initializeBoard() {
//        for (int col = 0; col < COLS; col++) {
//            for (int row = 0; row < ROWS; row++) {
//                Pane cell = new Pane();
//                //cell.setStyle("-fx-border-color: black; -fx-background-color: white;");
//                cell.setPrefSize(60, 60);
//
////                Circle circle = new Circle(25);
////                circle.setFill(Color.WHITE);
////                circle.setCenterX(30);
////                circle.setCenterY(30);
////                cell.getChildren().add(circle);
//
//                // Add click handling for the column
//                if (row == ROWS - 1) {
//                    int finalCol = col;
//                    cell.setOnMouseClicked(event -> handleColumnClick(finalCol));
//                }
//
//                gamePane.add(cell, col, row);
//            }
//        }
//    }
//
//    /**
//     * Handle a column click to place a piece.
//     *
//     * @param column The column index clicked.
//     */
//    private void handleColumnClick(int column) {
//        if (!game.validateMove(new int[]{column})) {
//            showAlert("Invalid Move", "This column is full!");
//            return;
//        }
//
//        game.makeMove(new int[]{column});
//        // set color of the circle
//        gamePane.getChildren().
//        //game.switchCurrentPlayer();
//        updateBoard();
//
//        // Check game end conditions
//        switch (game.validateGameEnds()) {
//            case Victory:
//                showAlert("Game Over", "Player " + (game.getCurrentPlayer() + 1) + " wins!");
//                resetGame();
//                break;
//            case Draw:
//                showAlert("Game Over", "The game is a draw!");
//                resetGame();
//                break;
//            default:
//                game.switchCurrentPlayer();
//                break;
//        }
//    }
//
//    /**
//     * Update the UI board with the current game state.
//     */
//    private void updateBoard() {
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLS; col++) {
//                ConnectFourPiece piece = (ConnectFourPiece) game.getBoard()[row][col];
//                Pane cell = (Pane) getNodeFromGridPane(gamePane, col, row);
//                Circle circle = (Circle) cell.getChildren().get(0);
//
//                if (piece == null) {
//                    circle.setFill(Color.WHITE);
//                } else if (piece.getSide() == ConnectFourSide.RED) {
//                    circle.setFill(Color.RED);
//                } else if (piece.getSide() == ConnectFourSide.YELLOW) {
//                    circle.setFill(Color.YELLOW);
//                }
//            }
//        }
//    }
//
//    /**
//     * Reset the game state and board.
//     */
//    private void resetGame() {
//        game.setUpBoard();
//        updateBoard();
//    }
//
//    /**
//     * Show an alert dialog with a title and message.
//     *
//     * @param title   The title of the alert.
//     * @param message The message of the alert.
//     */
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    /**
//     * Get a specific node from the GridPane.
//     *
//     * @param gridPane The GridPane to search.
//     * @param col      The column index.
//     * @param row      The row index.
//     * @return The node at the specified position.
//     */
//    private Pane getNodeFromGridPane(GridPane gridPane, int col, int row) {
//        for (var node : gridPane.getChildren()) {
//            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
//                return (Pane) node;
//            }
//        }
//        return null;
//    }


//    private void updateBoard(){
//        for(int row = 0; row <  ROWS; row++){
//
//        }
//    }
//
//
//
//    @Deprecated
//    public Connect4GameController(){
//        this.user1 = new User("0", "FirstUser", "example@gmail.com");
//        this.user2 = new User("1", "SecondUser", "otherExample@gmail.com");
//
//        this.gameConnect4 = new ConnectFour(2);
//        this.gameConnect4.addPlayer(this.user1.getUsername());
//        this.gameConnect4.addPlayer(this.user2.getUsername());
//
//    }
//
//    public void setupGrid(){
//        if(this.setup == false){
//            for(int x = 0; x < 7; x++){
//                for(int y = 0; y < 6; y++){
//                    Pane newPane = new Pane();
//                    int xCoord = x;
//                    int yCoord = y;
//                    this.gamePane.add(newPane, xCoord, yCoord);
//                    newPane.setOnMouseClicked(mouseEvent -> {
//                        int[] coordsArr = {xCoord, yCoord};
//                        try {
//                            makeMove(coordsArr, newPane);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//
//                }
//            }
//            Random randInt = new Random();
//            this.gameConnect4.setCurrentPlayer(randInt.nextInt(2));
//
//            if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
//                infoLabel.setText(this.user1.getUsername() + "'s move!");
//            } else{
//                infoLabel.setText(this.user2.getUsername() + "'s move!");
//            }
//
//            // Set Labels
//            this.player1Name.setText(this.user1.getUsername());
//            this.player2Name.setText(this.user2.getUsername());
//            this.winLabelP1.setText("Wins: " + this.user1.getPlayerProfile().getConnectFourProfile().getTotalWins());
//            this.winLabelP2.setText("Wins: " + this.user2.getPlayerProfile().getConnectFourProfile().getTotalWins());
//            this.rankLabelP1.setText("Rank: " + this.user1.getPlayerProfile().getConnectFourProfile().getScoreRank());
//            this.rankLabelP2.setText("Rank: "+ this.user2.getPlayerProfile().getConnectFourProfile().getScoreRank());
//            this.setup = true;
//        }
//    }
//
//    public void makeMove(int[] coordsArr, Pane newPane) throws IOException {
//        this.gameConnect4.makeMove(coordsArr); // gets x coordinate and drops into that column
//        this.gameConnect4.switchCurrentPlayer();
//        // making move appear on screen
//        if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
//            newPane.getChildren().add(new Label(" RED "));
//            this.infoLabel.setText(this.gameConnect4.getCurrentPlayer() + "'s move!");
//        } else {
//            newPane.getChildren().add(new Label(" YELLOW "));
//            this.infoLabel.setText(this.gameConnect4.getCurrentPlayer() + "'s move!");
//        }
//
//        if(this.gameConnect4.validateGameEnds().equals(AbstractBoardGame.GameEndState.Draw)){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("This Game Has Reached A Stalemate");
//            alert.setTitle("Draw!");
//            exitBtnFunc();
//
//        } else if(this.gameConnect4.validateGameEnds().equals(AbstractBoardGame.GameEndState.Victory)){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("You Won!");
//            alert.setTitle("Winner!");
//            exitBtnFunc();
//
//        }
//    }
//
    public void exitBtnFunc() throws IOException { // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Connect_4_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Connect-4");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    public void sendBtnFunc(){
        String chatTxt = "";
        if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
            chatTxt += this.user1.getUsername() + ": ";
        }else{
            chatTxt += this.user2.getUsername() + ": ";
        }

        chatTxt += this.chatTxtField.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }
}
