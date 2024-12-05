package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import gameLogic.Chess;
import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.chessPiece.ChessPieceDisplay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    Pane tempPane;

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
    AnchorPane afterlife1;

    @FXML
    AnchorPane afterlife2;

    AbstractBoardGame game;

    protected String currentPlayer;


    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    @Deprecated
    public ChessGameController() {
        //this.usrOne = HelloApplication.usrDb.getCurrentUser();
        this.usrOne = new User("SndUs", "snd@user", "pass");
        this.currentPlayer = this.usrOne.getUsername();
        this.usrTwo = HelloApplication.usrDb.searchByUsername("SndUsr");
        if (this.usrTwo == null) {
            this.usrTwo = new User("SndUsr", "snd@user", "pass");
        }

        HelloApplication.usrDb.addUser(this.usrTwo);
        this.cl = ChessLeaderboard.getInstance();


        this.gameChess = new Chess();
        this.gameChess.addPlayer(this.usrOne.getUsername());
        this.gameChess.addPlayer(this.usrTwo.getUsername());
    }

    @FXML
    public void initialize() {
        game = new Chess();
        game.addPlayer(usrOne.getUsername());
        game.addPlayer(usrTwo.getUsername());
        player1Name.setText(usrOne.getUsername());
        player2Name.setText(usrTwo.getUsername());
        rankLabelP1.setText(String.valueOf(usrOne.getPlayerProfile().getChessProfile().getScoreRank()));
        rankLabelP2.setText(String.valueOf(usrTwo.getPlayerProfile().getChessProfile().getScoreRank()));
        setUpBoard();

    }

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


    @FXML
    public void handleClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        //int row = (int) (y / CELL_SIZE); // Calculate row index
        //int col = (int) (x / CELL_SIZE); // Calculate column index
    }

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

    public void setUpBoard() {
        int size = 50;
        if (this.setup == false) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Pane currPane = new Pane();
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

    public void makeMove(int[] coordsArr, Pane currPane) throws IOException {
        if (currPane.getChildren().isEmpty()) {
            //this skips the else ifs
            if (origin!=null){
                dehighlightPane();
                int location1 = gamePane.getRowIndex(currPane); // x position
                int location2 = gamePane.getColumnIndex(currPane); // y position
                destination = new int[]{location1, location2};
                System.out.println(destination[0] + " " + destination[1]);
                if (!Arrays.equals(origin,destination)) {
                    String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                    game.updateMove(moves);
                    togglePlayer();
                }
                origin =null;
                destination = null;

            }

        } else if (currentPlayer.equals(player1Name.getText())) {
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p1Color== xLbl.getTextFill()) {

                if (origin==null) {
                    highlightPane(currPane);
                    int location1 = gamePane.getRowIndex(currPane); // x position
                    int location2 = gamePane.getColumnIndex(currPane); // y position
                    origin = new int[]{location1, location2};
                    tempPane=currPane;
                    System.out.println(origin[0] + " " + origin[1]);
                } else {
                    dehighlightPane();
                    int location1 = gamePane.getRowIndex(currPane); // x position
                    int location2 = gamePane.getColumnIndex(currPane); // y position
                    destination = new int[]{location1, location2};
                    System.out.println(destination[0] + " " + destination[1]);
                    if (!Arrays.equals(origin,destination)) {
                        String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                        game.updateMove(moves);
                        togglePlayer();
                    }
                    origin = null;
                    destination = null;
                }
            }
        } else if (currentPlayer.equals(player2Name.getText())) {
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p2Color == xLbl.getTextFill()) {
                if (origin==null) {
                    highlightPane(currPane);
                    int location1 = gamePane.getRowIndex(currPane); // x position
                    int location2 = gamePane.getColumnIndex(currPane); // y position
                    origin = new int[]{location1, location2};
                    tempPane=currPane;
                    System.out.println(origin[0] + " " + origin[1]);
                } else {
                    dehighlightPane();
                    int location1 = gamePane.getRowIndex(currPane); // x position
                    int location2 = gamePane.getColumnIndex(currPane); // y position
                    destination = new int[]{location1, location2};
                    System.out.println(destination[0] + " " + destination[1]);
                    if (!Arrays.equals(origin,destination)) {
                        String moves = String.format("%s,%s,%s,%s",origin[0],origin[1],destination[0],destination[1]);
                        game.updateMove(moves);
                        togglePlayer();
                    }
                    origin = null;
                    destination = null;
                }
            }
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals(player1Name.getText())) {
            currentPlayer=player2Name.getText();
        }else {
            currentPlayer=player1Name.getText();
        }
    }
    public void highlightPane(Pane currPane) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.YELLOW, null, null);
        Background background = new Background(backgroundFill);
        currPane.setBackground(background);
    }


    public void checkEndCon() {
        if(this.oneAlert == false) { // This is to make sure that only one pop-up, pops up
            if (this.gameChess.validateGameEnds() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText(usrOne.getUsername() + "Wins!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 1);
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });

                this.oneAlert = true;
            } else if (this.gameChess.validateGameEnds() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText(usrTwo.getUsername() + "Win!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 2);
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });
                this.oneAlert = true;
            } else if (this.gameChess.validateGameEnds() == 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Draw!");
                alert.setHeaderText("This Game Has Reached A Stalemate");
                alert.show();

                alert.setOnHidden(dialogEvent -> {
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });
                this.oneAlert = true;
            }
        }
    }


    public void saveEndData(char result, int player) {
        this.usrTwo = HelloApplication.usrDb.searchByUsername(this.usrTwo.getUsername());

        if ((result == 'W') && (player == 1)) {
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrOne.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrOne.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "W", 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrOne.getUsername(), "L", -1);
            this.cl.recordWin(this.usrOne.getUsername());
            this.cl.recordLoss(this.usrTwo.getUsername());
            System.out.println("test1");

        } else if ((result == 'W') && (player == 2)) {
            System.out.println("test2");
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrTwo.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrOne.getUsername(), "W", 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "L", -1);
            this.cl.recordWin(this.usrTwo.getUsername());
            this.cl.recordLoss(this.usrOne.getUsername());

        }
    }
        public void dehighlightPane(){
            tempPane.setBackground(null);
            tempPane=null;
        }
        @Override
        public void update(String obj){
            String[] objs=obj.split(",");
            switch (objs[0]){
                case "TurnEnd":
                    break;
                case "GameEnd":
                    break;
            }
        }
}