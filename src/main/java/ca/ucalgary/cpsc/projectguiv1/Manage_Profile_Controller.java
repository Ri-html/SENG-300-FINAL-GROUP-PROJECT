package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Manage_Profile_Controller {

    @FXML
    TextArea gameHistory1;

    @FXML
    TextArea gameHistory2;

    @FXML
    TextArea gameHistory3;

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


    private ChessProfile chessProfile = new ChessProfile();
    private TicTacToeProfile ticTacToeProfile = new TicTacToeProfile();
    private ConnectFourProfile connectFourProfile = new ConnectFourProfile();

    UserDatabase userDb = UserDatabase.getInstance();
    User currUser = userDb.getCurrentUser();


    @FXML
    private void initialize() {


        usernameText.setText(currUser.getUsername());
        status.setText("Online");

        usernameField.setText(currUser.getUsername());
        emailField.setText(currUser.getEmail());

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


    public void updateUserDetails() {
        String newUsername = usernameField.getText();
        String newEmail = emailField.getText();

        if (!newUsername.equals(currUser.getUsername())) {
            currUser.setUsername(newUsername);
            userDb.addUser(currUser);
            initialize();

        }

        if (!newEmail.equals(currUser.getEmail())) {
            currUser.setEmail(newEmail);
            userDb.addUser(currUser);
            initialize();

        }

    }


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


}
