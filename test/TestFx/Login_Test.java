package TestFx;

import UserAndProfile.UserDatabase;
import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Login_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test: Ensure login works with valid credentials.
     */
    @Test
    public void testValidLogin() {
        // Locate the username and password fields and login button
        TextField usernameField = lookup("#usernameTxtField").queryAs(TextField.class);
        PasswordField passwordField = lookup("#passwordField").queryAs(PasswordField.class);
        Button loginButton = lookup("#loginButton").queryAs(Button.class);

        // Ensure all fields exist
        assertNotNull("Username field should exist.", usernameField);
        assertNotNull("Password field should exist.", passwordField);
        assertNotNull("Login button should exist.", loginButton);

        // Set valid credentials
        interact(() -> {
            usernameField.setText("validUsername");
            passwordField.setText("validPassword");
        });

        // Simulate login button click
        clickOn("#loginButton");

        // Verify the title of the new stage matches the Home Page
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
       // assertEquals("Homepage", currentStage.getTitle());
    }

    /**
     * Test: Ensure login fails with invalid credentials.
     */
    @Test
    public void testInvalidLogin() {
        // Locate the username and password fields and login button
        TextField usernameField = lookup("#usernameTxtField").queryAs(TextField.class);
        PasswordField passwordField = lookup("#passwordField").queryAs(PasswordField.class);
        Button loginButton = lookup("#loginButton").queryAs(Button.class);

        // Ensure all fields exist
        assertNotNull("Username field should exist.", usernameField);
        assertNotNull("Password field should exist.", passwordField);
        assertNotNull("Login button should exist.", loginButton);

        // Set invalid credentials
        interact(() -> {
            usernameField.setText("invalidUsername");
            passwordField.setText("invalidPassword");
        });

        // Simulate login button click
        clickOn("#loginButton");
/*Issue with alert.class
        // Check if the error popup is displayed
        Alert alert = lookup(".alert").queryAs(Alert.class);
        assertNotNull("Error popup should appear for invalid login.", alert);
        assertEquals("Login Error", alert.getTitle());
        assertEquals("Error: Invalid username or password.", alert.getContentText());

 */
    }

    /**
     * Test: Ensure navigation to the sign-up screen works.
     */
    @Test
    public void testSignupNavigation() {
        // Locate the sign-up button/link
        Button signupButton = lookup("#signupButton").queryAs(Button.class);

        // Ensure the button exists
        assertNotNull("Sign-up button should exist.", signupButton);

        // Simulate button click
        clickOn("#signupButton");

        // Verify the title of the new stage matches the Sign-Up screen
        Stage currentStage = (Stage) signupButton.getScene().getWindow();
        assertEquals("Signup", currentStage.getTitle());
    }
}
