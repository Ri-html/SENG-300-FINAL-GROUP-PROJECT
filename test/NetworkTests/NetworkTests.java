package NetworkTests;

import UserAndProfile.*;
import network.Network;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;


/**
 * tests for Network.java
 */
public class NetworkTests {
private Network network;

    /**
     * Setup function to run before every test
     */
    @Before
    public void setup() {

    User user1 = new User("Rob", "rob@email.com","aaa");
    User user2 = new User("Joe", "Joe@email.com","aaa");
    User user3 = new User("Stacy", "Stacy@email.com","aaa");
    user1.getPlayerProfile().getChessProfile().setScoreRank(1000);
    user2.getPlayerProfile().getChessProfile().setScoreRank(1000);
    user3.getPlayerProfile().getChessProfile().setScoreRank(2000);

    user1.getPlayerProfile().getConnectFourProfile().setScoreRank(1000);
    user2.getPlayerProfile().getConnectFourProfile().setScoreRank(2000);
    user3.getPlayerProfile().getConnectFourProfile().setScoreRank(2000);

    user1.getPlayerProfile().getTicTacToeProfile().setScoreRank(2000);
    user2.getPlayerProfile().getTicTacToeProfile().setScoreRank(1000);
    user3.getPlayerProfile().getTicTacToeProfile().setScoreRank(2000);

    List<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
    users.add(user3);

    network = new Network(users);
    network.addChessGame(user1);
    network.addChessGame(user2);
    network.addConnectFourGame(user2);
    network.addConnectFourGame(user3);
    network.addTictactoeGame(user1);
    network.addTictactoeGame(user3);

    network.addChessQueue(user3);
    network.addConnectFourQueue(user1);
    network.addTictactoeQueue(user2);
}

    /**
     * test for getPlayers
     */
    @Test
    public void getPlayersTest() {
    int expected = 3;
    int actual = network.getPlayers().size();
    assertEquals(expected, actual);
}

