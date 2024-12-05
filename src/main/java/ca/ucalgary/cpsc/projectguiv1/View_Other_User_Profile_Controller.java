package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class View_Other_User_Profile_Controller {

    private User user;

    // View Other User Profile Fields
    @FXML
    private TextArea tictactoeTxtArea;

    @FXML
    private TextArea chessTxtArea;

    @FXML
    private Text rankingChessLabel;

    @FXML
    private Text rankingTicTacToeLabel;

    @FXML
    private Text yourUsernameLabel;

    @FXML
    private Text yourRankingChessLabel;

    @FXML
    private Text yourRankingTicTacToeLabel;

    @FXML
    private Text usernameLabel;

    @FXML
    private AnchorPane identity;

    @FXML
    private Text yourWinRateChess;

    @FXML
    private Text yourWinRateTicTacToe;

    @FXML
    private Text currentStatusLabel;

    public void setUser(User user){
        this.user = user;
        loadInfoFromUser(this.user);
    }
    public void loadInfoFromUser(User user){
        UserDatabase db = UserDatabase.getInstance(); // current database
        User currUser = db.getCurrentUser();
        //Usernames
        this.usernameLabel.setText(user.getUsername());
        this.yourUsernameLabel.setText(currUser.getUsername());
        // Tic Tac Toe rankings
        this.rankingTicTacToeLabel.setText("Ranking: " + user.getPlayerProfile().getTicTacToeProfile().getScoreRank());
        this.yourRankingTicTacToeLabel.setText("Ranking: " + currUser.getPlayerProfile().getTicTacToeProfile().getScoreRank());

        // Chess rankings
        int result = user.getPlayerProfile().getChessProfile().getScoreRank();
        this.rankingChessLabel.setText("Ranking: " +  result);
        this.yourRankingChessLabel.setText("Ranking: " + db.getCurrentUser().getPlayerProfile().getChessProfile().getScoreRank());

        // Your Win Rate
        this.yourWinRateChess.setText("Wins: " + currUser.getPlayerProfile().getChessProfile().getWinRate());
        this.yourWinRateTicTacToe.setText("Wins: " + currUser.getPlayerProfile().getTicTacToeProfile().getWinRate());

        // Other user's current status
        this.currentStatusLabel.setText("Online");
    }

    public void backBtnFunc() throws IOException {
        String file = "Search_for_Players.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Search for Players");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }
}
