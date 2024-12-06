package network;

import UserAndProfile.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Network.java
 * Network class stub
 * Keeps tracks of total online users and users in game or in a queue
 * Can search for users of same rank (All users or in queue)
 */
public class Network {
    // List of all users on network
    private List<User> players;
    // List of all users in a chess game
    private List<User> chessGame;
    // List of all users in a connect four game
    private List<User> connectFourGame;
    // List of all users in a tictactoe game
    private List<User> tictactoeGame;
    // List of users queueing for a chess game
    private List<User> chessQueue;
    // List of users queueing for a connect four game
    private List<User> connectFourQueue;
    // List of users queueing for a tictactoe game
    private List<User> tictactoeQueue;

    /**
     * Constructor
     * @param players
     */
    public Network(List<User> players) {
        this.players = players;
        this.chessGame = new ArrayList<>();
        this.connectFourGame = new ArrayList<>();
        this.tictactoeGame = new ArrayList<>();
        this.chessQueue = new ArrayList<>();
        this.connectFourQueue = new ArrayList<>();
        this.tictactoeQueue = new ArrayList<>();
    }

    /**
     * Returns list of players on network
     * @return (List<User>)
     */
    public List<User> getPlayers() {
        return players;
    }

    /**
     * Returns list of players in a chess game
     * @return (List<User>)
     */
    public List<User> getChessGame() {
        return chessGame;
    }

    /**
     * Returns list of players in a chess queue
     * @return (List<User>)
     */
    public List<User> getChessQueue() {
        return chessQueue;
    }

    /**
     * Returns list of players in a connect four game
     * @return (List<User>)
     */
    public List<User> getConnectFourGame(){
        return connectFourGame;
    }

    /**
     * Returns list of players in a connect four queue
     * @return (List<User>)
     */
    public List<User> getConnectFourQueue() {
        return connectFourQueue;
    }

    /**
     * Returns list of players in a tictactoe game
     * @return (List<User>)
     */
    public List<User> getTictactoeGame() {
        return tictactoeGame;
    }

    /**
     * Returns list of players in a tictactoe queue
     * @return (List<User>)
     */
    public List<User> getTictactoeQueue() {
        return tictactoeQueue;
    }

    /**
     * Returns list of players in of same chess rank (all players)
     * @return (List<User>)
     */
    public List<User> findChessRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getChessProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Returns list of players in of same chess rank (in queue)
     * @return (List<User>)
     */
    public List<User> findChessRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : chessQueue) {
            if (user.getPlayerProfile().getChessProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Returns list of players in of same connect four rank (all players)
     * @return (List<User>)
     */
    public List<User> findConnectFourRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getConnectFourProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Returns list of players in of same connect four rank (in queue)
     * @return (List<User>)
     */
    public List<User> findConnectFourRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : connectFourQueue) {
            if (user.getPlayerProfile().getConnectFourProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Returns list of players in of same tictactoe rank (all players)
     * @return (List<User>)
     */
    public List<User> findTictactoeRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getTicTacToeProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Returns list of players in of same tictactoe rank (in queue)
     * @return (List<User>)
     */
    public List<User> findTictactoeRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : tictactoeQueue) {
            if (user.getPlayerProfile().getTicTacToeProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    /**
     * Add user to chess queue
     * @param user (User)
     */
    public void addChessQueue(User user) {
        chessQueue.add(user);
    }

    /**
     * Add user to chess game
     * @param user (User)
     */
    public void addChessGame(User user) { chessGame.add(user); }

    /**
     * Add user to connect four queue
     * @param user (User)
     */
    public void addConnectFourQueue(User user) {
        connectFourQueue.add(user);
    }

    /**
     * Add user to connect four game
     * @param user (User)
     */
    public void addConnectFourGame(User user) {
        connectFourGame.add(user);
    }

    /**
     * Add user to tictactoe queue
     * @param user (User)
     */
    public void addTictactoeQueue(User user) {
        tictactoeQueue.add(user);
    }

    /**
     * Add user to tictactoe game
     * @param user (User)
     */
    public void addTictactoeGame(User user) {
        tictactoeGame.add(user);
    }

    /**
     * Add user to online network
     * @param user (User)
     */
    public void addUser(User user) {
        players.add(user);
    }

    /**
     * Remove user from chess queue
     * @param user (User)
     */
    public void removeChessQueue(User user) { chessQueue.remove(user); }
    /**
     * Remove user from chess game
     * @param user (User)
     */
    public void removeChessGame(User user) { chessGame.remove(user); }
    /**
     * Remove user from connect four queue
     * @param user (User)
     */
    public void removeConnectFourQueue(User user) { connectFourQueue.remove(user); }
    /**
     * Remove user from connect four queue
     * @param user (User)
     */
    public void removeConnectFourGame (User user) { connectFourGame.remove(user); }
    /**
     * Remove user from tictactoe queue
     * @param user (User)
     */
    public void removeTictactoeQueue(User user) { tictactoeQueue.remove(user); }
    /**
     * Remove user from tictactoe game
     * @param user (User)
     */
    public void removeTictactoeGame (User user) { tictactoeGame.remove(user); }
    /**
     * Remove user from network
     * @param user (User)
     */
    public void removeUser (User user) { players.remove(user);}

}

