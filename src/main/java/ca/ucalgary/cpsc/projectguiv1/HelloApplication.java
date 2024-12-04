package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static UserDatabase usrDb = UserDatabase.getInstance(); // Use the singleton instance

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        UserDatabase userDb = UserDatabase.getInstance();

        // Check if the database is empty
        if (userDb.getAllUsers().isEmpty()) {
            // Optionally add dummy data only if no users exist
            System.out.println("No users found in database. Starting with an empty database.");
        }

        launch();
    }

}
