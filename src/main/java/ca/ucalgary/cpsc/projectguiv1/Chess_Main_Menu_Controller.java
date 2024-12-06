package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Chess_Main_Menu_Controller {
    @FXML
    Button playOnline;

    @FXML
    Button leaderboard;

    @FXML
    Button gameHistory;

    @FXML
    Button exit;

    @FXML
    Button profile;

    @FXML
    ListView chatHistory;

    @FXML
    TextField chatInput;

    @FXML
    Button back;

    @FXML
    AnchorPane identity;

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

    /**
     * Opens the chess game screen
     * @throws IOException if the file does not load properly
     */
    public void playOnlineFunc() throws IOException {
        String file = "Chess_Game_Screen.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing Chess");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    //Navigate to the Leaderboard Page

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void leaderboardButtonFxn() throws IOException{
        String file = "Leaderboard.fxml";
        loadFileFunc(file, "Chess Leaderboard");
    }

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }

    /**
     * Passes file name and title to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void profileFunc() throws IOException {
        String file = "Manage_Profile.fxml";
        loadFileFunc(file, "Manage Profile");
    }
}
