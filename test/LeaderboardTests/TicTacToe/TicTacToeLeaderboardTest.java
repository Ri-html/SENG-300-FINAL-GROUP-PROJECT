package LeaderboardTests.TicTacToe;

import leaderboard.matchmaking_LB.Player;
import leaderboard.tictactoeLeaderboard.PlayerStats;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TicTacToeLeaderboardTest {

    @Before
    public void setUp() {
        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
        try {
            TicTacToeLeaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {

        }
    }

    @Test
    public void testRecordWinAndGetPlayerStats() {
        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();

        lb.recordWin("playerA");
        assertEquals(1, lb.getPlayerStats("playerA").getTotalWins());

        lb.recordWin("playerA");
        assertEquals(2, lb.getPlayerStats("playerA").getTotalWins());

        lb.recordWin("playerB");
        assertEquals(1, lb.getPlayerStats("playerB").getTotalWins());
    }

    @Test
    public void testGetTopPlayers() {
        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
        lb.recordWin("playerA");
        lb.recordWin("playerA");
        lb.recordWin("playerB");
        lb.recordWin("playerC");
        lb.recordWin("playerC");
        lb.recordWin("playerC");

        // playerC:3 wins, playerA:2 wins, playerB:1 win

        List<PlayerStats> top2 = lb.getTopPlayers();
        assertEquals(2, top2.size());
        assertEquals("playerC", top2.get(0).getPlayerId()); // Most wins should be first
        assertEquals("playerA", top2.get(1).getPlayerId());

        List<PlayerStats> top10 = lb.getTopPlayers();
        assertEquals(3, top10.size());
    }



    @Test
    public void testGetPlayerRank() {
        TicTacToeLeaderboard lb = TicTacToeLeaderboard.getInstance();
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
