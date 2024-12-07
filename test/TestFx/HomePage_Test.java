package TestFx;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class HomePage_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new HelloApplication().start(stage); // Start the application
    }

    /**
     * Test: Ensure clicking the "Game History" button navigates to the Game History screen.
     */
    @Test
    public void testGameHistoryNavigation() {
        // Locate the "Game History" button by its fx:id
        Button gameHistoryButton = lookup("#gameHistory").queryAs(Button.class);

        // Ensure the button exists
        assertNotNull("The 'Game History' button should exist.", gameHistoryButton);

        // Simulate a button click
        clickOn("#gameHistory");

        // Verify that the stage title matches the Game History screen
        Stage currentStage = (Stage) gameHistoryButton.getScene().getWindow();
        assertEquals("Game History", currentStage.getTitle(), "The title should match 'Game History' after navigation.");
    }
}
