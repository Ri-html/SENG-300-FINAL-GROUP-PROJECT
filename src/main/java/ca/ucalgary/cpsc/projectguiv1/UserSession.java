package ca.ucalgary.cpsc.projectguiv1;

/**
 * A singleton class for managing user session data.
 */
public class UserSession {
    private static UserSession instance;

    private String currentUsername; // The currently logged-in user
    private String viewedUsername;  // The currently viewed user

    // Private constructor to enforce the singleton pattern
    private UserSession() {}

    /**
     * Gets the single instance of UserSession.
     * @return the UserSession instance
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Gets the username of the currently logged-in user.
     * @return the current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Sets the username of the currently logged-in user.
     * @param currentUsername the username to set
     */
    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    /**
     * Gets the username of the currently viewed user.
     * @return the viewed username
     */
    public String getViewedUsername() {
        return viewedUsername;
    }

    /**
     * Sets the username of the currently viewed user.
     * @param viewedUsername the username to set
     */
    public void setViewedUsername(String viewedUsername) {
        this.viewedUsername = viewedUsername;
    }
}