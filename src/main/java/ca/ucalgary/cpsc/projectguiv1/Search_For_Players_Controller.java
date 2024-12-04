package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.ProfileSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Search_For_Players_Controller {

    @FXML
    TextField searchBar;

    @FXML
    Button searchButton;

    @FXML
    Label mainTitle;

    @FXML
    ListView resultView;

    @FXML
    Label resultLabel;

    @FXML
    Button backBtn;

    @FXML
    AnchorPane identity;



    private ProfileSearch profileSearch;

//    public Search_For_Players_Controller(ProfileSearch profileSearch){
//        this.profileSearch = profileSearch;
//    }

//    @FXML
//    private void onSearch(ActionEvent event) {
//        resultView.getItems().clear(); // clears out previous search
//        String searchTerm = searchBar.getText();
//        if (searchTerm == null || searchTerm.isEmpty()) {
//            resultLabel.setText("Please enter a username or email."); // error message
//            return;
//        }
//
//        profileSearch.searchProfile(searchTerm);
//
//        resultView.getItems().addAll(profileSearch.searchProfile(searchBar.getText()));
//
//    }

    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Playing Connect 4!");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }


}
