package ca.ucalgary.cpsc.projectguiv1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login_Controller {

    @FXML
    private GridPane identity;

    @FXML
    public void loginButtonFxn(ActionEvent event) throws IOException {
        // Navigate to the Home Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    public void signupLinkFxn(ActionEvent event) throws IOException {
        // Navigate to the Sign Up Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }
}
