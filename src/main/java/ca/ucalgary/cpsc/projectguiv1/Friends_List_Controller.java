package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class Friends_List_Controller {

    @FXML
    private ListView<String> friendsList; // To display the friends

    @FXML
    private Button backBtn; // To return to the previous screen

    private User currUser; // Current user whose friends are displayed

    /**
     * Set the current user and populate the friends list.
     *
     * @param user The currently logged-in user.
     */
    public void setUser(User user) {
        this.currUser = user;
        populateFriendsList();
    }

    /**
     * Populate the ListView with the friends' usernames.
     */
    private void populateFriendsList() {
        friendsList.getItems().clear(); // Clear any existing data
        if (currUser != null && currUser.getFriends() != null) {
            List<User> friends = currUser.getFriends();
            if (friends.isEmpty()) {
                friendsList.getItems().add("You have no friends added.");
            } else {
                for (User friend : friends) {
                    friendsList.getItems().add(friend.getUsername());
                }
            }
        } else {
            friendsList.getItems().add("Unable to load friends list.");
        }
    }

    /**
     * Handle the back button click to return to the previous screen.
     */
    @FXML
    private void backBtnFunc() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close(); // Close the current stage
    }
}
