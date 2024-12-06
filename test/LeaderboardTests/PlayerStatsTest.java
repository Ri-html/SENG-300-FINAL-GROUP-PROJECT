package LeaderboardTests;

import leaderboard.chessLeaderboard.PlayerStats;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerStatsTest {

    @Test
    public void testInitialValues() {
        PlayerStats stats = new PlayerStats("player1");
        assertEquals("player1", stats.getPlayerId());
        assertEquals(0, stats.getTotalWins());
    }

    @Test
    public void testIncrementWins() {
        PlayerStats stats = new PlayerStats("player1");
        stats.incrementWins();
        assertEquals(1, stats.getTotalWins());
        stats.incrementWins();
        assertEquals(2, stats.getTotalWins());
    }

    @Test
    public void testCompareTo() {
        PlayerStats p1 = new PlayerStats("p1");
        PlayerStats p2 = new PlayerStats("p2");

        // p1:0 wins, p2:0 wins
        assertEquals(0, p1.compareTo(p2));

        p1.incrementWins();
        // p1 should come before p2
        // compareTo returns a positive if p2 has fewer wins
        assertTrue(p1.compareTo(p2) < 0); 
        p2.incrementWins();
        p2.incrementWins();
        // p2 now has 2 wins, p1 has 1 win
        // p1.compareTo(p2) should now return > 0 (p2 is "greater")
        assertTrue(p1.compareTo(p2) > 0);
    }
}
