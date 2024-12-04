package ticTacToeLeaderboard;

public class PlayerStats implements Comparable<PlayerStats> {
    // Unique identifier for the player
    private String playerId; 
    // Total number of wins for the player
    private int totalWins; 
    // Total number of losses for the player
    private int totalLosses;

    // Constructor to initialize a player with no wins or losses
    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    // Getter for player ID
    public String getPlayerId() {
        return playerId;
    }

    // Getter for total wins
    public int getTotalWins() {
        return totalWins;
    }

    // Getter for total losses
    public int getTotalLosses() {
        return totalLosses;
    }

    // Increment the win count for the player
    public void incrementWins() {
        totalWins++;
    }

    // Increment the loss count for the player
    public void incrementLosses() {
        totalLosses++;
    }

    // Compare players based on total wins (descending order)
    @Override
    public int compareTo(PlayerStats other) {
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
