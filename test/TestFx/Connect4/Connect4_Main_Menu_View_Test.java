package TestFx.Connect4;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Connect4_Main_Menu_View_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new HelloApplication().start(stage); // Start the application
    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void testButtonClick() {
        // Locate the button by its ID
        Button button = lookup("#myButton").queryAs(Button.class);

        // Assert initial text
        assertEquals("Click Me", button.getText());

        // Simulate a button click
        clickOn("#myButton");

        // Assert changed text
        assertEquals("Clicked", button.getText());
    }
}