package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game_Screen_Controller {

    String currentPage;
    @FXML
    Button exit;
    @FXML
    Pane identity;
    @FXML
    AnchorPane afterlife1;
    @FXML
    AnchorPane afterlife2;

    //Chess Specific
    //Tic Tac Toe Specific
    //Checkers Specific

    public void setSceneReference(Scene newScene) {
        //Shared function

        // Register the 'onShowing' event handler on the Stage
        Stage stage = (Stage) newScene.getWindow(); // Get the stage from the current scene
        stage.setOnShowing(event -> onSceneShowing()); // Handle when the scene is about to be shown
    }

    private void onSceneShowing() {
        currentPage=identity.getId();
    }

}
