package leaderboard.matchmaking_LB;

public class Player {
    private String playerID;
    private String username;
    private int skillLevel;
    private String status; // Available, In-Game, etc.

    /**
     * Constructor to create a new player.
     * @param username The username of the player.
     * @param skillLevel The skill level of the player.
     */
    public Player(String username, int skillLevel) {
        this.playerID = generatePlayerID();
        this.username = username;
        this.skillLevel = skillLevel;
        this.status = "Available";
    }

    /**
     * Generates a unique player ID.
     * @return A unique player ID.
     */
    protected String generatePlayerID() {
        return java.util.UUID.randomUUID().toString();
    }


    /**
     * Gets the player ID.
     * @return The player ID.
     */
    public String getPlayerID() {
        return playerID;
    }

   /**
     * Gets the username of the player.
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the skill level of the player.
     * @return The skill level of the player.
     */
    public int getSkillLevel() {
        return skillLevel;
    }

    /**
     * Gets the status of the player.
     * @return The status of the player (e.g., Available, In-Game).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the player.
     * @param status The new status of the player.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Checks if this player is equal to another object.
     * @param obj The object to compare.
     * @return True if the object is a Player and has the same player ID, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return playerID.equals(player.playerID);
    }

    /**
     * Generates a hash code for the player.
     * @return The hash code of the player.
     */
    @Override
    public int hashCode() {
        return playerID.hashCode();
    }
}


