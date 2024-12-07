package leaderboard.chessLeaderboard;

/**
 * Represents the statistics for a single player, primarily tracking the total number of wins.
 * Implements link Comparable to enable sorting by total wins in descending order.
 */

public class PlayerStats implements Comparable<PlayerStats> {
    private String playerId;
    private int totalWins;
    private int totalLosses;

    /**
     * Constructs a PlayerStats instance for the specified player.
     *
     * playerId the unique identifier for the player
     */

    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }


    public void incrementWins() {
        totalWins++;
    }

    public void incrementLosses() {
        totalLosses++;
    }

    /**
     * Compares this player's statistics with another player's stats for ordering.
     * Players are sorted in descending order of total wins.
     *
     * @param other the other PlayerStats object to compare against
     * @return a negative integer if this player has more wins than the other,
     *         zero if both have the same number of wins,
     *         and a positive integer if this player has fewer wins than the other.
     */

    @Override
    public int compareTo(PlayerStats other) {
        // Sorts in descending order of totalWins
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
