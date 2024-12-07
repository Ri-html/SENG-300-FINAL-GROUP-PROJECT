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

    @Test
    public void testGameHistoryNavigation() {
    }
}
