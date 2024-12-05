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
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;


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

    private boolean oneAlert = false;

    private TicTacToeLeaderboard tttl;

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

    @FXML
    Label xTxtLbl;

    @FXML
    Label oTxtLbl;



    @Deprecated
    public TicTacToeGameController() {
        this.usrOne = HelloApplication.usrDb.getCurrentUser();
        //this.usrTwo = new User("SndUsr", "otheremail@google.com", "passwd");
        this.usrTwo = HelloApplication.usrDb.searchByUsername("SndUsr");
        if(this.usrTwo == null){
            this.usrTwo = new User("SndUsr", "snd@user", "pass");
        }

        HelloApplication.usrDb.addUser(this.usrTwo);
        this.tttl = TicTacToeLeaderboard.getInstance();


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
        this.xTxtLbl.setTextFill(Color.BLUE);
        this.xTxtLbl.setFont(new Font("Comic Sans", 100));
        this.xTxtLbl.setAlignment(Pos.TOP_CENTER);
        this.xTxtLbl.setText("X");

        this.oTxtLbl.setTextFill(Color.RED);
        this.oTxtLbl.setFont(new Font("Comic Sans", 100));
        this.oTxtLbl.setText("O  ");


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
                            System.out.println("io exception on setup");
                        }
                    });
                }
            }
            Random randInt = new Random();
            this.gameTicTacToe.setCurrentPlayer(randInt.nextInt(2));
            if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
                this.infoLabel.setFont(new Font("Comic Sans", 20));
                this.infoLabel.setText("Click to start!");

            } else {
                this.infoLabel.setFont(new Font("Comic Sans", 20));
                this.infoLabel.setText("Click to start!");
            }
            this.player1Name.setText(this.usrOne.getUsername());
            this.player2Name.setText(this.usrTwo.getUsername());
            this.winLabelP1.setText("Wins: " + this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins());
            this.winLabelP2.setText("Wins: " + this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins());

            if(this.tttl.getPlayerRank(this.usrOne.getUsername()) != -1) {
                this.rankLabelP1.setText("Rank: " + this.tttl.getPlayerRank(this.usrOne.getUsername()));
            }else{
                this.rankLabelP1.setText("Rank: 0");
            }
            if(this.tttl.getPlayerRank(this.usrOne.getUsername()) != -1) {
                this.rankLabelP2.setText("Rank: " + this.tttl.getPlayerRank(this.usrTwo.getUsername()));
            }else{
                this.rankLabelP2.setText("Rank: 0");
            }


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
                checkEndCon();
            } else {
                makeRandMove();
                checkEndCon();
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

    public void makeRandMove() {

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

    public void checkEndCon() {
        if(this.oneAlert == false) {
            if (this.gameTicTacToe.validateGameEnds() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("X's Win!");
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
            } else if (this.gameTicTacToe.validateGameEnds() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("O's Win!");
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
            } else if (this.gameTicTacToe.validateGameEnds() == 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Draw!");
                alert.setHeaderText("This Game Has Reached A Stalemate");
                alert.show();

                alert.setOnHidden(dialogEvent -> {
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
    public void saveEndData(char result, int player){ // Might have to change
        this.usrTwo = HelloApplication.usrDb.searchByUsername(this.usrTwo.getUsername());

        if((result == 'W') && (player == 1)) {
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrOne.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrOne.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateGameHistory(this.usrTwo.getUsername(), "W", 1);
            this.tttl.recordWin(this.usrOne.getUsername());
            this.tttl.recordLoss(this.usrTwo.getUsername());

        }else if ((result == 'W') && (player == 2)){
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrTwo.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateGameHistory(this.usrOne.getUsername(), "W", 1);
            this.tttl.recordWin(this.usrTwo.getUsername());
            this.tttl.recordLoss(this.usrOne.getUsername());

        }

    }
}




