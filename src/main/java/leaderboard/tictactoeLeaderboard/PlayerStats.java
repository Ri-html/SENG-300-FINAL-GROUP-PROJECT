package leaderboard.tictactoeLeaderboard;

/**
 * Represents the statistics for a player, including wins and losses.
 * This class implements Comparable to allow sorting based on total wins.
 */
public class PlayerStats implements Comparable<PlayerStats> {
    // Unique identifier for the player
    private String playerId; 
    // Total number of wins for the player
    private int totalWins; 
    // Total number of losses for the player
    private int totalLosses;

    /**
     * Constructs a PlayerStats object with the specified player ID.
     * Initializes the total wins and losses to zero.
     * 
     * @param playerId The unique identifier for the player.
     */
    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    /**
     * Retrieves the player's unique ID.
     * 
     * @return The player ID.
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Retrieves the total number of wins for the player.
     * 
     * @return The total wins.
     */
    public int getTotalWins() {
        return totalWins;
    }

    /**
     * Retrieves the total number of losses for the player.
     * 
     * @return The total losses.
     */
    public int getTotalLosses() {
        return totalLosses;
    }

    /**
     * Increments the player's win count by one.
     */
    public void incrementWins() {
        totalWins++;
    }

    /**
     * Increments the player's loss count by one.
     */
    public void incrementLosses() {
        totalLosses++;
    }

    /**
     * Compares this PlayerStats object with another based on total wins.
     * The comparison is in descending order of wins.
     * 
     * @param other The PlayerStats object to compare against.
     * @return A negative integer, zero, or a positive integer if this object
     *         has more, equal, or fewer wins than the specified object.
     */
    @Override
    public int compareTo(PlayerStats other) {
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
