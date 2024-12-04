package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Manage_Profile_Controller {

    @FXML
    private TextField usernameText;

    @FXML
    private TextField fullNameText;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField game1;

    @FXML
    private TextField gameHistory1;

    @FXML
    private TextField game2;

    @FXML
    private TextField gameHistory2;

    public void initialize() {
        UserDatabase userDb = UserDatabase.getInstance();
        User currUser = userDb.getCurrentUser();

        // Display user information
        usernameText.setText(currUser.getUsername());
        fullNameText.setText(currUser.getUsername()); // Use username instead of userId
        nameField.setText(currUser.getUsername());
        usernameField.setText(currUser.getUsername());
        emailField.setText(currUser.getEmail());

        displayGameHistory();
    }

    private void displayGameHistory() {
        // Assuming game profiles are already available
        game1.setText("Chess");
        gameHistory1.setText("Opponent: " + "PlayerX" + "\n" +
                "Result: " + "Win" + "\n" +
                "Score: " + "10");

        game2.setText("Tic-Tac-Toe");
        gameHistory2.setText("Opponent: " + "PlayerY" + "\n" +
                "Result: " + "Lose" + "\n" +
                "Score: " + "5");
    }
}
