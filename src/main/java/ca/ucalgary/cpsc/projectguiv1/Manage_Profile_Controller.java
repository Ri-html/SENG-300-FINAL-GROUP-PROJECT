package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static ca.ucalgary.cpsc.projectguiv1.HelloApplication.usrDb;

public class Manage_Profile_Controller {

    @FXML
    Button displayGameHistory;

    @FXML
    TextArea gameHistory1;

    @FXML
    TextArea gameHistory2;

    @FXML
    TextArea gameHistory3;

    @FXML
    TextArea recentMatch1;

    @FXML
    TextArea recentMatch2;

    @FXML
    TextField usernameField;

    @FXML
    TextField emailField;

    @FXML
    Text game1;

    @FXML
    Text game2;

    @FXML
    Text game3;

    @FXML
    Text usernameText;

    @FXML
    Text status;

    @FXML
    ScrollPane identity;

    @FXML
    Button showFriendsBtn; // The button to display the friend list

    @FXML
    TextArea friendsListArea; // TextArea to display the friend list


    // Initializing profiles for each game
    private ChessProfile chessProfile = new ChessProfile();
    private TicTacToeProfile ticTacToeProfile = new TicTacToeProfile();
    private ConnectFourProfile connectFourProfile = new ConnectFourProfile();

    // Getting the current user
    private User currUser = HelloApplication.usrDb.getCurrentUser();


    /**
     * Initialing all the data to be shown in the manage profile screen, such as username, email, and game history
     */
    @FXML
    private void initialize() {


        usernameText.setText(currUser.getUsername());
        status.setText("Online");

        usernameField.setText(currUser.getUsername());
        emailField.setText(currUser.getEmail());

        displayGameHistory();
        displayRecentMatches();
    }

    /**
     * Displaying the game history for each individual game
     */
    @FXML
    private void displayGameHistory() {
        if(this.currUser != null) {
            game1.setText("Chess");
            this.gameHistory1.setText(this.currUser.getPlayerProfile().displayChessGameHistory());

            game2.setText("Tic-Tac-Toe");
            this.gameHistory2.setText(this.currUser.getPlayerProfile().displayTicTacToeGameHistory());

            game3.setText("Connect-4");
            this.gameHistory3.setText(this.currUser.getPlayerProfile().displayConnect4GameHistory());
        }
    }

    /**
     * If changes are made to user details, it will apply them and show on screen
     */
    public void updateUserDetails() {
        String newUsername = usernameField.getText();
        String newEmail = emailField.getText();

        if (!newUsername.equals(currUser.getUsername())) {
            currUser.setUsername(newUsername);
            usrDb.addUser(currUser);
            initialize();

        }

        if (!newEmail.equals(currUser.getEmail())) {
            currUser.setEmail(newEmail);
            usrDb.addUser(currUser);
            initialize();

        }

    }

    /**
     * Displaying the recent matches played
     */
    public void displayRecentMatches() {
        List<GameRecord> recentMatches = currUser.getPlayerProfile().getAllGameRecords();
        if (recentMatches != null) {
            if (recentMatches.size() > 1) {
                GameRecord match1 = recentMatches.getFirst();
                recentMatch1.setText(match1.toString());

                GameRecord match2 = recentMatches.get(1);
                recentMatch2.setText(match2.toString());
            }
            else if (recentMatches.size() == 1) {
                GameRecord match1 = recentMatches.getFirst();
                recentMatch1.setText(match1.toString());
                recentMatch2.setText("N/A");
            }
        } else {
            recentMatch1.setText("N/A");
            recentMatch2.setText("N/A");
        }
    }


    /**
     * Functionality to go back to the homepage after clicking the exit button
     * @throws IOException
     */
    public void exitBtnFunc() throws IOException { // Go to Homepage
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


    @FXML
    private void showFriendsList() {
        if (currUser != null && currUser.getFriends() != null) {
            List<User> friends = currUser.getFriends();
            if (friends.isEmpty()) {
                friendsListArea.setText("You have no friends added.");
            } else {
                StringBuilder friendListText = new StringBuilder();
                for (User friend : friends) {
                    friendListText.append(friend.getUsername()).append("\n");
                }
                friendsListArea.setText(friendListText.toString());
            }
        } else {
            friendsListArea.setText("Unable to load friends list.");
        }
    }
}
