package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import gameLogic.Chess;
import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.chessPiece.ChessPieceDisplay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class ChessGameController {

    Color p1Color=Color.RED;
    Color p2Color=Color.BLUE;
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

    protected User user1= new User("1", "firsstUsr", "email@google.com");

    protected User user2= new User("2", "scndUsr", "otheremail@google.com");



    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    public ChessGameController() {
        currentPlayer = user1.getUsername();
    }

    @FXML
    public void initialize() {
        game=new Chess();
        game.addPlayer(user1.getUsername());
        game.addPlayer(user2.getUsername());
        player1Name.setText(user1.getUsername());
        player2Name.setText(user2.getUsername());
        rankLabelP1.setText(String.valueOf(user1.getPlayerProfile().getChessProfile().getScoreRank()));
        rankLabelP2.setText(String.valueOf(user2.getPlayerProfile().getChessProfile().getScoreRank()));
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
        if (game.getCurrentPlayer().equals(user1.getUsername())) {
            chatTxt += user1.getUsername() + ": ";
        } else {
            chatTxt += user2.getUsername() + ": ";
        }
        chatTxt += chatTxtField.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }

    public void setUpBoard() {
        int size=50;
        if(this.setup == false){
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    Pane currPane = new Pane();
                    int xCoord = x;
                    int yCoord = y;
                    if (yCoord==0){
                        if (xCoord==0){
                            //set pieces in the first row
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==1) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==2) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==3) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.queen.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==4) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.king.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==5) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        }else if (xCoord==6) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==7) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p1Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);
                        }

                    } else if (yCoord==1) {
                        //set pieces in the second row
                        Label xLbl = new Label();
                        xLbl.setTextFill(p1Color);
                        xLbl.setFont(new Font("Comic Sans", size));
                        xLbl.setText(String.valueOf(ChessPieceDisplay.pawn.getSymbol()));
                        xLbl.setAlignment(Pos.CENTER);
                        currPane.getChildren().add(xLbl);

                    } else if (yCoord==6) {
                        //set pieces in the seventh row
                        Label xLbl = new Label();
                        xLbl.setTextFill(p2Color);
                        xLbl.setFont(new Font("Comic Sans", size));
                        xLbl.setText(String.valueOf(ChessPieceDisplay.pawn.getSymbol()));
                        xLbl.setAlignment(Pos.CENTER);
                        currPane.getChildren().add(xLbl);

                    } else if (yCoord==7) {
                        //set pieces in the eighth row
                        if (xCoord==0){
                            //set pieces in the first row
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.rook.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==1) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==2) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==3) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.queen.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==4) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.king.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==5) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.bishop.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        }else if (xCoord==6) {
                            Label xLbl = new Label();
                            xLbl.setTextFill(p2Color);
                            xLbl.setFont(new Font("Comic Sans", size));
                            xLbl.setText(String.valueOf(ChessPieceDisplay.knight.getSymbol()));
                            xLbl.setAlignment(Pos.CENTER);
                            currPane.getChildren().add(xLbl);

                        } else if (xCoord==7) {
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

    public void makeMove(int[] coordsArr, Pane currPane) throws IOException{
        if (currPane.getChildren()==null){
            //this skips the else ifs
        }else if (currentPlayer.equals(player1Name.getText())){
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p1Color== xLbl.getTextFill()){
                highlightPane(currPane);
            }
        } else if (currentPlayer.equals(player2Name.getText())) {
            Label xLbl = (Label) currPane.getChildren().get(0);
            if (p2Color== xLbl.getTextFill()){
                highlightPane(currPane);
            }
        }
    }
    public void highlightPane(Pane currPane){
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLUE, null, null);
        Background background = new Background(backgroundFill);
        currPane.setBackground(background);
    }
}
