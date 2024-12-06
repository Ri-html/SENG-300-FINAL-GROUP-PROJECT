package LeaderboardTests.Chess;
import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.chessLeaderboard.PlayerStats;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class ChessLeaderboardTest {

    @Before
    public void setUp() {
        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        try {
            ChessLeaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {

        }
    }

    @Before
    public void resetLeaderboard() {
        try {
            // Access the private static `instance` field
            Field instanceField = ChessLeaderboard.class.getDeclaredField("instance");
            instanceField.setAccessible(true);              // Allow access to the private field
            instanceField.set(null, null);                  // Reset the singleton instance to null
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reset ChessLeaderboard singleton", e);
        }
    }

    @Test
    public void testRecordWinAndGetPlayerStats() {
        ChessLeaderboard lb = ChessLeaderboard.getInstance();

        lb.recordWin("playerA");
        assertEquals(1, lb.getPlayerStats("playerA").getTotalWins());

        lb.recordWin("playerA");
        assertEquals(2, lb.getPlayerStats("playerA").getTotalWins());

        lb.recordWin("playerB");
        assertEquals(1, lb.getPlayerStats("playerB").getTotalWins());
    }

    @Test
    public void testGetTopPlayers() {
        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        lb.recordWin("playerA");
        lb.recordWin("playerA");
        lb.recordWin("playerB");
        lb.recordWin("playerC");
        lb.recordWin("playerC");
        lb.recordWin("playerC");

        // playerC:3 wins, playerA:2 wins, playerB:1 win
        List<PlayerStats> top2 = lb.getTopPlayers(2);
        assertEquals(2, top2.size());
        assertEquals("playerC", top2.get(0).getPlayerId()); // Most wins should be first
        assertEquals("playerA", top2.get(1).getPlayerId());

        List<PlayerStats> top10 = lb.getTopPlayers(10);
        assertEquals(3, top10.size());
    }

    @Test
    public void testGetPlayerRank() {
        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        lb.recordWin("p1");
        lb.recordWin("p1");
        lb.recordWin("p2");
        lb.recordWin("p3");
        lb.recordWin("p3");
        lb.recordWin("p3");

        // p3:3 wins, p1:2 wins, p2:1 win
        assertEquals(1, lb.getPlayerRank("p3"));
        assertEquals(2, lb.getPlayerRank("p1"));
        assertEquals(3, lb.getPlayerRank("p2"));
        assertEquals(-1, lb.getPlayerRank("unknown")); 
    }
}
