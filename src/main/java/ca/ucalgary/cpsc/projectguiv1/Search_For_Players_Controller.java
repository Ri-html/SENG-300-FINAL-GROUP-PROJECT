package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.PlayerProfile;
import UserAndProfile.ProfileSearch;
import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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

        List<User> matchedUsers = new ArrayList<>();
        matchedUsers.addAll(db.searchUsername(searchTerm));
        matchedUsers.addAll(db.searchEmail(searchTerm));

        HashSet<User> allUsers = new HashSet<>(matchedUsers);

        // Set a custom cell factory to create buttons for each result
        resultView.setCellFactory(lv -> new ListCell<User>() {
            private final Button button = new Button();

            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);

                if (empty || user == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    button.setText(user.getUsername()); // Set button text
                    button.setOnAction(e -> navigateToProfilePage(user)); // Handle button click
                    setGraphic(button); // Add button to the ListCell
                    setText(null); // Clear default text
                }
            }
        });

        if(!matchedUsers.isEmpty()){
            for(User u : allUsers){
                resultView.getItems().add(u);
                resultLabel.setText("");
            }
        }

        if(resultView.getItems().isEmpty()){
            // no results
            resultView.getItems().add("No profile found for the given search term.");
        }

    }

    public void navigateToProfilePage(User user){

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
