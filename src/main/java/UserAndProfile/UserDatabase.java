package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;
    private User currentUser; // Keeps track of the currently logged-in user
    private static UserDatabase instance;

    // Private Constructor
    private UserDatabase() {
        this.users = new ArrayList<>();
    }

    // Singleton Pattern
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    // Load users from a file
    public void loadUsersFromFile(String fileName) {
        try {
            UserFileReader.readUsersFromFile(fileName, this);
            System.out.println("Loaded users from file.");
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // Add a user to the database
    public void addUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("12345678"); // Assign default password if not set
        }

        // Prevent duplicate usernames or emails
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
    }

    // Search for a user by username
    public User searchByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    // Search for a user by email
    public User searchByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // Set the currently logged-in user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Get the currently logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Returns a list of all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
