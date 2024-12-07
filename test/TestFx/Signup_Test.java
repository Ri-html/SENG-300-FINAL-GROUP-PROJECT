package TestFx;
import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import ca.ucalgary.cpsc.projectguiv1.Sign_Up_Controller;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Signup_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Sign_Up_Controller.class.getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
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

    /**
     * Testing if somebody tries to log in when there is missing info
     */
    @Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void SignupEmpty() {


    }
    /**
     * Testing if somebody tries to log in when username is taken
     */
    @Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void SignupTaken() {
        // Locate the button by its ID

    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterUsername() {


    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterEmail() {

    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void EnterPassword() {

    }
}