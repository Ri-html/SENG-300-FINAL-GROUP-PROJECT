package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import authProfile.UserDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manage_Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        UserDatabase userDb = UserDatabase.getInstance(); // Ensure UserDatabase is a singleton
        User dummyUser = new User("Dummy Regald", "dummySlame", "dummy@gmail.com");
        dummyUser.setPassword("securepass");
        userDb.addUser(dummyUser);
        dummyUser.loginUser();
        userDb.setCurrentUser(dummyUser);
        launch();
    }
}