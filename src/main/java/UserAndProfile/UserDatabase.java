package UserAndProfile;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Manages the collection of {@link User} objects, providing methods to add, search, and retrieve users.
 * <p>
 * The {@link UserDatabase} class uses a singleton pattern to ensure there is only one instance of the user database
 * in the application. It also handles reading and writing user data from/to a file, specifically "userDatabase.txt".
 * </p>
 */
public class UserDatabase {
    private List<User> users;
    private User currentUser;
    private static UserDatabase instance;
    private static final String USER_DATABASE_FILE = "userDatabase.txt";

    /**
     * Private constructor to initialize the {@link UserDatabase}. It initializes the user list and loads existing
     * users from a file.
     */
    private UserDatabase() {
        this.users = new ArrayList<>();
        loadUsersFromFile(); // Load users when the database is initialized
    }

    /**
     * Retrieves the singleton instance of the {@link UserDatabase}.
     * <p>
     * If the instance is null, a new {@link UserDatabase} is created. This ensures that only one instance of the
     * database is used throughout the application.
     * </p>
     *
     * @return the singleton instance of {@link UserDatabase}
     */
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    /**
     * Loads user data from the specified file into the database.
     * <p>
     * This method uses the {@link UserFileReader#readUsersFromFileWithoutAdd} method to read users from a file and add
     * them directly to the database without invoking the addUser() method.
     * </p>
     */
    private void loadUsersFromFile() {
        UserFileReader.readUsersFromFileWithoutAdd(USER_DATABASE_FILE, this);
    }

    /**
     * Adds a user to the database and optionally saves the user to the file.
     * <p>
     * This method performs validation on the user’s username and email to ensure that there are no duplicates and that
     * the email contains the "@" symbol. If the validation passes, the user is added to the database and saved to the
     * file (unless specified otherwise).
     * </p>
     *
     * @param user        the {@link User} object to add to the database
     * @param saveToFile  whether to save the user to the file (default is true)
     */
    public void addUser(User user) {
        addUser(user, true); // Default behavior: Save to file
    }

    /**
     * Adds a user to the database with the option to save the user to the file.
     * <p>
     * This method validates the user's information before adding them to the database. It checks for duplicate usernames,
     * duplicate emails, and ensures that the email contains the "@" symbol. If any validation fails, an error message is shown.
     * </p>
     *
     * @param user        the {@link User} object to add
     * @param saveToFile  whether or not to save the user to the file after adding
     */
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
    }

    /**
     * Searches for a user by their username.
     * <p>
     * This method performs a case-insensitive search for a username in the database and returns the first matching
     * user, or {@code null} if no match is found.
     * </p>
     *
     * @param username the username to search for
     * @return the {@link User} object if found, or {@code null} if no match is found
     */
    public User searchByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().toLowerCase().contains(username.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a user by their email.
     * <p>
     * This method performs a case-insensitive search for an email in the database and returns the first matching user,
     * or {@code null} if no match is found.
     * </p>
     *
     * @param email the email to search for
     * @return the {@link User} object if found, or {@code null} if no match is found
     */
    public User searchByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for users by their username.
     * <p>
     * This method returns a list of users whose usernames contain the given search term, ignoring case.
     * </p>
     *
     * @param username the username search term
     * @return a list of {@link User} objects that match the search criteria
     */
    public List<User> searchUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for users by their email.
     * <p>
     * This method returns a list of users whose emails contain the given search term, ignoring case.
     * </p>
     *
     * @param email the email search term
     * @return a list of {@link User} objects that match the search criteria
     */
    public List<User> searchEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all users in the database.
     * <p>
     * This method returns a new list containing all users currently in the database.
     * </p>
     *
     * @return a list of all users in the database
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return the {@link User} object representing the current user, or {@code null} if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param user the {@link User} object to set as the current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Displays an error popup with the given message.
     * <p>
     * This method shows a GUI alert to inform the user of validation errors. It is intended to be used by methods
     * that require user feedback, such as when validating user data.
     * </p>
     *
     * @param message the error message to display
     */
    private void showErrorPopup(String message) {
        // In the backend, we should not show GUI popups. This is a placeholder for future handling.
    }

    /**
     * Adds a user directly to the internal list without triggering any additional checks or file-saving operations.
     * <p>
     * This method is used specifically during the loading of users from the file to avoid unnecessary recursive calls
     * to {@link addUser}.
     * </p>
     *
     * @param user the {@link User} object to add directly to the list
     */
    public void addUserDirectly(User user) {
        users.add(user);
    }
}
