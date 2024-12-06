package UserAndProfile;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourProfile extends AbstractGameProfile {

    // List to store all game records
    private List<GameRecord> gameRecords = new ArrayList<>();


    // Constructor
    public ConnectFourProfile() {
        super();
    }

    // Updates the game history with details about the last game
    @Override
    public void updateGameHistory(String opponent, String result, int score) {
        setLastOpponent(opponent);
        setLastGameResult(result);
        setLastGameScore(score);

        // Update total games and scores
        setTotalGamesPlayed(getTotalGamesPlayed() + 1);
        setTotalScore(getTotalScore() + score);
    }

    public void updateGameHistoryReal(String opponent, String result, int score) {
        setLastOpponent(opponent);
        setLastGameResult(result);
        setLastGameScore(getTotalScore() + score);

        // Update total games and scores
        setTotalGamesPlayed(getTotalGamesPlayed() + 1);
        setTotalScore(getTotalScore() + score);
    }

    // Updates player status, including total games, score, and win rate
    @Override
    public void updatePlayerStatus(int totalGames, int score, double winRate) {
        setTotalGamesPlayed(totalGames);
        setTotalScore(score);
        setWinRate(winRate);
    }

    // Updates the win/loss record with total wins and losses
    @Override
    public void updateWinLoseRecord(int wins, int losses) {
        setTotalWins(wins);
        setTotalLosses(losses);

        // Update win rate calculation
        if (wins + losses > 0) {
            setWinRate((double) wins / (wins + losses) * 100);
        } else {
            setWinRate(0.0);
        }
    }

    // Updates the ranking based on score and win rate ranks
    @Override
    public void updateRanking(int scoreRank, int winRateRank) {
        setScoreRank(scoreRank);
        setWinRateRank(winRateRank);
    }


    // Adds a game record manually (optional method)

    public List<GameRecord> getAllGameRecords() {
        return gameRecords;
    }

    // Adds a game record manually (optional method)

    public void addGameRecord(GameRecord record) {
        gameRecords.add(record);
        setLastOpponent(record.getOpponent());
        setLastGameResult(record.getResult());
        setLastGameScore(record.getScore());
    }

    // Prints all game records (optional, for debugging)
    public void printGameRecords() {
        for (GameRecord record : gameRecords) {
            System.out.println(record);
        }
    }
}
