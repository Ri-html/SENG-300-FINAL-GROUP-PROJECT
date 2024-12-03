package ca.ucalgary.cpsc.projectguiv1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Sign_Up_Controller {

    @FXML
    private GridPane identity;

    @FXML
    public void loginLinkFxn(ActionEvent event) throws IOException {
        // Navigate back to the Login Page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    public void signupButtonFxn(ActionEvent event) throws IOException {
        // Navigate to the Home Page
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage currentStage = (Stage) identity.getScene().getWindow();
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Homepage.fxml. Ensure the file exists in the correct path.");
        }
    }
}
