
package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.GameRecord;
import UserAndProfile.User;
import gameLogic.Chess;
import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.boardGames.Game;
import gameLogic.piece.chessPiece.ChessPieceDisplay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessGameController implements BoardGameObserver{

    private User usrOne;
    private User usrTwo;
    private ChessLeaderboard cl;
    private Chess gameChess;
    private boolean oneAlert = false;
    int[] origin=null;
    int[] destination=null;
    Pane oldPane;
    Pane newPane;
    Label someLabel;
    boolean exception=false;

    Color p1Color = Color.RED;
    Color p2Color = Color.BLUE;
    //Chat related properties
    @FXML
    ScrollPane chatScrlPane;

    @FXML
    VBox chatBox;

    @FXML
    TextField chatTxtField;

    @FXML
    Button sendBtn;

    @FXML
    Pane identity;

    @FXML
    GridPane gamePane;

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
    TextArea afterlife1;

    @FXML
    TextArea afterlife2;

    AbstractBoardGame game;

    protected String currentPlayer;


    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    public static String otherPlayersName;

    /**
     * Sets up board and gui
     */
    @FXML
    public void initialize() {
        this.usrOne = HelloApplication.usrDb.getCurrentUser();
        this.currentPlayer = this.usrOne.getUsername();
        this.usrTwo = HelloApplication.usrDb.searchByUsername("SndUsr");
        if (this.usrTwo == null) {
            this.usrTwo = new User("SndUsr", "snd@user", "pass");
        }

        if(otherPlayersName != null){ // match with a specific user
            this.usrTwo = HelloApplication.usrDb.searchByUsername(otherPlayersName);
        }

        HelloApplication.usrDb.addUser(this.usrTwo);
        this.cl = ChessLeaderboard.getInstance();

        /*
        this.gameChess = new Chess();
        this.gameChess.addPlayer(this.usrOne.getUsername());
        this.gameChess.addPlayer(this.usrTwo.getUsername());
        */

        game = new Chess();
        game.addPlayer(usrOne.getUsername());
        game.addPlayer(usrTwo.getUsername());
        game.attachGameEndObserver(this);
        game.attachTurnEndObserver(this);
        game.attachInvalidMoveObserver(this);

        player1Name.setText(usrOne.getUsername());
        player2Name.setText(usrTwo.getUsername());
        rankLabelP1.setText(String.valueOf(usrOne.getPlayerProfile().getChessProfile().getScoreRank()));
        rankLabelP2.setText(String.valueOf(usrTwo.getPlayerProfile().getChessProfile().getScoreRank()));
        afterlife1.setStyle("-fx-text-fill:brown;");
        afterlife2.setStyle("-fx-text-fill :black;");
        setUpBoard();
    }

    /**
     * return to chess game menu
     * @throws IOException incase the return fails
     */
    @FXML
    public void exitBtnFunc() throws IOException { // Switch to chess main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Chess_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Chess Game");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * send messages to other player
     */
    public void sendBtnFunc() {
        String chatTxt = "";
        if (game.getCurrentPlayer().equals(usrOne.getUsername())) {
            chatTxt += usrOne.getUsername() + ": ";
        } else {
            chatTxt += usrTwo.getUsername() + ": ";
        }
        chatTxt += chatTxtField.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }

    /**
     * sets up the chess pieces on the board
     */
    public void setUpBoard() {
        int size = 50;
        if (this.setup == false) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Pane currPane = new Pane();
                    currPane.setAccessibleHelp("toRemove");
                    int xCoord = x;
                    int yCoord = y;
                    if (yCoord == 0) {
                        if (xCoord == 0) {
                            //set pieces in the first row
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 1) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 2) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 3) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.queen.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 4) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.king.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 5) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 6) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 7) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);
                        }

                    } else if (yCoord == 1) {
                        //set pieces in the second row
                        Label xLbl = new Label();
                        xLbl.setTextFill(p1Color);
                        xLbl.setFont(new Font("Comic Sans", size));
                        xLbl.setText(String.valueOf(ChessPieceDisplay.pawn.getSymbol()));
                        xLbl.setAlignment(Pos.CENTER);
                        currPane.getChildren().add(xLbl);

                    } else if (yCoord == 6) {
                        //set pieces in the seventh row
                        Label xLbl = new Label();
                        xLbl.setTextFill(p2Color);
                        xLbl.setFont(new Font("Comic Sans", size));
                        xLbl.setText(String.valueOf(ChessPieceDisplay.pawn.getSymbol()));
                        xLbl.setAlignment(Pos.CENTER);
                        currPane.getChildren().add(xLbl);

                    } else if (yCoord == 7) {
                        //set pieces in the eighth row
                        if (xCoord == 0) {
                            //set pieces in the first row
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 1) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 2) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 3) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.queen.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 4) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.king.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 5) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 6) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord == 7) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);
                        }
                    }

                    this.gamePane.add(currPane, xCoord, yCoord);
                    arrOfPanes.add(currPane);
                    arrOfPaneCoords.add(y + " " + x);
                    currPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, xCoord};
                        try {
                            makeMove(coordsArr, currPane);
                        } catch (IOException ioe) {
                            System.out.println("io exception");
                        }
                    });
                }
            }

            this.setup = true;
        }
    }

    /**
     * makes the move
     * @param coordsArr array containing the coordinate of the pieces
     * @param currPane the pane that implements this onclick function
     * @throws IOException  throws an exception
     */
    public void makeMove(int[] coordsArr, Pane currPane) throws IOException {
            //used to skip the rest if current pane is empty
        if (currPane.getChildren().isEmpty()&& origin==null) {

            //if the cell is empty and is the second click
        }else if (currPane.getChildren().isEmpty()&& origin!=null) {
                dehighlightPane(currPane);
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                destination = new int[]{location1, location2};
                System.out.println(destination[0] + " " + destination[1]);
                if (!Arrays.equals(origin,destination)) {
                    String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                    game.updateMove(moves);
                    System.out.println(currentPlayer);
                    if (!exception){
                        togglePlayer();
                        moveLabel();
                    }else{
                        exception=false;
                    }
                }
                origin =null;
                destination = null;

          //if player one moves and is the first click
        } else if (currentPlayer.equals(player1Name.getText()) && origin==null) {
            Label xLbl = (Label) currPane.getChildren().getFirst();

            //check if the colour is correct
            if (p1Color== xLbl.getTextFill()) {
                highlightPane(currPane);
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                origin = new int[]{location1, location2};
                oldPane=currPane;
                System.out.println(origin[0] + " " + origin[1]);
            }

            //if the cell contains player two piece and is the first click
        } else if (currentPlayer.equals(player2Name.getText()) && origin==null) {
            Label xLbl = (Label) currPane.getChildren().getFirst();
            if (p2Color == xLbl.getTextFill()) {
                highlightPane(currPane);
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                origin = new int[]{location1, location2};
                oldPane=currPane;
                System.out.println(origin[0] + " " + origin[1]);
            }

            //if current player is Player1 and is the second click would only trigger effect when it clicks another player
        } else if (currentPlayer.equals(player1Name.getText()) && origin!=null) {
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p2Color == xLbl.getTextFill()) {
                dehighlightPane(currPane);
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                destination = new int[]{location1, location2};
                System.out.println(destination[0] + " " + destination[1]);

                //check to see if it is clicking on the same cell
                if (!Arrays.equals(origin,destination)) {
                    String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                    game.updateMove(moves);
                    if (!exception){
                        String text=afterlife2.getText();
                        Label label=(Label)currPane.getChildren().getFirst();
                        text+=label.getText()+"\n";
                        afterlife2.setText(text);
                        currPane.getChildren().clear();
                        togglePlayer();
                        moveLabel();
                    }
                }
                origin = null;
                destination = null;
            }else{
                dehighlightPane(currPane);
                origin = null;
                destination = null;
            }
            //if current player is player two and is the second click on a player one piece
        } else if (currentPlayer.equals(player2Name.getText()) && origin!=null) {
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p1Color == xLbl.getTextFill()) {
                //dehighlight the pane the set the current pane as destination
                dehighlightPane(currPane);
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                destination = new int[]{location1, location2};
                System.out.println(destination[0] + " " + destination[1]);

                //check to see if it is clicking on the same cell and perform the move if not
                if (!Arrays.equals(origin,destination)) {
                    String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                    game.updateMove(moves);
                    if (!exception){
                        String text=afterlife1.getText();
                        Label label=(Label)currPane.getChildren().getFirst();
                        text+=label.getText()+"\n";
                        afterlife1.setText(text);
                        currPane.getChildren().clear();
                        togglePlayer();
                        moveLabel();
                    }
                }
                origin = null;
                destination = null;
            }else{
                dehighlightPane(currPane);
                origin = null;
                destination = null;
            }
        }
    }

    /**
     * switches the current player
     */
    private void togglePlayer() {
        if (currentPlayer.equals(player1Name.getText())) {
            currentPlayer=player2Name.getText();
        }else {
            currentPlayer=player1Name.getText();
        }
    }

    /**
     * highlights the current pane
     * @param currPane the pane clicked
     */
    public void highlightPane(Pane currPane) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.YELLOW, null, null);
        Background background = new Background(backgroundFill);
        currPane.setBackground(background);
    }

    /**
     * check if end condition is met and perform different operations for win and tie
     */
    public void checkEndCon() {
        if(this.oneAlert == false) { // This is to make sure that only one pop-up, pops up
            if (this.game.validateGameEnds() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText(usrOne.getUsername() + " Wins!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 1);

                    // Create a confirmation dialog
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Rematch Request");
                    alert2.setHeaderText("Hi, the other player requested a rematch. Do you want to play?");

                    // Show the dialog and wait for user response
                    alert2.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            resetGame();
                        } else {
                            try {
                                exitBtnFunc(); // Once the game is declared over, quit the screen
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                });

                this.oneAlert = true;
            } else if (this.game.validateGameEnds() == 2) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText(usrTwo.getUsername() + " Wins!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 2);
                    // Create a confirmation dialog
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Rematch Request");
                    alert2.setHeaderText("Hi, the other player requested a rematch. Do you want to play?");

                    // Show the dialog and wait for user response
                    alert2.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            resetGame();
                        } else {
                            try {
                                exitBtnFunc(); // Once the game is declared over, quit the screen
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                });
                this.oneAlert = true;

            } else if (this.game.validateGameEnds() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Draw!");
                alert.setHeaderText("This Game Has Reached A Stalemate");
                alert.show();

                alert.setOnHidden(dialogEvent -> {
                    // Create a confirmation dialog
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Rematch Request");
                    alert2.setHeaderText("Hi, the other player requested a rematch. Do you want to play?");

                    // Show the dialog and wait for user response
                    alert2.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            resetGame();
                        } else {
                            try {
                                exitBtnFunc(); // Once the game is declared over, quit the screen
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                });
                this.oneAlert = true;
            }
        }
    }

    /**
     * Saves the data into a file
     * @param result   the result of the player
     * @param player    the player
     */
    public void saveEndData(char result, int player) {
        this.usrTwo = HelloApplication.usrDb.searchByUsername(this.usrTwo.getUsername());

        if ((result == 'W') && (player == 1)) {
            this.usrOne.getPlayerProfile().getChessProfile().setTotalWins(this.usrOne.getPlayerProfile().getChessProfile().getTotalWins() + 1);
            this.usrTwo.getPlayerProfile().getChessProfile().setTotalLosses(this.usrTwo.getPlayerProfile().getChessProfile().getTotalLosses() + 1);
            this.usrOne.getPlayerProfile().getChessProfile().updateRanking(this.usrOne.getPlayerProfile().getChessProfile().getScoreRank(), this.usrOne.getPlayerProfile().getChessProfile().getWinRateRank());
            this.usrOne.getPlayerProfile().getChessProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "W", 1);
            this.usrTwo.getPlayerProfile().getChessProfile().updateGameHistoryReal(this.usrOne.getUsername(), "L", -1);
            this.cl.recordWin(this.usrOne.getUsername());
            this.cl.recordLoss(this.usrTwo.getUsername());

            GameRecord gameRecord = new GameRecord("Chess", this.usrTwo.getUsername(), "W", this.usrOne.getPlayerProfile().getChessProfile().getTotalScore());
            this.usrOne.getPlayerProfile().getChessProfile().addGameRecord(gameRecord);

            GameRecord gameRecord2 = new GameRecord("Chess", this.usrOne.getUsername(), "L", this.usrTwo.getPlayerProfile().getChessProfile().getTotalScore());
            this.usrTwo.getPlayerProfile().getChessProfile().addGameRecord(gameRecord2);

        } else if ((result == 'W') && (player == 2)) {
            System.out.println("test2");
            this.usrTwo.getPlayerProfile().getChessProfile().setTotalWins(this.usrTwo.getPlayerProfile().getChessProfile().getTotalWins() + 1);
            this.usrOne.getPlayerProfile().getChessProfile().setTotalLosses(this.usrOne.getPlayerProfile().getChessProfile().getTotalLosses() + 1);
            this.usrTwo.getPlayerProfile().getChessProfile().updateRanking(this.usrTwo.getPlayerProfile().getChessProfile().getScoreRank(), this.usrTwo.getPlayerProfile().getChessProfile().getWinRateRank());
            this.usrTwo.getPlayerProfile().getChessProfile().updateGameHistoryReal(this.usrOne.getUsername(), "W", 1);
            this.usrOne.getPlayerProfile().getChessProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "L", -1);
            this.cl.recordWin(this.usrTwo.getUsername());
            this.cl.recordLoss(this.usrOne.getUsername());

            GameRecord gameRecord = new GameRecord("Chess", this.usrOne.getUsername(), "W", this.usrTwo.getPlayerProfile().getChessProfile().getTotalScore());
            this.usrTwo.getPlayerProfile().getChessProfile().addGameRecord(gameRecord);

            GameRecord gameRecord2 = new GameRecord("Chess", this.usrTwo.getUsername(), "L", this.usrOne.getPlayerProfile().getChessProfile().getTotalScore());
            this.usrOne.getPlayerProfile().getChessProfile().addGameRecord(gameRecord2);

        }
    }

    /**
     * unhighlight the previous pane clicked
     * @param currPane the current pane, and the second pane clicked
     */
        public void dehighlightPane(Pane currPane){
            oldPane.setBackground(null);
            newPane=currPane;
        }

    /**
     * move the label representing the chess piece
     */
    public void moveLabel(){
            Label label=(Label) oldPane.getChildren().get(0);
            oldPane.getChildren().remove(label);
            newPane.getChildren().add(label);
            Pane tempPane=oldPane;
            oldPane=newPane;
            newPane=tempPane;
        }

    /**
     * resets the game to initial state if both players agree to a rematch
     */
    public void resetGame(){
            gamePane.getChildren().removeIf(children -> children instanceof Pane);
            afterlife1.setText("");
            afterlife2.setText("");
            currentPlayer=usrOne.getUsername();
            oldPane=null;
            newPane=null;
            this.setup=false;
            this.oneAlert=false;
            initialize();
        }

    /**
     * This method updates this observer about the turn end condition, game end condition, and invalid moves
     * @param obj   a string object passed by the notify methods in abstractBoardGame
     */
    @Override
        public void update(String obj){
            String[] objs=obj.split("\n");
            switch (objs[0]){
                case "TurnEnd":
                    break;
                case "GameEnd":
                    checkEndCon();
                    break;
                case "InvalidMove":
                    exception=true;
                    break;
            }
        }
}