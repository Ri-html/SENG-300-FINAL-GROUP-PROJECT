package ca.ucalgary.cpsc.projectguiv1;


import UserAndProfile.TicTacToeProfile;
import UserAndProfile.User;

import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGameController {
    private User usrOne;
    private User usrTwo;

    private TicTacToe gameTicTacToe;

    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    private int amtOfMoves = 0;

    private boolean allFilled = false;

    @FXML
    Pane identity;
    @FXML
    Button exitBtn;

    @FXML
    Button sendBtn;

    @FXML
    TextField chatTxtFld;

    @FXML
    VBox chatBox;

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
    GridPane gamePane;

    @FXML
    Label infoLabel;

    @FXML
    ScrollPane chatScrlPane;

    @Deprecated
    public TicTacToeGameController() {
        this.usrOne = new User("1", "firsstUsr", "email@google.com");
        this.usrTwo = new User("2", "scndUsr", "otheremail@google.com");
        HelloApplication.usrDb.addUser(this.usrOne);

        this.gameTicTacToe = new TicTacToe(2);
        this.gameTicTacToe.addPlayer(this.usrOne.getUsername());
        this.gameTicTacToe.addPlayer(this.usrTwo.getUsername());
    }


    public void exitBtnFunc() throws IOException { // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tic_Tac_Toe_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Tic-Tac-Toe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    public void setupGrid() {
        // Adding clickable panes to the grid
        if (this.setup == false) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    Pane currPane = new Pane();
                    int xCoord = x;
                    int yCoord = y;
                    this.gamePane.add(currPane, y, x);
                    arrOfPanes.add(currPane);
                    arrOfPaneCoords.add(y + " " + x);
                    currPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, yCoord};
                        try {
                            makeMove(coordsArr, currPane);
                        } catch (IOException ioe) {
                            System.out.println("io exception");
                        }
                    });
                }
            }
            Random randInt = new Random();
            this.gameTicTacToe.setCurrentPlayer(randInt.nextInt(2));
            if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
                //this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
                this.infoLabel.setFont(new Font("Comic Sans", 20));
                this.infoLabel.setText("Click to start!");

            } else {
                //this.infoLabel.setText(this.usrTwo.getUsername() + "'s move!");
                this.infoLabel.setText("Click to start!");
            }
            this.player1Name.setText(this.usrOne.getUsername());
            this.player2Name.setText(this.usrTwo.getUsername());
            this.winLabelP1.setText("Win: " + this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins());
            this.winLabelP2.setText("Win: " + this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins());
            this.rankLabelP1.setText("Rank: " + this.usrOne.getPlayerProfile().getTicTacToeProfile().getScoreRank());
            this.rankLabelP2.setText("Rank: " + this.usrTwo.getPlayerProfile().getTicTacToeProfile().getScoreRank());
            this.setup = true;
        }

    }

    public void makeMove(int[] coordsArr, Pane currPane) throws IOException {
        try {
            if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
                this.gameTicTacToe.makeMove(coordsArr);
                this.amtOfMoves++;
                this.gameTicTacToe.switchCurrentPlayer();

                Label xLbl = new Label();
                xLbl.setTextFill(Color.BLUE);
                xLbl.setFont(new Font("Comic Sans", 100));
                xLbl.setText("  X");
                xLbl.setAlignment(Pos.CENTER);
                currPane.getChildren().add(xLbl);

                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
                checkEndCon();
                makeRandMove();
            } else {
                makeRandMove();
            }

        } catch (IllegalArgumentException iae) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tile Already Occupied");
            alert.show();

        }
    }

    public void sendBtnFunc() {
        String chatTxt = "";
        if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
            chatTxt += this.usrOne.getUsername() + ": ";
        } else {
            chatTxt += this.usrTwo.getUsername() + ": ";
        }
        chatTxt += this.chatTxtFld.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }

    public void makeRandMove() throws IOException {

        Random rand = new Random();
        int idx = rand.nextInt(9);
        Pane randPane = this.arrOfPanes.get(idx);
        String randCoords = this.arrOfPaneCoords.get(idx);
        Scanner sc = new Scanner(randCoords);
        int randXCoord = sc.nextInt();
        int randYCoord = sc.nextInt();
        int[] rCoordArr = {randYCoord, randXCoord};
        boolean foundSpot = false;
        if(this.amtOfMoves >= 9){
            allFilled = true;
        }

        while ((foundSpot == false) && (allFilled == false)) {
            try {
                this.gameTicTacToe.makeMove(rCoordArr);
                this.gameTicTacToe.switchCurrentPlayer();
                Label oLbl = new Label();
                oLbl.setTextFill(Color.RED);
                oLbl.setFont(new Font("Comic Sans", 100));
                oLbl.setText("  O");
                oLbl.setAlignment(Pos.CENTER);
                randPane.getChildren().add(oLbl);
                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
                checkEndCon();
                this.amtOfMoves++;
                foundSpot = true;
            } catch (IllegalArgumentException iae) {
                idx = rand.nextInt(9);
                randPane = this.arrOfPanes.get(idx);
                randCoords = this.arrOfPaneCoords.get(idx);
                sc = new Scanner(randCoords);
                randXCoord = sc.nextInt();
                randYCoord = sc.nextInt();
                rCoordArr[1] = randXCoord;
                rCoordArr[0] = randYCoord;
            }

        }

    }
    @Deprecated
    public void checkEndCon() throws IOException{ // game logic needs to add more end game states
//        if (this.gameTicTacToe.validateGameEnds().equals(AbstractBoardGame.GameEndState.Victory)) {
//            exitBtnFunc();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Winner!");
//            alert.setHeaderText("You Won!");
//            alert.show();
//            saveEndData('W');
//        } else if (this.gameTicTacToe.validateGameEnds().equals(AbstractBoardGame.GameEndState.Draw)) {
//            exitBtnFunc();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Draw!");
//            alert.setHeaderText("This Game Has Reached A Stalemate");
//            alert.show();
//        }
    }
    public void saveEndData(char result){ // Might have to change
        TicTacToeProfile profOne = new TicTacToeProfile();
        if(result == 'W') {
            profOne.updateGameHistory(this.usrOne.getUsername(), "W", 1);
        }else if (result == 'L'){
            profOne.updateGameHistory(this.usrOne.getUsername(), "L", 1);
        }
    }
}




