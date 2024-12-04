package UserAndProfile;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;
    private User currentUser;
    private static UserDatabase instance;
    private static final String USER_DATABASE_FILE = "userDatabase.txt";

    // Constructor
    private UserDatabase() {
        this.users = new ArrayList<>();
        loadUsersFromFile(); // Load users when the database is initialized
    }

    // Singleton Instance
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    // Load users from the file
    private void loadUsersFromFile() {
        // Directly read and add users to the list without calling addUser()
        UserFileReader.readUsersFromFileWithoutAdd(USER_DATABASE_FILE, this);
    }

    // Add a user and save to the file
    public void addUser(User user) {
        addUser(user, true); // Default behavior: Save to file
    }

    public void addUser(User user, boolean saveToFile) {
        // Check if username already exists
        if (searchByUsername(user.getUsername()) != null) {
            showErrorPopup("Error: Username already exists.");
            return;  // Exit early, no further action
        }

        // Check if email already exists
        if (searchByEmail(user.getEmail()) != null) {
            showErrorPopup("Error: Email already registered.");
            return;  // Exit early, no further action
        }

        // Check if email contains "@" symbol
        if (!user.getEmail().contains("@")) {
            showErrorPopup("Error: Invalid email address. It must contain '@'.");
            return;  // Exit early, no further action
        }

        // If all validations pass, add user to the database
        users.add(user);
        if (saveToFile) {
            UserFileWriter.appendUserToFile(user, USER_DATABASE_FILE); // Append user to the file
        }

        // Code to navigate to the home screen or the next screen (if validation passes)
        // Make sure to only navigate here if the user is valid and added.
    }


    // Search by username
    public User searchByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    // Search by email
    public User searchByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // Get all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Get current user
    public User getCurrentUser() {
        return currentUser;
    }

    // Set current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Helper method to display popups
    private void showErrorPopup(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    // New method to avoid calling addUser() during user loading
    public void addUserDirectly(User user) {
        users.add(user);
    }
}
