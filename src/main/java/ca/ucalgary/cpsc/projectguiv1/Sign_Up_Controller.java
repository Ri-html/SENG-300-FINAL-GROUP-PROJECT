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

public class Sign_Up_Controller {

    @FXML
    private GridPane identity;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginLinkFxn(ActionEvent event) throws IOException {
        // Navigate back to the Login Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    public void signupButtonFxn(ActionEvent event) throws IOException {
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validation
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showErrorPopup("Error: All fields are required.");
            return;  // Exit early if any field is empty
        }

        // Check if the email is valid (contains '@')
        if (!email.contains("@")) {
            showErrorPopup("Error: Invalid email address. It must contain '@'.");
            return;  // Exit early if email is invalid
        }

        // Check if the username or email already exists
        UserDatabase userDatabase = UserDatabase.getInstance();
        if (userDatabase.searchByUsername(username) != null) {
            showErrorPopup("Error: Username already exists.");
            return;  // Exit early if username exists
        }
        if (userDatabase.searchByEmail(email) != null) {
            showErrorPopup("Error: Email already registered.");
            return;  // Exit early if email exists
        }

        // Add user to the database
        User newUser = new User(username, email, password);
        userDatabase.addUser(newUser);

        // Confirm successful registration
        System.out.println("User registered successfully!");

        // Navigate to the Home Page only after successful registration
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    // Method to show error popup
    private void showErrorPopup(String message) {
        // Create an error alert dialog
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");               // Set the title of the alert
        alert.setHeaderText(null);             // No header for the error (optional)
        alert.setContentText(message);        // Set the content message (your error message)

        // Show the alert and wait for the user to dismiss it
        alert.showAndWait();
    }
}
