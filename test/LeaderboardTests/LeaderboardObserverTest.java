package LeaderboardTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import UserAndProfile.*;

public class LeaderboardObserverTest {
/*
    @Before
    public void setup() {
        Leaderboard lb = Leaderboard.getInstance();
        try {
            Leaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {
        }
    }

    @Test
    public void testUpdate() {
        LeaderboardObserver observer = new LeaderboardObserver();
        String gameId = "someGameId";
        String message = "GameEnd\n" + gameId + "\n" + "winner,playerX,loser,playerY\n";

        observer.update(message);

        Leaderboard lb = Leaderboard.getInstance();
        PlayerStats stats = lb.getPlayerStats("playerX");
        assertNotNull(stats);
        assertEquals(1, stats.getTotalWins());

        // Check that loser stats are not incremented
        PlayerStats loserStats = lb.getPlayerStats("playerY");
        assertNull(loserStats); // never recorded a win for playerY
    }

    @Test
    public void testUpdateNoWinnerLine() {
        LeaderboardObserver observer = new LeaderboardObserver();

        // If message doesn't contain winner line properly
        String message = "GameEnd\nsomeGameId\nnoProperWinnerLine\n";
        observer.update(message);

        Leaderboard lb = Leaderboard.getInstance();
        // No winner recorded
        assertEquals(-1, lb.getPlayerRank("playerX"));
    }

 */
}
