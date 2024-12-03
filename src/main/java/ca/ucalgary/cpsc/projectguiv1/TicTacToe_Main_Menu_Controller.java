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

    public void loadFileFunc(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(),600,800);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    //Navigate to the Leaderboard Page
    public void leaderboardButtonFxn() throws IOException{
        String file = "Leaderboard.fxml";
        loadFileFunc(file, "Tic-Tac-Toe Leaderboard");
    }

    // Play the game
    public void playOnlineFunc() throws IOException {
        String file = "TicTacToe_Game_Screen.fxml";
        loadFileFunc(file, "Playing Tic-Tac-Toe!");
    }

    // Exit to homepage
    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }

    // Go to profile
    public void profileFunc() throws IOException {
        String file = "Manage_Profile.fxml";
        loadFileFunc(file, "Manage Profile");
    }

}
