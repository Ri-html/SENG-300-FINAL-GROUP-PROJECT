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
 * Controller class for handling user login and navigation in the application.
 * <p>
 * This class provides functionality for user login, displaying an error popup in case of invalid login,
 * and navigating to the Home Page or Sign Up page. It interacts with the UserDatabase to authenticate the user.
 * </p>
 */
public class Login_Controller {

    /**
     * The GridPane layout containing the login elements.
     */
    @FXML
    private GridPane identity;

    /**
     * The TextField for the username input.
     */
    @FXML
    private TextField usernameTxtField;

    /**
     * The PasswordField for the password input.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Handles the login action when the login button is clicked.
     * <p>
     * This method validates the user's credentials by searching for the username in the UserDatabase.
     * If the username and password match, the user is logged in and redirected to the Home Page.
     * Otherwise, an error popup is shown.
     * </p>
     *
     * @param event the ActionEvent triggered by the login button click
     * @throws IOException if an I/O error occurs while loading the Home Page
     */
    @FXML
    public void loginButtonFxn(ActionEvent event) throws IOException {
        String username = usernameTxtField.getText().trim();
        String password = passwordField.getText().trim(); // Use passwordField here

        UserDatabase userDatabase = UserDatabase.getInstance();
        User user = userDatabase.searchByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            userDatabase.setCurrentUser(user); // Set the logged-in user
            System.out.println("Login successful for user: " + username);
            UserSession.getInstance().setCurrentUsername(username);

            // Navigate to the Home Page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,500);
            Stage newStg = new Stage();
            newStg.sizeToScene();
            newStg.setTitle("Homepage");
            newStg.setScene(scene);
            newStg.show();
            Stage stgWindw = (Stage) this.identity.getScene().getWindow();
            stgWindw.close();

        } else {
            // Show error pop-up instead of printing to the console
            showErrorPopup("Error: Invalid username or password.");
        }
    }

    /**
     * Navigates the user to the Sign Up page when the Sign Up link is clicked.
     *
     * @param event the ActionEvent triggered by the Sign Up link click
     * @throws IOException if an I/O error occurs while loading the Sign Up page
     */
    @FXML
    public void signupLinkFxn(ActionEvent event) throws IOException {
        // Navigate to the Sign Up Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    /**
     * Displays an error popup with a given message.
     * <p>
     * This method is called when there is an invalid login attempt, and it shows an alert with the error message.
     * </p>
     *
     * @param message the message to be displayed in the error popup
     */
    private void showErrorPopup(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null); // No header
        alert.setContentText(message); // Set the error message
        alert.showAndWait(); // Show the alert and wait for user action
    }
}
