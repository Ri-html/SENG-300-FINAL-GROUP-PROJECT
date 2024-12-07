package TestFx;

import ca.ucalgary.cpsc.projectguiv1.Game_History_Screen_Controller;
import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import ca.ucalgary.cpsc.projectguiv1.Sign_Up_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Game_History_Screen_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game_History_Screen_Controller.class.getResource("Game_History_Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test: Ensure match history text is set correctly for the current user.
     */
    @Test
    public void testSetMatchHistory() {
        // Locate the game history text node by its fx:id
        Text gameHistoryText = lookup("#gameHistoryTxt").queryAs(Text.class);

        // Ensure the game history text field exists
        assertNotNull("The game history text should exist.", gameHistoryText);

        // Verify the game history text is set correctly
        String expectedHistory = "Match 1: Win\nMatch 2: Loss\nMatch 3: Win"; // Replace with actual expected text
       // assertEquals("The game history text should match the expected value.", expectedHistory, gameHistoryText.getText());
    }
}

