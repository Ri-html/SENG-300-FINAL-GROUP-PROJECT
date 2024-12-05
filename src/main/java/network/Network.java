package network;

import UserAndProfile.*;

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

    // Constructor
    public Network(List<User> players) {
        this.players = players;
        this.chessGame = new ArrayList<>();
        this.connectFourGame = new ArrayList<>();
        this.tictactoeGame = new ArrayList<>();
        this.chessQueue = new ArrayList<>();
        this.connectFourQueue = new ArrayList<>();
        this.tictactoeQueue = new ArrayList<>();
    }

    // Getter for all players on network
    public List<User> getPlayers() {
        return players;
    }

    // Getter for players in a chess game
    public List<User> getChessGame() {
        return chessGame;
    }

    // Getter for players in a chess queue
    public List<User> getChessQueue() {
        return chessQueue;
    }

    // Getter for players in a connect four game
    public List<User> getConnectFourGame(){
        return connectFourGame;
    }

    // Getter for players in a connect four queue
    public List<User> getConnectFourQueue() {
        return connectFourQueue;
    }

    // Getter for players in a tictactoe game
    public List<User> getTictactoeGame() {
        return tictactoeGame;
    }

    // Getter for players in a tictactoe queue
    public List<User> getTictactoeQueue() {
        return tictactoeQueue;
    }

    // Find chess players of same rank that are online
    public List<User> findChessRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getChessProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Find chess players of same rank that are queued for a game
    public List<User> findChessRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : chessQueue) {
            if (user.getPlayerProfile().getChessProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Find Connect Four players of same rank that are online
    public List<User> findConnectFourRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getConnectFourProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Find Connect Four players of same rank that are queued for a game
    public List<User> findConnectFourRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : connectFourQueue) {
            if (user.getPlayerProfile().getConnectFourProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Find Tictactoe players of same rank that are online
    public List<User> findTictactoeRank(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : players) {
            if (user.getPlayerProfile().getTicTacToeProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Find Tictactoe players of same rank that are queued for a game
    public List<User> findTictactoeRankQueue(int rank) {
        List<User> matchedUsers = new ArrayList<>();
        for (User user : tictactoeQueue) {
            if (user.getPlayerProfile().getTicTacToeProfile().getScoreRank() == rank) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    // Add user to chess queue
    public void addChessQueue(User user) {
        chessQueue.add(user);
    }

    // Add user to chess game
    public void addChessGame(User user) { chessGame.add(user); }

    // Add user to Connect Four queue
    public void addConnectFourQueue(User user) {
        connectFourQueue.add(user);
    }

    // Add user to Connect Four game
    public void addConnectFourGame(User user) {
        connectFourGame.add(user);
    }

    // Add user to Tictactoe queue
    public void addTictactoeQueue(User user) {
        tictactoeQueue.add(user);
    }

    // Add user to Tictactoe game
    public void addTictactoeGame(User user) {
        tictactoeGame.add(user);
    }

    // Add user to players online
    public void addUser(User user) {
        players.add(user);
    }

    // Remove user from chess queue
    public void removeChessQueue(User user) { chessQueue.remove(user); }
    // Remove user from chess game
    public void removeChessGame(User user) { chessGame.remove(user); }
    // Remove user from connect four queue
    public void removeConnectFourQueue(User user) { connectFourQueue.remove(user); }
    // Remove user from connect four game
    public void removeConnectFourGame (User user) { connectFourGame.remove(user); }
    // Remove user from tictactoe queue
    public void removeTictactoeQueue(User user) { tictactoeQueue.remove(user); }
    // Remove user from tictactoe game
    public void removeTictactoeGame (User user) { tictactoeGame.remove(user); }
    // Remove user from network
    public void removeUser (User user) { players.remove(user);}

}

