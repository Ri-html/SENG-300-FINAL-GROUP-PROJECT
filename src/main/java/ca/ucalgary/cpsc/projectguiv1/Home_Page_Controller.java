package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Home_Page_Controller implements Initializable{
    @FXML
    GridPane identity;

    @FXML
    Button gameHistory;
    @FXML
    Label mainTitle;

    @FXML
    Button chessSelection;

    @FXML
    Button tictactoeSelection;

    @FXML
    Button connect4Selection;

    @FXML
    Label rankingChessLbl;

    @FXML
    Label rankingTicTacToeLbl;

    @FXML
    Label rankingConnect4Lbl;

    @FXML
    Button searchPlayersBtn;

//    @FXML
//    ListView<String> listView;
//    private User user1;
//
//    public Home_Page_Controller(){
//        user1 = new User("1", "john", "john@email.com");
//    }


    public void selectionFxn(String file, String title) throws IOException { // Switch to sign up page
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

    public void chessSelectionFxn() throws IOException {
        String file = "Chess_Main_Menu_View.fxml";
        selectionFxn(file, "Chess Main Menu");
    }

    public void ticTacToeSelectionFxn() throws IOException {
        String file = "Tic_Tac_Toe_Main_Menu_View.fxml";
        selectionFxn(file, "Tic-Tac-Toe Main Menu");
    }

    public void connect4SelectionFxn() throws IOException {
        String file = "Connect_4_Main_Menu_View.fxml";
        selectionFxn(file, "Connect 4 Main Menu");
    }

    public void gameHistorySelectionFxn() throws IOException {
        String file = "Game_History_Screen.fxml";
        selectionFxn(file, "Game History");
    }

    public void searchPlayersFunc() throws IOException {
        String file = "Search_for_Players.fxml";
        selectionFxn(file, "Search for Players");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDatabase db = UserDatabase.getInstance();
        User currUser = db.getCurrentUser();
        this.rankingTicTacToeLbl.setText("Ranking: " + currUser.getPlayerProfile().getTicTacToeProfile().getScoreRank());
        this.rankingChessLbl.setText("Ranking: " + currUser.getPlayerProfile().getChessProfile().getScoreRank());
        this.rankingConnect4Lbl.setText("Ranking: " + currUser.getPlayerProfile().getConnectFourProfile().getScoreRank());
    }
}
