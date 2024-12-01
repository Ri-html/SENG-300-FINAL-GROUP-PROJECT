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

public class Sign_Up_Controller {

    @FXML
    GridPane identity;

    @FXML
    Label mainTitle;

    @FXML
    Label usernameLbl;

    @FXML
    Label nameLbl;

    @FXML
    Label emailLbl;

    @FXML
    TextField nameTxtField;

    @FXML
    TextField usernameTxtField;

    @FXML
    TextField emailTxtField;

    @FXML
    Button signupButton;

    @Deprecated
    public Sign_Up_Controller(){ // This class

    }
    public void loginLinkFxn() throws IOException { // Switch to sign up page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
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
