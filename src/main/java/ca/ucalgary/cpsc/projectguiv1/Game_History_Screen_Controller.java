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
    private Button back;

    @FXML
    private BorderPane eighthMostRecent;

    @FXML
    Text eighthMostRecentData;

    @FXML
    private Text eighthMostRecentWL;

    @FXML
    private BorderPane fifthMostRecent;

    @FXML
    private Text fifthMostRecentData;

    @FXML
    private Text fifthMostRecentWL;

    @FXML
    private BorderPane fourthMostRecent;

    @FXML
    private Text fourthMostRecentData;

    @FXML
    private BorderPane mostRecent;

    @FXML
    private Text mostRecentData;

    @FXML
    private Text mostRecentWL;

    @FXML
    private BorderPane ninthMostRecent;

    @FXML
    private Text ninthMostRecentData;

    @FXML
    private Text ninthMostRecentWL;

    @FXML
    private Button profile;

    @FXML
    private BorderPane secondMostRecent;

    @FXML
    private Text secondMostRecentData;

    @FXML
    private Text secondMostRecentWL;

    @FXML
    private BorderPane seventhMostRecent;

    @FXML
    private Text seventhMostRecentData;

    @FXML
    private Text seventhMostRecentWL;

    @FXML
    private BorderPane sixthMostRecent;

    @FXML
    private Text sixthMostRecentData;

    @FXML
    private Text sixthMostRecentWL;

    @FXML
    private BorderPane thirdMostRecent;

    @FXML
    private Text thirdMostRecentData;

    @FXML
    private Text thirdMostRecentWL;

    @FXML
    private AnchorPane identity;


    private User currUsr = HelloApplication.usrDb.getCurrentUser();


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


    public Game_History_Screen_Controller(){
        setMatchHistory();
    }

    public void setMatchHistory(){
        this.mostRecentData.setText(this.currUsr.getPlayerProfile().displayGameHistory());

    }
    public String matchToString(User theUsr){

        return "";
    }

    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }
}

