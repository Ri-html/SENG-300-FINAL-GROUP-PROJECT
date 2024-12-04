package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;
    private User currentUser;
    private static UserDatabase instance;
    private static final String USER_DATABASE_FILE = "userDatabase.txt"; // Centralized file name

    // Private Constructor
    private UserDatabase() {
        this.users = new ArrayList<>();
        loadUsersFromFile(); // Automatically load users when the database is instantiated
    }

    // Singleton Pattern
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    // Load users from the file
    private void loadUsersFromFile() {
        try {
            UserFileReader.readUsersFromFile(USER_DATABASE_FILE, this);
            System.out.println("Users loaded successfully from file.");
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // Save users to the file
    public void saveUsersToFile() {
        try {
            UserFileWriter.writeUsersToFile(this, USER_DATABASE_FILE);
            System.out.println("Users saved successfully to file.");
        } catch (Exception e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Add a user to the database
    public void addUser(User user) {
        if (searchByUsername(user.getUsername()) != null) {
            System.out.println("Error: Username already exists.");
            return;
        }
        if (searchByEmail(user.getEmail()) != null) {
            System.out.println("Error: Email already registered.");
            return;
        }

        users.add(user);
        System.out.println("User added: " + user.getUsername());
        saveUsersToFile(); // Save the updated user list
    }

    // Search for a user by username
    public User searchByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    // Search for a user by email
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
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
