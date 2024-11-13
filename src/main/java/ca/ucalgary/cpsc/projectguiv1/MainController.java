package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLoginButtonClick() {
        welcomeText.setText("New Text!");
    }

    @FXML
    protected void onResetPasswordLinkClick() throws IOException {
        // Load reset password page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        SceneController screenController = new SceneController(scene); // set main
        //screenController.addScreen("signup", FXMLLoader.load(HelloApplication.class.getResource( "sign-up.fxml")));
        screenController.activate("reset");
    }

    @FXML
    protected void onSignUpLinkClicked() {
        // on sign up link clicked
        welcomeText.setText("New Text!");
    }

    @FXML
    protected void onResetBackButtonClick() {
        // go back to log in page
        welcomeText.setText("New Text!");
    }

    // After login button is clicked, redirect to home page

}