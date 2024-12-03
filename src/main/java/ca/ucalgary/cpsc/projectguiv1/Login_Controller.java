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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage currentStage = (Stage) identity.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Homepage.fxml. Ensure the file exists in the correct path.");
        }
    }

    @FXML
    public void signupLinkFxn(ActionEvent event) throws IOException {
        // Navigate to the Sign Up Page
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage currentStage = (Stage) identity.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Signup.fxml. Ensure the file exists in the correct path.");
        }
    }
}
