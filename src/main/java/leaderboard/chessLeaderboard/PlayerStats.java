package leaderboard.chessLeaderboard;

public class PlayerStats implements Comparable<PlayerStats> {
    private String playerId;
    private int totalWins;
    private int totalLosses;

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

    @Override
    public int compareTo(PlayerStats other) {
        // Sorts in descending order of totalWins
        return Integer.compare(other.totalWins, this.totalWins);
    }
}
