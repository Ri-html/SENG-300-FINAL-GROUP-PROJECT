package UserAndProfile;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize the user database
        UserDatabase userDatabase = new UserDatabase();

        // Create test users and add them to the database
        User user1 = new User("1", "Alice", "alice@example.com");
        User user2 = new User("2", "Bob", "bob@example.com");
        user1.setPassword("securepass");
        user2.setPassword("anotherpass");
        userDatabase.addUser(user1);
        userDatabase.addUser(user2);

        //Test creating a user without setting a password
        System.out.println("\nSijia: Testing default password functionality...");
        User user3 = new User("3", "Charlie", "charlie@example.com");
        userDatabase.addUser(user3); // Default password should be applied
        System.out.println("Charlie's default password: " + user3.getPassword());

        // Test logging in
        System.out.println("Testing login:");
        user1.loginUser(); // Log in the first user
        userDatabase.setCurrentUser(user1); // Set the current logged-in user in the database
        System.out.println("Currently logged in user: " + userDatabase.getCurrentUser().getUsername());

        // Display current profile
        System.out.println("\nDisplaying current profile modules:");
        user1.getPlayerProfile().displayProfile();

        // Test editing profile modules
        System.out.println("\nTesting EditProfile:");
        List<String> newModules = Arrays.asList("Games Played", "Ranking"); // User wants to display only these modules
        boolean editSuccess = userDatabase.editProfile(newModules);
        if (editSuccess) {
            System.out.println("Profile modules updated successfully.");
        } else {
            System.out.println("Failed to update profile modules.");
        }
        // Display updated profile
        System.out.println("\nDisplaying updated profile modules:");
        user1.getPlayerProfile().displayProfile();


        // Test password reset
        System.out.println("Testing password reset:");
        userDatabase.resetPassword("alice@example.com", "newpassword");

        // Test logging out
        System.out.println("Testing logout:");
        userDatabase.logoutCurrentUser();

        // Attempt to edit profile while logged out
        System.out.println("\nAttempting to edit profile while logged out:");
        userDatabase.editProfile(Arrays.asList("Player Stats")); // Should fail

    }
}
