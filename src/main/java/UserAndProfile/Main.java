package UserAndProfile;

/**
 * The Main class demonstrates the usage of the UserDatabase class.
 * It initializes the user database, displays all users, adds a new user,
 * and then displays the updated list of users.
 */
public class Main {

    /**
     * The main method serves as the entry point of the application.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the user database
        UserDatabase userDb = UserDatabase.getInstance();

        // Display loaded users
        System.out.println("Loaded users:");
        for (User user : userDb.getAllUsers()) {
            System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }

        // Test adding a new user
        User newUser = new User("testUser", "test@example.com", "password123");
        userDb.addUser(newUser);

        // Display updated users
        System.out.println("Updated users:");
        for (User user : userDb.getAllUsers()) {
            System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }
    }
}