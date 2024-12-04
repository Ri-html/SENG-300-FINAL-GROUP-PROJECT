package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import UserAndProfile.UserFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Sign_Up_Controller {

    @FXML
    private GridPane identity;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginLinkFxn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    public void signupButtonFxn(ActionEvent event) throws IOException {
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            System.out.println("Error: All fields are required.");
            return;
        }

        if (UserDatabase.getInstance().searchByUsername(username) != null) {
            System.out.println("Error: Username already exists.");
            return;
        }

        if (UserDatabase.getInstance().searchByEmail(email) != null) {
            System.out.println("Error: Email already registered.");
            return;
        }

        User newUser = new User(username, email, password);
        UserDatabase.getInstance().addUser(newUser);
        UserFileWriter.writeUsersToFile(UserDatabase.getInstance(), "userDatabase.txt");

        System.out.println("User registered successfully!");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage currentStage = (Stage) identity.getScene().getWindow();
        currentStage.setScene(scene);
    }
}
