package TestFx.TicTacToe;

import ca.ucalgary.cpsc.projectguiv1.HelloApplication;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToe_Main_Menu_View_Test extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        new HelloApplication().start(stage); // Start the application
    }


    @org.junit.jupiter.api.Test //THIS IS DEFAULT EXAMPLE FROM SUTCLIFFE
    public void leaderboardButton() {

    }
    @org.junit.jupiter.api.Test
    public void playOnlineButton() {

    }

    @org.junit.jupiter.api.Test
    public void gameHistoryButton() {

    }

    @org.junit.jupiter.api.Test
    public void exitButton() {

    }

    @org.junit.jupiter.api.Test
    public void seeProfile() {

    }

    @org.junit.jupiter.api.Test
    public void chatHistory() {

    }

    @org.junit.jupiter.api.Test
    public void chatMessage() {

    }

    @org.junit.jupiter.api.Test
    public void backButton() {

    }
}