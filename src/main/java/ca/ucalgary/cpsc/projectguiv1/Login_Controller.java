package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login_Controller {

    @FXML
    private GridPane identity;

    @FXML
    private TextField usernameTxtField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginButtonFxn(ActionEvent event) throws IOException {
        String username = usernameTxtField.getText().trim();
        String password = passwordField.getText().trim(); // Use passwordField here

        UserDatabase userDatabase = UserDatabase.getInstance();
        User user = userDatabase.searchByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            userDatabase.setCurrentUser(user); // Set the logged-in user
            System.out.println("Login successful for user: " + username);

            // Navigate to the Home Page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage currentStage = (Stage) identity.getScene().getWindow();
            currentStage.setScene(scene);
        } else {
            System.out.println("Error: Invalid username or password.");
        }
    }



    @FXML
    public void signupLinkFxn(ActionEvent event) throws IOException {
        // Navigate to the Sign Up Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }
}
