package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String email;
    private String password; // User's account password
    private boolean isLoggedIn; // Tracks if the user is logged in
    private List<User> friends; // List to store user's friends
    private PlayerProfile playerProfile; // Added PlayerProfile field

    // Constructor for creating a user with all fields
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password); // Use the setter to handle password validation
        this.friends = new ArrayList<>();
        this.playerProfile = new PlayerProfile(); // Initialize the PlayerProfile
    }

    // Getters and Setters for User fields
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        } else {
            this.password = password;
        }
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    // Getter and Setter for friends
    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            System.out.println(friend.getUsername() + " has been added as a friend.");
        } else {
            System.out.println(friend.getUsername() + " is already your friend.");
        }
    }

    public void removeFriend(User friend) {
        if (friends.contains(friend)) {
            friends.remove(friend);
            System.out.println(friend.getUsername() + " has been removed from your friends list.");
        } else {
            System.out.println(friend.getUsername() + " is not in your friends list.");
        }
    }
}