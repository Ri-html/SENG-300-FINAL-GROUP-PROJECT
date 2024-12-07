package leaderboard.connect4Leaderboard;

import leaderboard.chessLeaderboard.ChessLeaderboard;

import java.util.*;

/**
 * Singleton class to manage the Connect4 leaderboard.
 * Tracks player wins, losses, and ranks.
 */
public class Connect4Leaderboard {
    // Singleton instance of the leaderboard
    private static Connect4Leaderboard instance = null;
    // Map to store player stats with player ID as the key
    private Map<String, PlayerStats> playerStatsMap;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the player stats map.
     */
    private Connect4Leaderboard() {
        playerStatsMap = new HashMap<>();
    }

    /**
     * Get the singleton instance of the leaderboard.
     *
     * @return the singleton instance of Connect4Leaderboard.
     */
    public static synchronized Connect4Leaderboard getInstance() {
        if (instance == null) {
            instance = new Connect4Leaderboard();
        }
        return instance;
    }

    /**
     * Record a win for the specified player.
     *
     * @param playerId the ID of the player who won.
     */
    public synchronized void recordWin(String playerId) {
        PlayerStats stats = playerStatsMap.get(playerId);
        // If player doesn't exist, create a new entry
        if (stats == null) {
            stats = new PlayerStats(playerId);
            playerStatsMap.put(playerId, stats);
        }
        stats.incrementWins();
    }

    /**
     * Record a loss for the specified player.
     *
     * @param playerId the ID of the player who lost.
     */
    public synchronized void recordLoss(String playerId) {
        PlayerStats stats = playerStatsMap.get(playerId);
        // If player doesn't exist, create a new entry
        if (stats == null) {
            stats = new PlayerStats(playerId);
            playerStatsMap.put(playerId, stats);
        }
        stats.incrementLosses();
    }

    /**
     * Get a list of the top 10 players based on total wins.
     *
     * @return a sorted list of the top 10 players.
     */
    public synchronized List<PlayerStats> getTopPlayers() {
        List<PlayerStats> allStats = new ArrayList<>(playerStatsMap.values());
        // Sort players by wins in descending order
        Collections.sort(allStats);
        return allStats.subList(0, Math.min(10, allStats.size()));
    }

    /**
     * Get the rank of a specific player by ID.
     *
     * @param playerId the ID of the player.
     * @return the rank of the player, or -1 if the player is not found.
     */
    public synchronized int getPlayerRank(String playerId) {
        List<PlayerStats> allStats = new ArrayList<>(playerStatsMap.values());
        // Sort players by wins in descending order
        Collections.sort(allStats);
        // Iterate through the sorted list to find the player's rank
        for (int i = 0; i < allStats.size(); i++) {
            if (allStats.get(i).getPlayerId().equals(playerId)) {
                return i + 1; // Return rank as 1-based index
            }
        }
        return -1; // Player not found
    }

    /**
     * Get the stats for a specific player by ID.
     *
     * @param playerId the ID of the player.
     * @return the PlayerStats object for the player, or null if not found.
     */
    public synchronized PlayerStats getPlayerStats(String playerId) {
        return playerStatsMap.get(playerId);
    }

    /**
     * Initialize the leaderboard with sample data for testing purposes.
     */
    public static void initializeSampleData() {
        // Get the singleton instance of the leaderboard
        Connect4Leaderboard leaderboard = Connect4Leaderboard.getInstance();
        // Add sample players with wins and losses
        leaderboard.recordWin("player1");
        leaderboard.recordWin("player2");
        leaderboard.recordLoss("player3");
        leaderboard.recordWin("player1");
        leaderboard.recordLoss("player2");
        leaderboard.recordWin("player3");




        // Add sample data
        leaderboard.recordWin("Alice");
        leaderboard.recordWin("Alice");
        leaderboard.recordWin("Alice");
        leaderboard.recordLoss("Alice");

        leaderboard.recordWin("Bob");
        leaderboard.recordWin("Bob");
        leaderboard.recordLoss("Bob");
        leaderboard.recordLoss("Bob");

        leaderboard.recordWin("Charlie");
        leaderboard.recordWin("Charlie");
        leaderboard.recordLoss("Charlie");

        leaderboard.recordWin("Diana");
        leaderboard.recordLoss("Diana");

        leaderboard.recordWin("Eve");
        leaderboard.recordWin("Eve");

        leaderboard.recordWin("Frank");
        leaderboard.recordWin("Frank");
        leaderboard.recordLoss("Frank");

        leaderboard.recordWin("Grace");
        leaderboard.recordLoss("Grace");
        leaderboard.recordLoss("Grace");

        leaderboard.recordWin("Henry");
        leaderboard.recordLoss("Henry");
        leaderboard.recordLoss("Henry");

    }


}
