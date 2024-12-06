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
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;

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
    Button displayGameHistory;

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

    /**
     * Passes file name and title of chess main menu to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void chessSelectionFxn() throws IOException {
        String file = "Chess_Main_Menu_View.fxml";
        selectionFxn(file, "Chess Main Menu");
    }

    /**
     * Passes file name and title of tic-tac-toe main menu to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void ticTacToeSelectionFxn() throws IOException {
        String file = "Tic_Tac_Toe_Main_Menu_View.fxml";
        selectionFxn(file, "Tic-Tac-Toe Main Menu");
    }

    /**
     * Passes file name and title of connect 4 main menu to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void connect4SelectionFxn() throws IOException {
        String file = "Connect_4_Main_Menu_View.fxml";
        selectionFxn(file, "Connect 4 Main Menu");
    }

    /**
     * Passes file name and title of user's game history to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void gameHistorySelectionFxn() throws IOException {
        String file = "Game_History_Screen.fxml";
        selectionFxn(file, "Game History");
    }

    /**
     * Passes file name and title of search for players screen to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void searchPlayersFunc() throws IOException {
        String file = "Search_for_Players.fxml";
        selectionFxn(file, "Search for Players");
    }

    /**
     * Passes file name and title of manage user profile to loadFileFunc
     * @throws IOException if the file does not load properly
     */
    public void playerProfFunc() throws IOException {
        String file = "Manage_Profile.fxml";
        selectionFxn(file, "Manage Profile");
    }

    /**
     * Initializes the current ranking of the user in all games when the homepage screen is loaded.
     * @param url resolves relative paths for the root object
     * @param resourceBundle resources used to localize the root object
      */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDatabase db = UserDatabase.getInstance();
        User currUser = db.getCurrentUser();
        this.rankingTicTacToeLbl.setText("Ranking: " + currUser.getPlayerProfile().getTicTacToeProfile().getTotalScore());
        this.rankingChessLbl.setText("Ranking: " + currUser.getPlayerProfile().getChessProfile().getTotalScore());
        this.rankingConnect4Lbl.setText("Ranking: " + currUser.getPlayerProfile().getConnectFourProfile().getTotalScore());
    }
}
