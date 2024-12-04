package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userId;
    private String username;
    private String email;
    private PlayerProfile playerProfile;
    private String password; // User's account password
    private boolean isLoggedIn; // Tracks if the user is logged in
    private List<User> friends; // List to store user's friends

    // Constructor
    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.playerProfile = new PlayerProfile();  // Initialize the player profile for this user
        this.password = "1234"; // Assign default password
        this.friends = new ArrayList<>();
    }

    // Getters and Setters for User fields
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password cannot be empty. Default password '12345678' is set.");
            this.password = "12345678"; // Keep default password if empty
        } else {
            this.password = password;
        }
    }

    // Getter and Setter for friends
    public List<User> getFriends() {
        return friends;
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    //Method to create new user after registration
    public void createUser(String email, String password) {
        //check that password meet requirements
        if (password.length() > 7){
        isLoggedIn = true;
        System.out.println("User logged in successfully.");
        }else{
            System.out.println("Password needs to be 8 characters.");
        }
        
    }


    // Method to log the user in
    public void loginUser() {
        isLoggedIn = true;
        System.out.println("User logged in successfully.");
    }

    // Method to log the user out
    public void logoutUser() {
        isLoggedIn = false;
        System.out.println("User logged out successfully.");
    }


    // Method to add a friend
    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            System.out.println(friend.getUsername() + " has been added as a friend.");
        } else {
            System.out.println(friend.getUsername() + " is already your friend.");
        }
    }

    // Method to remove a friend
    public void removeFriend(User friend) {
        if (friends.contains(friend)) {
            friends.remove(friend);
            System.out.println(friend.getUsername() + " has been removed from your friends list.");
        } else {
            System.out.println(friend.getUsername() + " is not in your friends list.");
        }
    }

    // Method to view friends
    public void viewFriends() {
        if (friends.isEmpty()) {
            System.out.println("You have no friends yet.");
        } else {
            System.out.println("Your friends:");
            for (User friend : friends) {
                System.out.println(friend.getUsername());
            }
        }
    }
}