    /**
     * test for getChessGame
     */
    @Test
    public void getChessGameTest() {
    int expected = 2;
    String expected1 = "Rob";
    String expected2 = "Joe";

    List<User> chessUsers = network.getChessGame();
    int actual = chessUsers.size();
    String actual1 = chessUsers.get(0).getUsername();
    String actual2 = chessUsers.get(1).getUsername();

    assertEquals(expected, actual);
    assertEquals(expected1, actual1);
    assertEquals(expected2, actual2);
}
    /**
     * test for getConnectFourGame
     */
    @Test
    public void getConnectFourGameTest() {
        int expected = 2;
        String expected1 = "Joe";
        String expected2 = "Stacy";

        List<User> connectFourUsers = network.getConnectFourGame();
        int actual = connectFourUsers.size();
        String actual1 = connectFourUsers.get(0).getUsername();
        String actual2 = connectFourUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * test for getTictactoeGame
     */
    @Test
    public void getTictactoeGameTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Stacy";

        List<User> TictactoeUsers = network.getTictactoeGame();
        int actual = TictactoeUsers.size();
        String actual1 = TictactoeUsers.get(0).getUsername();
        String actual2 = TictactoeUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * test for getChessQueue
     */
    @Test
    public void getChessQueueTest() {
        int expected = 1;
        String expected1 = "Stacy";

        List<User> chessQueueUsers = network.getChessQueue();
        int actual = chessQueueUsers.size();
        String actual1 = chessQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }
    /**
     * test for getConnectFOurQueue
     */
    @Test
    public void getConnectFourQueueTest() {
        int expected = 1;
        String expected1 = "Rob";

        List<User> connectFourQueueUsers = network.getConnectFourQueue();
        int actual = connectFourQueueUsers.size();
        String actual1 = connectFourQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }

    /**
     * test for getTictactoeQueue
     */
    @Test
    public void getTictactoeQueueTest() {
        int expected = 1;
        String expected1 = "Joe";

        List<User> tictactoeQueueUsers = network.getTictactoeQueue();
        int actual = tictactoeQueueUsers.size();
        String actual1 = tictactoeQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }
    /**
     * test for findChessRank
     */
    @Test
    public void findChessRankTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Joe";

        List<User> chessRank = network.findChessRank(1000);
        int actual = chessRank.size();
        String actual1 = chessRank.get(0).getUsername();
        String actual2 = chessRank .get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    /**
     * test for findConnectFourRank
     */
    @Test
    public void findConnectFourRankTest() {
        int expected = 2;
        String expected1 = "Joe";
        String expected2 = "Stacy";

        List<User> connectFourRank = network.findConnectFourRank(2000);
        int actual = connectFourRank.size();
        String actual1 = connectFourRank.get(0).getUsername();
        String actual2 = connectFourRank .get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    /**
     * test for findTictactoeRank
     */
    @Test
    public void findTictactoeRankTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Stacy";

        List<User> TictactoeRank = network.findTictactoeRank(2000);
        int actual = TictactoeRank.size();
        String actual1 = TictactoeRank.get(0).getUsername();
        String actual2 = TictactoeRank .get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * test for findChessQueueRank
     */
    @Test
    public void findChessQueueRankTest() {
        int expected = 1;
        String expected1 = "Stacy";

        List<User> chessQueueRank = network.findChessRankQueue(2000);
        int actual = chessQueueRank.size();
        String actual1 = chessQueueRank.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }
    /**
     * test for findConnectFourQueueRank
     */
    @Test
    public void findConnectFourQueueRankTest() {
        int expected = 1;
        String expected1 = "Rob";

        List<User> connectFourQueueRank = network.findConnectFourRankQueue(1000);
        int actual = connectFourQueueRank.size();
        String actual1 = connectFourQueueRank.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }

    /**
     * test for findTictactoeQueueRank
     */
    @Test
    public void findTictactoeQueueRankTest() {
        int expected = 1;
        String expected1 = "Joe";

        List<User> tictactoeQueueRank = network.findTictactoeRankQueue(1000);
        int actual = tictactoeQueueRank.size();
        String actual1 = tictactoeQueueRank.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }

    /**
     * test for addChessGame
     */
    @Test

    public void addChessGameTest() {
        int expected = 3;
        String expected1 = "Rob";
        String expected2 = "Joe";
        String expected3 = "Josh";
        User user1 = new User("Josh","Josh@email.com","aaa");
        network.addChessGame(user1);

        List<User> chessUsers = network.getChessGame();
        int actual = chessUsers.size();
        String actual1 = chessUsers.get(0).getUsername();
        String actual2 = chessUsers.get(1).getUsername();
        String actual3 = chessUsers.get(2).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

        network.removeChessGame(user1);

    }

    /**
     * test for addConnectFourGame
     */
    @Test

    public void addConnectFourGameTest() {
        int expected = 3;
        String expected1 = "Joe";
        String expected2 = "Stacy";
        String expected3 = "Jeff";
        User user1 = new User( "Jeff","Jeff@email.com","aaa");
        network.addConnectFourGame(user1);

        List<User> connectFourUsers = network.getConnectFourGame();
        int actual = connectFourUsers.size();
        String actual1 = connectFourUsers.get(0).getUsername();
        String actual2 = connectFourUsers.get(1).getUsername();
        String actual3 = connectFourUsers.get(2).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

        network.removeConnectFourGame(user1);
    }
    /**
     * test for addTictactoeGame
     */
    @Test

    public void addTictactoeGameTest() {
        int expected = 3;
        String expected1 = "Rob";
        String expected2 = "Stacy";
        String expected3 = "Adam";
        User user1 = new User("Adam","Adam@email.com","aaa");
        network.addTictactoeGame(user1);

        List<User> tictactoeUsers = network.getTictactoeGame();
        int actual = tictactoeUsers.size();
        String actual1 = tictactoeUsers.get(0).getUsername();
        String actual2 = tictactoeUsers.get(1).getUsername();
        String actual3 = tictactoeUsers.get(2).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

        network.removeTictactoeGame(user1);

    }

    /**
     * test for addChessQueue
     */
    @Test

    public void addChessQueueTest() {
        int expected = 2;
        String expected1 = "Stacy";
        String expected2 = "Kory";
        User user1 = new User("Kory","Kory@email.com","aaa");
        network.addChessQueue(user1);

        List<User> chessQueueUsers = network.getChessQueue();
        int actual = chessQueueUsers.size();

        String actual1 = chessQueueUsers.get(0).getUsername();
        String actual2 = chessQueueUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

        network.removeChessQueue(user1);
    }
    /**
     * test for addConnectFourQueue
     */
    @Test

    public void addConnectFourQueueTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Steve";
        User user1 = new User("Steve","Steve@email.com","aaa");
        network.addConnectFourQueue(user1);

        List<User> connectFourQueueUsers = network.getConnectFourQueue();
        int actual = connectFourQueueUsers.size();

        String actual1 = connectFourQueueUsers.get(0).getUsername();
        String actual2 = connectFourQueueUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

        network.removeConnectFourQueue(user1);
    }
    /**
     * test for addTictactoeQueue
     */
    @Test

    public void addTictactoeQueueTest() {
        int expected = 2;
        String expected1 = "Joe";
        String expected2 = "Kyle";
        User user1 = new User("Kyle","Kyle@email.com","aaa");
        network.addTictactoeQueue(user1);

        List<User> tictactoeQueueUsers = network.getTictactoeQueue();
        int actual = tictactoeQueueUsers.size();

        String actual1 = tictactoeQueueUsers.get(0).getUsername();
        String actual2 = tictactoeQueueUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

        network.removeTictactoeQueue(user1);

    }

    /**
     * test for removeChessGame
     */
    @Test
    public void removeChessGameTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Joe";

        User user1 = new User("Martin", "Martin@email.com","aaa");

        network.addChessGame(user1);
        network.removeChessGame(user1);
        List<User> chessUsers = network.getChessGame();

        int actual = chessUsers.size();

        String actual1 = chessUsers.get(0).getUsername();
        String actual2 = chessUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    /**
     * test for removeConnectFourGame
     */
    @Test
    public void removeConnectFourGameTest() {

        int expected = 2;
        String expected1 = "Joe";
        String expected2 = "Stacy";
        User user1 = new User("Richard","Richard@email.com","aaa");
        network.addConnectFourGame(user1);
        network.removeConnectFourGame(user1);

        List<User> connectFourUsers = network.getConnectFourGame();
        int actual = connectFourUsers.size();
        String actual1 = connectFourUsers.get(0).getUsername();
        String actual2 = connectFourUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    /**
     * test for removeTictactoeGame
     */
    @Test
    public void removeTictactoeGameTest() {
        int expected = 2;
        String expected1 = "Rob";
        String expected2 = "Stacy";
        User user1 = new User("Sarah","Sarah@email.com","aaa");
        network.addTictactoeGame(user1);
        network.removeTictactoeGame(user1);

        List<User> tictactoeUsers = network.getTictactoeGame();
        int actual = tictactoeUsers.size();
        String actual1 = tictactoeUsers.get(0).getUsername();
        String actual2 = tictactoeUsers.get(1).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

    }
    /**
     * test for removeChessQueue
     */
    @Test
    public void removeChessQueueTest() {

        int expected = 1;
        String expected1 = "Stacy";
        User user1 = new User("Daniel","Daniel@email.com","aaa");
        network.addChessQueue(user1);
        network.removeChessQueue(user1);

        List<User> chessQueueUsers = network.getChessQueue();
        int actual = chessQueueUsers.size();

        String actual1 = chessQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);

    }
    /**
     * test for removeConnectFourQueue
     */
    @Test
    public void removeConnectFourQueueTest() {
        int expected = 1;
        String expected1 = "Rob";
        User user1 = new User("James","James@email.com","aaa");
        network.addConnectFourQueue(user1);
        network.removeConnectFourQueue(user1);

        List<User> connectFourQueueUsers = network.getConnectFourQueue();
        int actual = connectFourQueueUsers.size();

        String actual1 = connectFourQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);

    }

