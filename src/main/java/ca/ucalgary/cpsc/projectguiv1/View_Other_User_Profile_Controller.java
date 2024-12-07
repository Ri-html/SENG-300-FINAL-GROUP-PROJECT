package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
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
    private TextArea connect4TxtArea;

    @FXML
    private Text rankingChessLabel;

    @FXML
    private Text rankingTicTacToeLabel;

    @FXML
    private Text rankingConnect4Label;

    @FXML
    private Text yourUsernameLabel;

    @FXML
    private Text yourRankingChessLabel;

    @FXML
    private Text yourRankingTicTacToeLabel;

    @FXML
    private Text yourRankingConnect4Label;

    @FXML
    private Text usernameLabel;

    @FXML
    private AnchorPane identity;

    @FXML
    private Text yourWinRateChess;

    @FXML
    private Text yourWinRateTicTacToe;

    @FXML
    private Text yourWinRateConnect4;

    @FXML
    private Text currentStatusLabel;

    @FXML
    private Button AddFriend;

    @FXML
    private Button challengeChessBtn;

    @FXML
    private Button challengeTicTacToeBtn;

    @FXML
    private Button challengeConnect4Btn;

    /**
     * Method to set User object from another controller.
     * @param user user object being passed
     */
    public void setUser(User user){
        this.user = user;
        loadInfoFromUser(this.user);
    }

    /**
     * Loads information from current user and user whose profile is being viewed.
     * @param user user object being used.
     */
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

        // Connect 4 Rankings
        this.rankingConnect4Label.setText("Ranking: " + user.getPlayerProfile().getConnectFourProfile().getScoreRank());
        this.yourRankingConnect4Label.setText("Ranking: " + currUser.getPlayerProfile().getConnectFourProfile().getScoreRank());

        // Your Win Rate
        this.yourWinRateChess.setText("Wins: " + currUser.getPlayerProfile().getChessProfile().getTotalWins());
        this.yourWinRateTicTacToe.setText("Wins: " + currUser.getPlayerProfile().getTicTacToeProfile().getTotalWins());
        this.yourWinRateConnect4.setText("Wins: " + currUser.getPlayerProfile().getConnectFourProfile().getTotalWins());

        // Other user's current status
        this.currentStatusLabel.setText("Online");
    }
    // What will the challenge button do? Should it just start a new game with that user?

    /**
     * When challenge button next to tic-tac-toe game is clicked, loads tic-tac-toe game
     * with other player as opponent.
     * @throws IOException if the file does not load properly
     */
    public void playTicTacToe() throws IOException {
        // challengeTicTacToeBtn
        TicTacToeGameController.otherPlayersName = this.user.getUsername(); // User being challenged

        // Load tic tac toe game
        String file = "TicTacToe_Game_Screen.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing Tic Tac Toe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * When challenge button next to connect 4 game is clicked, loads connect 4 game
     * with other player as opponent.
     * @throws IOException if the file does not load properly
     */
    public void playConnect4() throws IOException{
        //challengeConnect4Btn
        Connect4GameController.otherPlayersName = this.user.getUsername();

        // Load Connect 4 game
        String file = "Connect4_Game_Screen.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing Connect 4");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * When challenge button next to chess game is clicked, loads chess game
     * with other player as opponent.
     * @throws IOException if the file does not load properly
     */
    public void playChess() throws IOException{
        //
        ChessGameController.otherPlayersName = this.user.getUsername();

        // Load Chess game
        String file = "Chess_Game_Screen.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing Chess");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * Goes back to Search for Players screen and closes View Other User Profile screen.
     * @throws IOException if the file does not load properly
     */
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


    public void AddFriend() {
        UserDatabase db = UserDatabase.getInstance(); // Access the database
        User currentUser = db.getCurrentUser(); // Get the logged-in user

        // Add the viewed user (this.user) to the current user's friends
            if (!currentUser.getFriends().contains(this.user)) {
                currentUser.addFriend(this.user); // Use the User class's method

                // Show a confirmation alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Friend Added");
                alert.setHeaderText(null); // No header text
                alert.setContentText(this.user.getUsername() + " has been added as a friend.");
                alert.showAndWait(); // Show the alert and wait for user action
            } else {
                // Show a message if the user is already a friend
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Already Friends");
                alert.setHeaderText(null); // No header text
                alert.setContentText(this.user.getUsername() + " is already your friend.");
                alert.showAndWait();

        }

    }

}
