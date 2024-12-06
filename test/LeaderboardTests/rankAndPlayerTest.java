package LeaderboardTests;
import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.connect4Leaderboard.Connect4Leaderboard;
import leaderboard.connect4Leaderboard.PlayerStats;
import leaderboard.matchmaking_LB.Match;
import leaderboard.matchmaking_LB.MatchmakingSystem;
import leaderboard.matchmaking_LB.Player;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class rankAndPlayerTest {

    @Before
    public void resetTicTacToeLeaderboard() {
        try {
            // Access the private static `instance` field
            Field instanceField = TicTacToeLeaderboard.class.getDeclaredField("instance");
            instanceField.setAccessible(true);              // Allow access to the private field
            instanceField.set(null, null);                  // Reset the singleton instance to null
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reset TicTacToeLeaderboard singleton", e);
        }
    }
    @Before
    public void resetConnect4Leaderboard() {
        try {
            // Access the private static `instance` field
            Field instanceField = Connect4Leaderboard.class.getDeclaredField("instance");
            instanceField.setAccessible(true);              // Allow access to the private field
            instanceField.set(null, null);                  // Reset the singleton instance to null
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reset Connect4Leaderboard singleton", e);
        }
    }

    @Before
    public void resetChessLeaderboard() {
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
    public void playerSkillTest(){
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", -1000);
        Player test3 = new Player("test3", 999999);
        assertEquals(10, test.getSkillLevel());
        assertEquals(-1000, test2.getSkillLevel());
        assertEquals(999999, test3.getSkillLevel());
    }

    @Test
    public void playSetStatusTest(){
        Player test = new Player("test", 10);
        assertEquals("Available", test.getStatus());

        test.setStatus("TEST");
        assertEquals("TEST", test.getStatus());

    }

    @Test
    public void incrementTotalWinsTest(){
        Player test = new Player("test", 10);
        String testID = test.getPlayerID();
        PlayerStats PS = new PlayerStats(testID);

        assertEquals(0, PS.getTotalWins());
        PS.incrementWins();
        assertEquals(1, PS.getTotalWins());
        PS.incrementWins();
        assertEquals(2, PS.getTotalWins());
        assertEquals(0, PS.getTotalLosses());
    }

    @Test
    public void incrementTotalLossesTest(){
        Player test = new Player("test", 10);
        String testID = test.getPlayerID();
        PlayerStats PS = new PlayerStats(testID);

        assertEquals(0, PS.getTotalLosses());
        PS.incrementLosses();
        assertEquals(1, PS.getTotalLosses());
        PS.incrementLosses();
        assertEquals(2, PS.getTotalLosses());
    }

    @Test
    public void compareToOtherTest(){
        Player test = new Player("test", 10);
        String testID = test.getPlayerID();
        PlayerStats PS = new PlayerStats(testID);

        Player test2 = new Player("test2", 10);
        String testID2 = test2.getPlayerID();
        PlayerStats PS2 = new PlayerStats(testID);

        assertEquals(0, PS.compareTo(PS2));

        PS.incrementWins();
        assertEquals(1, PS.getTotalWins());
        assertEquals(-1, PS.compareTo(PS2));

        PS2.incrementWins();
        PS2.incrementWins();

        assertEquals(1, PS.compareTo(PS2));
    }

    @Test
    public void listPlayersMatchmakingTest(){
        MatchmakingSystem MS = new MatchmakingSystem();
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", 10);
        List<Player> players = new ArrayList<>();
        players.add(test);
        players.add(test2);
        MS.addPlayerToQueue(test);
        MS.addPlayerToQueue(test2);

        assertEquals(players, MS.getAvailablePlayers());

        MS.removePlayerFromQueue(test);
        players.remove(test);
        assertEquals(players, MS.getAvailablePlayers());

    }

    @Test
    public void matchStatusesTest(){
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", 10);

        Match m = new Match(test, test2, "connectFour");
        assertEquals("Pending", m.getStatus());

        m.startMatch();
        assertEquals("In-Progress", m.getStatus());

        m.endMatch("Won", test);
        assertEquals("Completed", m.getStatus());

    }

    @Test
    public void Connect4LeaderboardRanksTest(){
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", 10);
        Connect4Leaderboard cfl = Connect4Leaderboard.getInstance();

        String ID1 = test.getPlayerID();
        String ID2 = test2.getPlayerID();

        cfl.recordWin(ID1);
        assertEquals(1, cfl.getPlayerRank(ID1));
        cfl.recordWin(ID1);
        cfl.recordWin(ID2);
        assertEquals(1, cfl.getPlayerRank(ID1));
        assertEquals(2, cfl.getPlayerRank(ID2));
        assertEquals(-1, cfl.getPlayerRank("playernotfound"));
    }

    @Test
    public void TicTacToeLeaderboardRanksTest(){
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", 10);
        TicTacToeLeaderboard tttl = TicTacToeLeaderboard.getInstance();

        String ID1 = test.getPlayerID();
        String ID2 = test2.getPlayerID();

        tttl.recordWin(ID1);
        assertEquals(1, tttl.getPlayerRank(ID1));
        tttl.recordWin(ID1);
        tttl.recordWin(ID2);
        assertEquals(1, tttl.getPlayerRank(ID1));
        assertEquals(2, tttl.getPlayerRank(ID2));
        assertEquals(-1, tttl.getPlayerRank("test"));
    }
    @Test
    public void ChessLeaderboardRanksTest(){
        Player test = new Player("test", 10);
        Player test2 = new Player("test2", 10);
        ChessLeaderboard cl = ChessLeaderboard.getInstance();

        String ID1 = test.getPlayerID();
        String ID2 = test2.getPlayerID();

        cl.recordWin(ID1);
        assertEquals(1, cl.getPlayerRank(ID1));
        cl.recordWin(ID1);
        cl.recordWin(ID2);
        assertEquals(1, cl.getPlayerRank(ID1));
        assertEquals(2, cl.getPlayerRank(ID2));
        assertEquals(-1, cl.getPlayerRank("test"));
    }
}