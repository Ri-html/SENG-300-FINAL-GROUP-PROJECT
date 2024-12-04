package TestFx;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.scene.control.Label;
import static org.mockito.Mockito.*;


import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Signup_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new HelloApplication().start(stage); // Start the application
    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void SignupEmptyUsername() {
        // Locate the button by its ID
        Button button = lookup("#signupButton").queryAs(Button.class);
        TextField username = lookup("#usernameField").queryAs(TextField.class);
        TextField email = lookup("#emailField").queryAs(TextField.class);
        TextField password = lookup("#passwordField").queryAs(TextField.class);

    //As long as username, email, and password are passed, you will be logged on
    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterUsername() {
        // Locate the textfield by its ID
        TextField textField = lookup("#usernameField").queryAs(TextField.class);

    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterEmail() {
        // Locate the textfield by its ID
        TextField textField = lookup("#emailField").queryAs(TextField.class);
    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterPassword() {
        // Locate the textfield by its ID
        TextField textField = lookup("#passwordField").queryAs(TextField.class);
    }
    //Still figuring out how Mockito works!
    @Test
    void testUpdateLabel() {
        // Mock the Label
        Label mockLabel = mock(Label.class);

        // Create the controller with the mocked Label
        MyController controller = new MyController(mockLabel);

        // Test the updateLabel method
        controller.updateLabel("Hello, World!");

           // Verify that setText was called on the Label
        verify(mockLabel).setText("Hello, World!");
    }
}