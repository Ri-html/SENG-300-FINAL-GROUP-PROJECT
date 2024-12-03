package ca.ucalgary.cpsc.projectguiv1;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Leaderboard_Controller {
    @FXML
    private GridPane identity;

    @FXML
    private Button refresh_button;

    @FXML
    private Button back_button;

    @FXML
    private Button player_1st_button;

    @FXML
    private Button player_2nd_button;

    @FXML
    private Button player_3rd_button;

    @FXML
    private Button player_4th_button;

    @FXML
    private Button player_5th_button;

    @FXML
    private Button player_6th_button;

    @FXML
    private Button player_7th_button;

    @FXML
    private Button player_8th_button;

    @FXML
    private Button player_9th_button;

    @FXML
    private Button player_10th_button;

    @FXML
    private Button player_11th_button;

    @FXML
    private Button player_12th_button;

    @FXML
    private Button player_13th_button;

    public void loadFileFunc(String file, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(),600,600);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }

}
