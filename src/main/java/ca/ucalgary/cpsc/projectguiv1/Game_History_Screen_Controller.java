package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import authProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Game_History_Screen_Controller {

    @FXML
    Button back;

    @FXML
    Button profile;

    @FXML
    BorderPane mostRecent;

    @FXML
    BorderPane secondMostRecent;

    @FXML
    BorderPane thirdMostRecent;

    @FXML
    BorderPane fourthMostRecent;

    @FXML
    BorderPane fifthMostRecent;

    @FXML
    BorderPane sixthMostRecent;

    @FXML
    BorderPane seventhMostRecent;

    @FXML
    BorderPane eighthMostRecent;

    @FXML
    BorderPane ninthMostRecent;


    private User currUsr = HelloApplication.usrDb.getCurrentUser();;

    public Game_History_Screen_Controller(){
        setMatchHistory();
    }

    public void setMatchHistory(){
        //this.mostRecent.getChildren().add(matchToString(this.currUsr));
    }
    public String matchToString(User theUsr){

        return "";
    }
}

