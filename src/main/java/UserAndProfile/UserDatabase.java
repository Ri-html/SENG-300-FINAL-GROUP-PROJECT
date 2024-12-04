package UserAndProfile;

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
        UserFileReader.readUsersFromFile(USER_DATABASE_FILE, this);
    }

    // Add a user and save to the file
    public void addUser(User user) {
        addUser(user, true); // Default behavior: Save to file
    }

    // Add user with control over file writing
    public void addUser(User user, boolean saveToFile) {
        if (searchByUsername(user.getUsername()) != null) {
            System.out.println("Error: Username already exists.");
            return;
        }
        if (searchByEmail(user.getEmail()) != null) {
            System.out.println("Error: Email already registered.");
            return;
        }
        users.add(user);
        if (saveToFile) {
            UserFileWriter.appendUserToFile(user, USER_DATABASE_FILE); // Append user to the file
        }
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
}