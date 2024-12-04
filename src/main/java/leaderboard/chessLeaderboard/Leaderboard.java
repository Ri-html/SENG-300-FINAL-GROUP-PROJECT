package leaderboard.chessLeaderboard;

import java.util.*;

public class Leaderboard {
    private static Leaderboard instance = null;
    private Map<String, PlayerStats> playerStatsMap;

    private Leaderboard() {
        playerStatsMap = new HashMap<>();
    }

    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
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
}
