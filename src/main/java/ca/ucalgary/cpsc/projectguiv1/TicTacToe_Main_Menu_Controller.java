package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TicTacToe_Main_Menu_Controller {

    @FXML
    AnchorPane identity;

    @FXML
    Label mainTitle;

    @FXML
    Button playOnline;

    @FXML
    Button leaderboard;

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

    // Leaderboard code:
    public void getLeaderboard(){


    }

    // Game History:
    public void getGameHistory(){


    }

    // Go to Profile


}
