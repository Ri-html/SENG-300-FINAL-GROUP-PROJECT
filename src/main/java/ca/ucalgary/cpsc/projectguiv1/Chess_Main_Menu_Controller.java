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

    public void loadFileFunc(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    // play online
    public void playOnlineFunc() throws IOException {
        String file = "Chess_Game_Screen.fxml";
        loadFileFunc(file, "Playing Chess");
    }

    //Navigate to the Leaderboard Page
    public void leaderboardButtonFxn() throws IOException{
        String file = "Leaderboard.fxml";
        loadFileFunc(file, "Chess Leaderboard");
    }

    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }

    public void profileFunc() throws IOException {
        String file = "Manage_Profile.fxml";
        loadFileFunc(file, "Manage Profile");
    }
}
