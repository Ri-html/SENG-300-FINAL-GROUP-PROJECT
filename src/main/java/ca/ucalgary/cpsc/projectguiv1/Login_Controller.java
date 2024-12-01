package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login_Controller {

    @FXML
    Label mainTitle;

    @FXML
    TextField usernameTxtField;

    @FXML
    Label usernameLbl;

    @FXML
    Button loginButton;

    @FXML
    GridPane identity;

    @Deprecated
    public Login_Controller(){ // This class

    }
    public void signupLinkFxn() throws IOException { // Switch to sign up page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Sign Up");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();

    }

}