    /**
     * test for removeTictactoeQueue
     */
    @Test
    public void removeTictactoeQueueTest() {
        int expected = 1;
        String expected1 = "Joe";
        User user1 = new User("Ashley","Ashley@email.com","aaa");
        network.addTictactoeQueue(user1);
        network.removeTictactoeQueue(user1);

        List<User> tictactoeQueueUsers = network.getTictactoeQueue();
        int actual = tictactoeQueueUsers.size();

        String actual1 = tictactoeQueueUsers.get(0).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);

    }

    /**
     * test for addUser
     */
    @Test
    public void addUserTest() {
        int expected = 4;
        String expected1 = "Rob";
        String expected2 = "Joe";
        String expected3 = "Stacy";
        String expected4 = "Lisa";
        User user1 = new User("Lisa","Lisa@email.com","aaa");
        network.addUser(user1);
        List<User> allUsers = network.getPlayers();
        int actual = allUsers.size();

        String actual1 = allUsers.get(0).getUsername();
        String actual2 = allUsers.get(1).getUsername();
        String actual3 = allUsers.get(2).getUsername();
        String actual4 = allUsers.get(3).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);

        network.removeUser(user1);
    }

    /**
     * test for removeUser
     */
    @Test
    public void removeUsertest() {
        int expected = 3;
        String expected1 = "Rob";
        String expected2 = "Joe";
        String expected3 = "Stacy";
        User user1 = new User("Nathan","Nathan@email.com","aaa");
        network.addUser(user1);
        network.removeUser(user1);
        List<User> allUsers = network.getPlayers();
        int actual = allUsers.size();

        String actual1 = allUsers.get(0).getUsername();
        String actual2 = allUsers.get(1).getUsername();
        String actual3 = allUsers.get(2).getUsername();

        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

    }



}
