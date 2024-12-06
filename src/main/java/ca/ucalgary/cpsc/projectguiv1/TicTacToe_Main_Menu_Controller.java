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

    /**
     * Opens new screens, like leaderboard, exit and manage your profile from the main menu.
     * @param file the fxl file that is being loaded onto the screen
     * @param title the title of the screen
     * @throws IOException if the file does not load properly
     */
    public void loadFileFunc(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(),800,500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void leaderboardButtonFxn() throws IOException{
        String file = "Leaderboard.fxml"; //Navigate to the Leaderboard Page
        loadFileFunc(file, "Tic-Tac-Toe Leaderboard");
    }

    /**
     * Opens the tic tac toe game screen
     * @throws IOException if the file does not load properly
     */
    public void playOnlineFunc() throws IOException { // Play the game
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TicTacToe_Game_Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),900,700);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing TicTacToe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml"; // Exit to homepage
        loadFileFunc(file, "Homepage");
    }

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void profileFunc() throws IOException {
        String file = "Manage_Profile.fxml"; // Go to profile
        loadFileFunc(file, "Manage Profile");
    }

}
