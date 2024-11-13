package ca.ucalgary.cpsc.projectguiv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        SceneController screenController = new SceneController(scene); // set main
        screenController.addScreen("signup", FXMLLoader.load(HelloApplication.class.getResource( "sign-up.fxml")));
        screenController.addScreen("reset", FXMLLoader.load(HelloApplication.class.getResource( "reset-password.fxml")));

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}