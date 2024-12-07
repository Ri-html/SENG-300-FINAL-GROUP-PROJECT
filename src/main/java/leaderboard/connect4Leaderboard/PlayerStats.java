package leaderboard.connect4Leaderboard;

/**
 * Represents the statistics of a player in the Connect Four leaderboard.
 * Tracks the total number of wins and losses for each player.
 */
public class PlayerStats implements Comparable<PlayerStats> {
    // Unique identifier for the player
    private String playerId;
    // Total number of wins for the player
    private int totalWins;
    // Total number of losses for the player
    private int totalLosses;

    /**
     * Constructor to initialize a player with no wins or losses.
     *
     * @param playerId The unique ID of the player.
     */
    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    /**
     * Getter for the player's unique ID.
     *
     * @return The player's ID.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Getter for the total number of wins.
     *
     * @return The total number of wins.
     */
    public int getTotalWins() {
        return totalWins;
    }

    /**
     * Getter for the total number of losses.
     *
     * @return The total number of losses.
     */
    public int getTotalLosses() {
        return totalLosses;
    }

    /**
     * Increment the win count for the player by one.
     */
    public void incrementWins() {
        totalWins++;
    }

    /**
     * Increment the loss count for the player by one.
     */
    public void incrementLosses() {
        totalLosses++;
    }

    /**
     * Compare this player's statistics with another player based on total wins.
     * Sorting is done in descending order of wins.
     *
     * @param other The other player's statistics to compare.
     * @return A negative value if this player has fewer wins, 
     *         zero if the wins are equal, 
     *         a positive value if this player has more wins.
     */
    @Override
    public int compareTo(PlayerStats other) {
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
