package TestFx;
import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.io.IOException;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Signup_Test extends ApplicationTest {
    Pane mainroot;
    Stage mainstage;

    @Override
    public void start(Stage stage) throws IOException {
        mainroot = (Pane) FXMLLoader.load(HelloApplication.class.getResource("Signup.fxml"));
        mainstage = stage;
        stage.setScene(new Scene(mainroot));
        stage.show();
        stage.toFront();
    }
    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void SignupEmptyUsername() {
        // Locate the button by its ID
        Button button = (Button) mainroot.lookup("#signupButton");
        clickOn("#signupbutton");
        assertEquals("Sign Up", button.getText());


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
    /* Still figuring out how Mockito works!
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
    */
}