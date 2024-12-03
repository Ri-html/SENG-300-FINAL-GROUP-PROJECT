package authProfile;

public class User {

    private String userId;
    private String username;
    private String email;
    private PlayerProfile playerProfile;
    private String password; // User's account password
    private boolean isLoggedIn; // Tracks if the user is logged in


    // Constructor
    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.playerProfile = new PlayerProfile();  // Initialize the player profile for this user
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
        this.password = password;
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    // Method to log the user in
    public void loginUser() {
        //in practice we would send password textbox contents to the database, ideally hashed so a hacker cannot listen in on the network activity and get a password in plaintext (known as sniffing) 
        isLoggedIn = true;
        System.out.println("User logged in successfully.");
    }

    // Method to log the user out
    public void logoutUser() {
        isLoggedIn = false;
        System.out.println("User logged out successfully.");
    }



}

