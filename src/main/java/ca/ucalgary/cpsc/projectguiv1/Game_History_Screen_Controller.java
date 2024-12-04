package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import authProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


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


    private User currUsr = HelloApplication.usrDb.getCurrentUser();

    public Game_History_Screen_Controller(){
        setMatchHistory();
    }

    public void setMatchHistory(){
        this.mostRecentData.setText(this.currUsr.getPlayerProfile().displayGameHistory());

    }
    public String matchToString(User theUsr){

        return "";
    }
}

