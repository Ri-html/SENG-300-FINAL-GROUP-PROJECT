package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.PlayerProfile;
import UserAndProfile.ProfileSearch;
import UserAndProfile.User;
import UserAndProfile.UserDatabase;
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
import java.util.List;

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


    UserDatabase db = UserDatabase.getInstance();

    public void onSearch() {
        resultView.getItems().clear(); // clears out previous search

        String searchTerm = searchBar.getText();
        if (searchTerm == null || searchTerm.isEmpty()) {
            resultLabel.setText("Please enter a username or email."); // error message
        }
        searchBar.clear();

        List<User> matchedUsers = db.searchUsername(searchTerm);
        if(!matchedUsers.isEmpty()){
            for(int i = 0; i < matchedUsers.size(); i++){
                resultView.getItems().add(matchedUsers.get(i).getUsername());
            }
        }

        matchedUsers = db.searchEmail(searchTerm);
        if(!matchedUsers.isEmpty()){
            for(int i = 0; i < matchedUsers.size(); i++){
                resultView.getItems().add(matchedUsers.get(i).getUsername());
            }
        }
        // change code for email stuff

    }

    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Homepage");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }


}
