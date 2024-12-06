package LeaderboardTests.TicTacToe;

import leaderboard.tictactoeLeaderboard.PlayerStats;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboardObserver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TicTacToeLeaderboardObserverTest {

    @Before
    public void setup() {
        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
        try {
            TicTacToeLeaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {
        }
    }

    @Test
    public void testUpdate() {
        TicTacToeLeaderboardObserver observer = new TicTacToeLeaderboardObserver();
        String gameId = "someGameId";
        String message = "GameEnd\n" + gameId + "\n" + "winner,playerX,loser,playerY\n";

        observer.update(message);

        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
        PlayerStats stats = lb.getPlayerStats("playerX");
        assertNotNull(stats);
        assertEquals(1, stats.getTotalWins());

        // Check that loser stats are not incremented
        PlayerStats loserStats = lb.getPlayerStats("playerY");
        assertEquals(1, loserStats.getTotalLosses()); // never recorded a win for playerY
    }                                                           // Will be incremented to 1 for losses from 0

    @Test
    public void testUpdateNoWinnerLine() {
        TicTacToeLeaderboardObserver observer = new TicTacToeLeaderboardObserver();

        // If message doesn't contain winner line properly
        String message = "GameEnd\nsomeGameId\nnoProperWinnerLine\n";
        observer.update(message);

        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
        // No winner recorded
        assertEquals(-1, lb.getPlayerRank("playerX"));
    }
}
