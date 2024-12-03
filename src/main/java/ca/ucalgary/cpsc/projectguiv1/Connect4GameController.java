package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Connect4GameController {

    private User user1;
    private User user2;

    //private ConnectFour connect4game;

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
    ScrollPane chatScrlPane;

    @FXML
    VBox chatBox;

    @FXML
    TextField chatTxtField;

    @FXML
    Button sendBtn;


    @Deprecated
    public Connect4GameController(){

    }

    public void exitBtnFunc() throws IOException { // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Connect_4_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Tic-Tac-Toe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

//    public void sendBtnFunc(){
//        String chatTxt = "";
//        if(this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())){
//            chatTxt += this.usrOne.getUsername() + ": ";
//        }else{
//            chatTxt += this.usrTwo.getUsername() + ": ";
//        }
//
//        chatTxt += this.chatTxtFld.getText();
//        this.chatBox.getChildren().add(new Label(chatTxt));
//        this.chatScrlPane.setContent(this.chatBox);
//    }
}
