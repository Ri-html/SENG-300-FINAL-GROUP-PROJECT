package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

/**
 * Controller class for the Login page.
 * Handles user interactions and events for the login view.
 */
public class LoginController {

    // FXML controls bound to Login.fxml
    @FXML
    private TextField usernameField; // TextField for entering the username

    @FXML
    private Button loginButton; // Button for submitting the login form

    @FXML
    private Hyperlink signUpLink; // Hyperlink to redirect users to the sign-up page

    /**
     * This method is called after the FXML file has been loaded.
     * Used for initializing the controller or setting default values.
     */
    @FXML
    public void initialize() {
        // Initialization logic for the login page
        System.out.println("Login Controller Initialized");
    }

    /**
     * Event handler for the login button click.
     * This method is triggered when the user clicks the "Login" button.
     */
    @FXML
    private void handleLoginButtonClick() {
        // Get the username entered in the TextField
        String username = usernameField.getText();
        System.out.println("Login button clicked with username: " + username);

        // Add your login logic here, e.g., validating user credentials
    }

    /**
     * Event handler for the sign-up hyperlink click.
     * This method is triggered when the user clicks the "Sign up" hyperlink.
     */
    @FXML
    private void handleSignUpLinkClick() {
        // Logic to redirect the user to the sign-up page
        System.out.println("Sign up link clicked");
    }
}
