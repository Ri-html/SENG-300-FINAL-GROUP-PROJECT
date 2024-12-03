package ca.ucalgary.cpsc.projectguiv1;

import authProfile.ChessProfile;
import authProfile.TicTacToeProfile;
import authProfile.ConnectFourProfile;
import authProfile.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Manage_Profile_Controller {

    @FXML
    TextArea gameHistory1;

    @FXML
    TextArea gameHistory2;

    @FXML
    TextArea gameHistory3;

    @FXML
    TextField nameField;

    @FXML
    TextField usernameField;

    @FXML
    TextField email;

    @FXML
    Text game1;

    @FXML
    Text game2;

    @FXML
    Text game3;

    @FXML
    Text usernameText;

    @FXML
    Text fullNameText;

    @FXML
    Text status;


    private ChessProfile chessProfile = new ChessProfile();
    private TicTacToeProfile ticTacToeProfile = new TicTacToeProfile();
    private ConnectFourProfile connectFourProfile = new ConnectFourProfile();

    //User user = User.getCurrentUser();
    User user = new User("Dummy Master", "dummySucks", "dummy@gmail.com");

    @FXML
    private void initialize() {
        usernameText.setText(user.getUsername());
        fullNameText.setText(user.getUserId());

        nameField.setText(user.getUserId());
        usernameField.setText(user.getUsername());
        email.setText(user.getEmail());

        displayGameHistory();
    }


    private void displayGameHistory() {
        game1.setText("Chess");
        gameHistory1.setText("Opponent: " + chessProfile.getLastOpponent() + "\n" +
                             "Result: " + chessProfile.getLastGameResult() + "\n" +
                             "Score: " + chessProfile.getLastGameScore());

        game2.setText("Tic-Tac-Toe");
        gameHistory2.setText("Opponent: " + ticTacToeProfile.getLastOpponent() + "\n" +
                "Result: " + ticTacToeProfile.getLastGameResult() + "\n" +
                "Score: " + ticTacToeProfile.getLastGameScore());

        game3.setText("Connect-4");
        gameHistory3.setText("Opponent: " + connectFourProfile.getLastOpponent() + "\n" +
                "Result: " + connectFourProfile.getLastGameResult() + "\n" +
                "Score: " + connectFourProfile.getLastGameScore());

    }

    @FXML
    private void updateUserDetails() {
        String newName = nameField.getText();
        String newUsername = usernameField.getText();
        String newEmail = email.getText();

        if (!newName.equals(user.getUserId())) {
            user.setUserId(newName);
        }

        if (!newUsername.equals(user.getUsername())) {
            user.setUsername(newUsername);
        }

        if (!newEmail.equals(user.getEmail())) {
            user.setEmail(newEmail);
        }

    }


}
