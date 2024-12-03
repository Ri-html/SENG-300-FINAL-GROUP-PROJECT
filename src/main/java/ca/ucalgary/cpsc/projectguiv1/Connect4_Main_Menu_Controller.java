package ca.ucalgary.cpsc.projectguiv1;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Connect4_Main_Menu_Controller {
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


}
