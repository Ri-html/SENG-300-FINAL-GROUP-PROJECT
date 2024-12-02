package ca.ucalgary.cpsc.projectguiv1;


import authProfile.User;

import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Random;

public class TicTacToeGameController {
    private User usrOne;
    private User usrTwo;

    private TicTacToe gameTicTacToe;

    private boolean setup = false;

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

    @Deprecated
    public TicTacToeGameController() {
        this.usrOne = new User("1", "firsstUsr", "email@google.com");
        this.usrTwo = new User("2", "scndUsr", "email@google.com");

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
                    currPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, yCoord};
                        try {
                            makeMove(coordsArr, currPane);
                        }catch (IOException ioe){
                            System.out.println("io exception");
                        }
                    });
                }
            }
            Random randInt = new Random();
            this.gameTicTacToe.setCurrentPlayer(randInt.nextInt(2));

            this.setup = true;
        }

    }

    public void makeMove(int[] coordsArr, Pane currPane) throws IOException{
        try {
            this.gameTicTacToe.makeMove(coordsArr);
            this.gameTicTacToe.switchCurrentPlayer();

            if(this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())){
                currPane.getChildren().add(new Label("              X")); // Later add an image
            }else{
                currPane.getChildren().add(new Label("              O"));
            }
            System.out.println(this.gameTicTacToe.validateGameEnds());
            if(this.gameTicTacToe.validateGameEnds().equals(AbstractBoardGame.GameEndState.Victory)){
                exitBtnFunc();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("You Won");
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

        System.out.println(this.gameTicTacToe.toString());
    }


}
