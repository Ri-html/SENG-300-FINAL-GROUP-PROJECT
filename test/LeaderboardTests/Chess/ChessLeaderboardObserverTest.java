package LeaderboardTests.Chess;

import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.chessLeaderboard.ChessLeaderboardObserver;
import leaderboard.chessLeaderboard.PlayerStats;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChessLeaderboardObserverTest {

    @Before
    public void setup() {
        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        try {
            ChessLeaderboard.class.getDeclaredMethod("clearAll").invoke(lb);
        } catch (Exception e) {
        }
    }

    @Test
    public void testUpdate() {
        ChessLeaderboardObserver observer = new ChessLeaderboardObserver();
        String gameId = "someGameId";
        String message = "GameEnd\n" + gameId + "\n" + "winner,playerX,loser,playerY\n";

        observer.update(message);

        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        PlayerStats stats = lb.getPlayerStats("playerX");
        assertNotNull(stats);
        assertEquals(1, stats.getTotalWins());

        // Check that loser stats are not incremented
        PlayerStats loserStats = lb.getPlayerStats("playerY");
        assertNull(loserStats); // never recorded a win for playerY
    }

    @Test
    public void testUpdateNoWinnerLine() {
        ChessLeaderboardObserver observer = new ChessLeaderboardObserver();

        // If message doesn't contain winner line properly
        String message = "GameEnd\nsomeGameId\nnoProperWinnerLine\n";
        observer.update(message);

        ChessLeaderboard lb = ChessLeaderboard.getInstance();
        // No winner recorded
        assertEquals(-1, lb.getPlayerRank("playerX"));
    }
}
