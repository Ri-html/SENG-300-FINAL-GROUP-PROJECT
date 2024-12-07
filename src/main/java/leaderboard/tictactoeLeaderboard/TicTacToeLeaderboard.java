package leaderboard.tictactoeLeaderboard;

import java.util.*;

import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.tictactoeLeaderboard.PlayerStats;

/**
 * Manages the leaderboard for Tic Tac Toe players.
 * Provides methods for tracking wins, losses, and player rankings.
 * This class follows the Singleton design pattern.
 */
public class TicTacToeLeaderboard {
    // Singleton instance of the leaderboard
    private static TicTacToeLeaderboard instance = null;
    // Map to store player stats with player ID as the key
    private Map<String, PlayerStats> playerStatsMap;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the player stats map.
     */
    private TicTacToeLeaderboard() {
        playerStatsMap = new HashMap<>();
    }

    /**
     * Retrieves the singleton instance of the TicTacToeLeaderboard.
     * Ensures thread safety with synchronized access.
     * 
     * @return The singleton instance of the leaderboard.
     */
    public static synchronized TicTacToeLeaderboard getInstance() {
        if (instance == null) {
            instance = new TicTacToeLeaderboard();
        }
        return instance;
    }

    /**
     * Records a win for the specified player.
     * If the player does not exist in the leaderboard, a new entry is created.
     * 
     * @param playerId The unique ID of the player.
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
     * Records a loss for the specified player.
     * If the player does not exist in the leaderboard, a new entry is created.
     * 
     * @param playerId The unique ID of the player.
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
     * Retrieves a list of the top 10 players based on total wins.
     * 
     * @return A list of the top 10 players sorted by total wins.
     */
    public synchronized List<PlayerStats> getTopPlayers() {
        List<PlayerStats> allStats = new ArrayList<>(playerStatsMap.values());
        // Sort players by wins in descending order
        Collections.sort(allStats);
        return allStats.subList(0, Math.min(10, allStats.size()));
    }

    /**
     * Retrieves the rank of a specific player by ID.
     * 
     * @param playerId The unique ID of the player.
     * @return The 1-based rank of the player, or -1 if the player is not found.
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
     * Retrieves the statistics for a specific player by ID.
     * 
     * @param playerId The unique ID of the player.
     * @return The PlayerStats object for the player, or null if the player is not found.
     */
    public synchronized PlayerStats getPlayerStats(String playerId) {
        return playerStatsMap.get(playerId);
    }

    /**
     * Initializes the leaderboard with sample data for testing or demonstration purposes.
     * Adds predefined wins and losses for sample players.
     */
    public static void initializeSampleData() {
        // Get the singleton instance of the leaderboard
        TicTacToeLeaderboard leaderboard = TicTacToeLeaderboard.getInstance();

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
