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

/**
 * Controller class for handling user sign-up in the application.
 * <p>
 * This class manages the user registration process, including validating input fields,
 * checking if the username or email already exists, and navigating to the Home page
 * upon successful registration. It also handles navigation back to the Login page.
 * </p>
 */
public class Sign_Up_Controller {

    /**
     * The GridPane layout containing the sign-up form elements.
     */
    @FXML
    private GridPane identity;

    /**
     * The TextField for entering the user's email address.
     */
    @FXML
    private TextField emailField;

    /**
     * The TextField for entering the user's username.
     */
    @FXML
    private TextField usernameField;

    /**
     * The PasswordField for entering the user's password.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Navigates back to the Login page when the login link is clicked.
     *
     * @param event the ActionEvent triggered by the login link click
     * @throws IOException if an I/O error occurs while loading the Login page
     */
    @FXML
    public void loginLinkFxn(ActionEvent event) throws IOException {
        // Navigate back to the Login Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    /**
     * Handles the sign-up action when the sign-up button is clicked.
     * <p>
     * This method validates the user input fields (email, username, and password), checks if
     * the username and email already exist in the database, and adds the new user to the database
     * upon successful validation. It also navigates to the Home Page after a successful registration.
     * </p>
     *
     * @param event the ActionEvent triggered by the sign-up button click
     * @throws IOException if an I/O error occurs while loading the Home page
     */
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

    /**
     * Displays an error popup with a given message.
     * <p>
     * This method is called when there is a validation error or an issue during the sign-up process,
     * and it shows an alert with the error message.
     * </p>
     *
     * @param message the message to be displayed in the error popup
     */
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
