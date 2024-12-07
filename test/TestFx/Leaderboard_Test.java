package TestFx;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Leaderboard_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new HelloApplication().start(stage); // Start the application
    }

    /**
     * Test: Ensure clicking the 1st player button navigates to the correct user profile page.
     */
    @Test
    public void testPlayer1stButtonNavigation() {
        // Locate the 1st player button by its fx:id
        Button firstPlayerButton = lookup("#player_1st_button").queryAs(Button.class);

        // Ensure the button exists
        assertNotNull("The 1st player button should exist.", firstPlayerButton);

        // Set sample text for the button to simulate a player
        interact(() -> firstPlayerButton.setText("Alice - Wins: 10"));

        // Simulate clicking the button
        clickOn("#player_1st_button");

        // Verify that the new stage title corresponds to the user's profile
        Stage currentStage = (Stage) firstPlayerButton.getScene().getWindow();
        assertEquals("Viewing Alice's Profile", currentStage.getTitle());
    }
}
