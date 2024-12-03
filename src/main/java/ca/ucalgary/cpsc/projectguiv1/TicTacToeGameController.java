package ca.ucalgary.cpsc.projectguiv1;


import authProfile.User;

import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
            } else {
                this.infoLabel.setText(this.usrTwo.getUsername() + "'s move!");
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
                this.gameTicTacToe.switchCurrentPlayer();
                currPane.getChildren().add(new Label("              X")); // Later add an image or something
                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
            } else {

                Random rand = new Random();
                int idx = rand.nextInt(9);
                Pane randPane = this.arrOfPanes.get(idx);
                String randCoords = this.arrOfPaneCoords.get(idx);
                Scanner sc = new Scanner(randCoords);
                int randXCoord = sc.nextInt();
                int randYCoord = sc.nextInt();
                int[] rCoordArr = {randYCoord, randXCoord};
                boolean foundSpot = false;

                while (!foundSpot) {
                    try {
                        this.gameTicTacToe.makeMove(rCoordArr);
                        this.gameTicTacToe.switchCurrentPlayer();
                        randPane.getChildren().add(new Label("              O")); // Later add an image or something
                        this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");

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

            if (this.gameTicTacToe.validateGameEnds().equals(AbstractBoardGame.GameEndState.Victory)) {
                exitBtnFunc();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("You Won!");
                alert.show();

            } else if (this.gameTicTacToe.validateGameEnds().equals(AbstractBoardGame.GameEndState.Draw)) {
                exitBtnFunc();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Draw!");
                alert.setHeaderText("This Game Has Reached A Stalemate");
                alert.show();
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


}
