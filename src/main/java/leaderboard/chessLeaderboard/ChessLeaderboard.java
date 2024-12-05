package leaderboard.chessLeaderboard;

import java.util.*;

public class ChessLeaderboard {
    private static ChessLeaderboard instance = null;
    private Map<String, PlayerStats> playerStatsMap;

    private ChessLeaderboard() {
        playerStatsMap = new HashMap<>();
    }

    public static synchronized ChessLeaderboard getInstance() {
        if (instance == null) {
            instance = new ChessLeaderboard();
        }
        return instance;
    }

    public synchronized void recordWin(String playerId) {
        PlayerStats stats = playerStatsMap.get(playerId);
        if (stats == null) {
            stats = new PlayerStats(playerId);
            playerStatsMap.put(playerId, stats);
        }
        stats.incrementWins();
    }

    public synchronized void recordLoss(String playerId) {
        PlayerStats stats = playerStatsMap.get(playerId);
        // If player doesn't exist, create a new entry
        if (stats == null) {
            stats = new PlayerStats(playerId);
            playerStatsMap.put(playerId, stats);
        }
        stats.incrementLosses();
    }

    public synchronized List<PlayerStats> getTopPlayers(int n) {
        List<PlayerStats> allStats = new ArrayList<>(playerStatsMap.values());
        Collections.sort(allStats);
        return allStats.subList(0, Math.min(n, allStats.size()));
    }

    public synchronized int getPlayerRank(String playerId) {
        List<PlayerStats> allStats = new ArrayList<>(playerStatsMap.values());
        Collections.sort(allStats);
        for (int i = 0; i < allStats.size(); i++) {
            if (allStats.get(i).getPlayerId().equals(playerId)) {
                return i + 1; // Ranks start from 1
            }
        }
        return -1; // Player not found
    }

    public synchronized PlayerStats getPlayerStats(String playerId) {
        return playerStatsMap.get(playerId);
    }

    // Example user data
    public static void initializeSampleData() {
        ChessLeaderboard leaderboard = ChessLeaderboard.getInstance();

        // Simulate some player wins
        leaderboard.recordWin("Alice");
        leaderboard.recordWin("Alice");
        leaderboard.recordWin("Alice");

        leaderboard.recordWin("Bob");
        leaderboard.recordWin("Bob");

        leaderboard.recordWin("Charlie");
        leaderboard.recordWin("Charlie");
        leaderboard.recordWin("Charlie");
        leaderboard.recordWin("Charlie");

        leaderboard.recordWin("Diana");

        leaderboard.recordWin("Eve");
        leaderboard.recordWin("Eve");
        leaderboard.recordWin("Eve");

        leaderboard.recordWin("Frank");
        leaderboard.recordWin("Frank");
        leaderboard.recordWin("Frank");
        leaderboard.recordWin("Frank");
        leaderboard.recordWin("Frank");

        leaderboard.recordWin("Grace");
        leaderboard.recordWin("Grace");

        leaderboard.recordWin("Henry");
        leaderboard.recordWin("Henry");
        leaderboard.recordWin("Henry");

        leaderboard.recordWin("Isabelle");

        leaderboard.recordWin("Jack");
        leaderboard.recordWin("Jack");
    }
}
