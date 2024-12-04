package leaderboard.chessLeaderboard;

public class PlayerStats implements Comparable<PlayerStats> {
    private String playerId;
    private int totalWins;

    public PlayerStats(String playerId) {
        this.playerId = playerId;
        this.totalWins = 0;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void incrementWins() {
        totalWins++;
    }

    @Override
    public int compareTo(PlayerStats other) {
        // Sorts in descending order of totalWins
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
