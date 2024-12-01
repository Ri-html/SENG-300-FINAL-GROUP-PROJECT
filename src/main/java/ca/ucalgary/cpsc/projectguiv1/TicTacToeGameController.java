package ca.ucalgary.cpsc.projectguiv1;

import authProfile.TicTacToeProfile;
import authProfile.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class TicTacToeGameController {
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
    public TicTacToeGameController(){ // This class

    }

    public TicTacToeGameController(User playerOne, User playerTwo){

    }


    public void exitBtnFunc() throws IOException{ // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tic_Tac_Toe_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Tic-Tac-Toe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();

    }
    private void makeMove(){

    }

}
