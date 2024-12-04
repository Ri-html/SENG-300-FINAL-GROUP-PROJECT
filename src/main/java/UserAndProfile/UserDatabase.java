package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;
    private User currentUser; // Keeps track of the currently logged-in user

    // Constructor
    public UserDatabase() {
        this.users = new ArrayList<>();
    }

    // Add a user to the database
    public void addUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("12345678"); // Assign default password if not set
        }
        users.add(user);
        System.out.println("User added: " + user.getUsername() + " (Default password applied if none was set)");
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

    // Log out the currently logged-in user
    public boolean logoutCurrentUser() {
        if (currentUser != null) {
            String username = currentUser.getUsername(); // Save username for logging
            currentUser.logoutUser(); // Call the logout method on the User class
            currentUser = null; // Clear the current user
            System.out.println("User " + username + " has been logged out.");
            return true;
        } else {
            System.out.println("Error: No user is currently logged in.");
            return false;
        }
    }

    // Send a password reset link to the user's email
    public boolean sendPasswordResetLink(String email) {
        User user = searchByEmail(email); // Find the user by email
        if (user != null) {
            String resetLink = "http://resetpassword.com/reset?userId=" + user.getUserId(); // Generate a reset link
            System.out.println("Password reset link sent to " + email + ": " + resetLink);
            return true;
        } else {
            System.out.println("Error: Email not found in the system.");
            return false;
        }
    }

    // Reset the user's password
    public boolean resetPassword(String email, String newPassword) {
        User user = searchByEmail(email); // Find the user by email
        if (user != null) {
            if (newPassword == null || newPassword.isEmpty()) {
                System.out.println("Error: New password cannot be empty.");
                return false;
            }
            user.setPassword(newPassword); // Update the password
            System.out.println("Password reset successfully for user: " + email);
            return true;
        } else {
            System.out.println("Error: Email not registered.");
            return false;
        }
    }

    /**
     * Edit the currently logged-in user's profile modules.
     *
     * @param modules List of module names the user wants to display.
     * @return true if the profile was updated successfully, false otherwise.
     */
    public boolean editProfile(List<String> modules) {
        if (currentUser == null) {
            System.out.println("Error: No user is currently logged in.");
            return false;
        }

        PlayerProfile profile = currentUser.getPlayerProfile();
        if (profile == null) {
            System.out.println("Error: User does not have a profile.");
            return false;
        }

        // Attempt to set the selected modules
        boolean success = profile.setSelectedModules(modules);
        if (success) {
            System.out.println("Profile updated successfully. Selected modules: " + profile.getSelectedModules());
            // Optionally, send a confirmation email or log the update
            return true;
        } else {
            System.out.println("Failed to update profile. Please ensure all modules are valid.");
            return false;
        }
    }

    /**
     * Edit the currently logged-in user's account details, such as password or username. Email cannot be changed.
     *
     * @param item Item that the user wants to change. Can be "username" or "password"
     * @return true if the profile was updated successfully, false otherwise.
     */
    public boolean editAccount(String item){
        if (item == "username"){
            //the user will set their new username into a textbox which may be incorporated into an alert.
            //after successful change, an email may be sent to the user.
        }else if (item == "password"){
            //user needs to enter their current password into a textbox, to check that it is correct. after this occurs, they enter their new password.
            //after successful change, an email may be sent to the user.
        }
        return false;
    }

    // Returns a list of all users in the database
    public List<User> getAllUsers() {
        return new ArrayList<>(users); // Return a copy of the users list to avoid direct modification
    }



}

