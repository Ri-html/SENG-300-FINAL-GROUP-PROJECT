package TestFx.Chess;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;

import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chess_Game_Screen_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new HelloApplication().start(stage); // Start the application
    }

    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void MovePiece() {

    }
    @org.junit.jupiter.api.Test
    public void CapturePiece() {

    }
}
