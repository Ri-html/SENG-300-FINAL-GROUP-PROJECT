package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Game_History_Screen_Controller {


    @FXML
    private AnchorPane identity;

    @FXML
    private Text gameHistoryTxt;


    private User currUsr = HelloApplication.usrDb.getCurrentUser();


    /**
     * Opens new screens, like leaderboard, exit and manage your profile from the main menu.
     * @param file the fxl file that is being loaded onto the screen
     * @param title the title of the screen
     * @throws IOException if the file does not load properly
     */
    public void loadFileFunc(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }


    public Game_History_Screen_Controller(){
    }

    /**
     * Updates the string data for the game history upon clicking "Display Game History"
     */
    public void setMatchHistory(){
        if(this.currUsr != null) {
            this.gameHistoryTxt.setText(this.currUsr.getPlayerProfile().displayGameHistory());
        }

    }
    /**
     * Loads the player's profile screen and switches scenes to the profile
     * @throws IOException if the file/screen does not load properly
     */
    public void gotoPlayerProfFunc() throws IOException{
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

    /**
     * Passes in the Homepage file and title of the homepage window to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }
}

