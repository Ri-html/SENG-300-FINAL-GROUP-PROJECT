package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system with account details, a list of friends, and a player profile.
 * <p>
 * The {@code User} class encapsulates the user's account information, such as username, email, and password,
 * and provides methods for managing the user's friends list and player profile.
 * </p>
 */
public class User {

    private String username;
    private String email;
    private String password; // User's account password
    private boolean isLoggedIn; // Tracks if the user is logged in
    private List<User> friends; // List to store user's friends
    private PlayerProfile playerProfile; // The player's profile containing game-related information

    /**
     * Constructs a new {@code User} with the specified username, email, and password.
     * <p>
     * This constructor initializes the user's friends list and player profile.
     * </p>
     *
     * @param username the username of the user
     * @param email    the email address of the user
     * @param password the password of the user
     * @throws IllegalArgumentException if the password is null or empty
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password); // Use the setter to handle password validation
        this.friends = new ArrayList<>();
        this.playerProfile = new PlayerProfile(); // Initialize the PlayerProfile
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * <p>
     * Passwords cannot be null or empty.
     * </p>
     *
     * @param password the new password of the user
     * @throws IllegalArgumentException if the password is null or empty
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        } else {
            this.password = password;
        }
    }

    /**
     * Returns the player's profile associated with the user.
     *
     * @return the player's profile
     */
    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    /**
     * Sets the player's profile associated with the user.
     *
     * @param playerProfile the new player's profile
     */
    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    /**
     * Returns the list of the user's friends.
     *
     * @return a list of {@code User} objects representing the user's friends
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * Adds a friend to the user's friends list.
     * <p>
     * If the friend is already in the list, no action is taken.
     * </p>
     *
     * @param friend the {@code User} object to add as a friend
     */
    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            System.out.println(friend.getUsername() + " has been added as a friend.");
        } else {
            System.out.println(friend.getUsername() + " is already your friend.");
        }
    }

    /**
     * Removes a friend from the user's friends list.
     * <p>
     * If the friend is not in the list, no action is taken.
     * </p>
     *
     * @param friend the {@code User} object to remove from the friends list
     */
    public void removeFriend(User friend) {
        if (friends.contains(friend)) {
            friends.remove(friend);
            System.out.println(friend.getUsername() + " has been removed from your friends list.");
        } else {
            System.out.println(friend.getUsername() + " is not in your friends list.");
        }
    }
}
