package authProfile;
import java.util.Date;

public abstract class AbstractGameProfile {

    // Game history fields
    private String lastOpponent;
    private String lastGameResult;
    private int lastGameScore;

    // Player status fields
    private int totalGamesPlayed;
    private int totalScore;
    private double winRate;

    // Win/Lose record fields
    private int totalWins;
    private int totalLosses;

    // Ranking fields
    private int scoreRank;
    private int winRateRank;

    // Constructor
    public AbstractGameProfile() {
        // Initialize with default values if needed
    }

    // Abstract methods to be implemented by subclasses (specific to each game)
    public abstract void updateGameHistory(String opponent, String result, int score);
    public abstract void updatePlayerStatus(int totalGames, int score, double winRate);
    public abstract void updateWinLoseRecord(int wins, int losses);
    public abstract void updateRanking(int scoreRank, int winRateRank);

    // Getters and setters for each field

    // Game history getters/setters
    public String getLastOpponent() {
        return lastOpponent;
    }

    public void setLastOpponent(String lastOpponent) {
        this.lastOpponent = lastOpponent;
    }

    public String getLastGameResult() {
        return lastGameResult;
    }

    public void setLastGameResult(String lastGameResult) {
        this.lastGameResult = lastGameResult;
    }

    public int getLastGameScore() {
        return lastGameScore;
    }

    public void setLastGameScore(int lastGameScore) {
        this.lastGameScore = lastGameScore;
    }

    // Player status getters/setters
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    // Win/Lose record getters/setters
    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }

    // Ranking getters/setters
    public int getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(int scoreRank) {
        this.scoreRank = scoreRank;
    }

    public int getWinRateRank() {
        return winRateRank;
    }

    public void setWinRateRank(int winRateRank) {
        this.winRateRank = winRateRank;
    }


}
