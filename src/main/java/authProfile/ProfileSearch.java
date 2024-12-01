package authProfile;

public class ProfileSearch {

    private UserDatabase userDatabase;

    // Constructor
    public ProfileSearch(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    // Search by either username or email
    public PlayerProfile searchProfile(String searchTerm) {
        User user = searchUser(searchTerm);
        return user != null ? user.getPlayerProfile() : null;
    }

    // Helper method for searching by username or email
    private User searchUser(String searchTerm) {
        // Check if the search term matches a username
        User user = userDatabase.searchByUsername(searchTerm);
        if (user != null) {
            return user;
        }

        // If not found by username, check by email
        return userDatabase.searchByEmail(searchTerm);
    }
}
