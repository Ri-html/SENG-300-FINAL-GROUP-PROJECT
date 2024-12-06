package LeaderboardTests.Connect4;

import leaderboard.connect4Leaderboard.Connect4Leaderboard;
import leaderboard.connect4Leaderboard.Connect4LeaderboardObserver;
import leaderboard.connect4Leaderboard.PlayerStats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class Connect4LeaderboardObserverTest {

    @Before
    public void setup() {
        Connect4Leaderboard lb = Connect4Leaderboard.getInstance();
        try {
            Connect4Leaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {
        }
    }

    @Test
    public void testUpdate() {
        Connect4LeaderboardObserver observer = new Connect4LeaderboardObserver();
        String gameId = "someGameId";
        String message = "GameEnd\n" + gameId + "\n" + "winner,playerX,loser,playerY\n";

        observer.update(message);

        Connect4Leaderboard lb = Connect4Leaderboard.getInstance();             // Ws/Ls start accumulating from 0
        PlayerStats stats = lb.getPlayerStats("playerX");
        assertNotNull(stats);
        assertEquals(1, stats.getTotalWins());

        // Check that loser stats are not incremented
        PlayerStats loserStats = lb.getPlayerStats("playerY");
        assertEquals(1, loserStats.getTotalLosses()); // never recorded a win for playerY
    }                                                           // Instead losses would be updated to 1 from 0

    @Test
    public void testUpdateNoWinnerLine() {
        Connect4LeaderboardObserver observer = new Connect4LeaderboardObserver();

        // If message doesn't contain winner line properly
        String message = "GameEnd\nsomeGameId\nnoProperWinnerLine\n";
        observer.update(message);

        Connect4Leaderboard lb = Connect4Leaderboard.getInstance();
        // No winner recorded
        assertEquals(-1, lb.getPlayerRank("playerX"));
    }
}
