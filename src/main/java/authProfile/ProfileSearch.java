package authProfile;

public class ProfileSearch {

    private UserDatabase userDatabase;

    // Constructor
    public ProfileSearch(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    // Search by username or email
    public PlayerProfile searchProfile(String searchTerm) {
        // First, try searching by username
        User user = userDatabase.searchByUsername(searchTerm);
        if (user != null) {
            return user.getPlayerProfile();
        }

        // If not found by username, try searching by email
        user = userDatabase.searchByEmail(searchTerm);
        if (user != null) {
            return user.getPlayerProfile();
        }

        // If not found by either, return null or throw exception
        return null;
    }
}
