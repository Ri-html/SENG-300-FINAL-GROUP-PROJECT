package UserAndProfile;

/**
 * The ProfileSearch class provides functionality to search for player profiles
 * by username or email within the user database.
 */
public class ProfileSearch {

    /**
     * The user database instance used for searching profiles.
     */
    private UserDatabase userDatabase;

    /**
     * Constructs a ProfileSearch object with the specified user database.
     * 
     * @param userDatabase The user database to be used for searching profiles.
     */
    public ProfileSearch(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    /**
     * Searches for a player profile by username or email.
     * 
     * @param searchTerm The username or email to search for.
     * @return The PlayerProfile associated with the search term, or null if no match is found.
     */
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

        // If not found by either, return null
        return null;
    }
}