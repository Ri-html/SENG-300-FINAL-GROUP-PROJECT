package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe_Main_Menu_Controller {

    @FXML
    AnchorPane identity;

    @FXML
    Label mainTitle;

    @FXML
    Button playOnline;

    @FXML
    Button leaderboard; //leads to leaderboard page

    @FXML
    Button gameHistory;

    @FXML
    Button exit; // leads back to home page

    @FXML
    Button back;    // back and exit do the same thing here

    @FXML
    Button profile; // takes you to manage profile page

    @FXML
    ListView chatHistory;

    @FXML
    TextField chatInput;

    // Play online --> loads a new game and redirects to the game page for tic tac toe
    // Leaderboard --> loads leaderboard data and takes u to the leaderboard page
    // game history --> loads game history for this game (past 5 games?)
    // exit --> goes back to the homepage

    // Leaderboard code:
    public void getLeaderboard(){


    }

    //Navigate to the Leaderboard Page
    public void leaderboardButtonFxn() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Leaderboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,800);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Leaderboard");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    // Game History:
    public void getGameHistory(){


    }

    // Exit to homepage
    public void exitBtnFunc() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Homepage");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    // Go to profile
    public void profileFunc() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manage_Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Manage Profile");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

}
