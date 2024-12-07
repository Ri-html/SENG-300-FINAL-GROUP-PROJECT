package leaderboard.matchmaking_LB;

import java.util.ArrayList;
import java.util.List;

public class MatchmakingSystem {
    private List<Player> availablePlayers;

    /**
     * Constructor to initialize the matchmaking system.
     */
    public MatchmakingSystem() {
        this.availablePlayers = new ArrayList<>();
    }

    /**
     * Adds a player to the matchmaking queue.
     * @param player The player to be added to the queue.
     */
    public void addPlayerToQueue(Player player) {
        if (!availablePlayers.contains(player)) {
            availablePlayers.add(player);
            System.out.println("Player " + player.getUsername() + " added to matchmaking queue.");
        }
    }

    /**
     * Removes a player from the matchmaking queue.
     * @param player The player to be removed from the queue.
     */
    public void removePlayerFromQueue(Player player) {
        if (availablePlayers.remove(player)) {
            System.out.println("Player " + player.getUsername() + " removed from matchmaking queue.");
        } else {
            System.out.println("Player " + player.getUsername() + " is not in the queue.");
        }
    }

    /**
     * Attempts to match two players from the queue and start a game session.
     * If there are fewer than two players in the queue, matchmaking will not proceed.
     */
    public void attemptMatchmaking() {
        if (availablePlayers.size() < 2) {
            System.out.println("Not enough players in the queue for matchmaking.");
            return;
        }

        Player player1 = availablePlayers.get(0);
        Player player2 = availablePlayers.get(1);
        availablePlayers.remove(player1);
        availablePlayers.remove(player2);

        String gameType = selectGameType();
        Match match = new Match(player1, player2, gameType);
        match.startMatch();

        // Simulate match result
        String result = simulateMatchResult();
        Player winner = result.equals("Won") ? player1 : null; // Assuming player1 wins for simulation purposes
        match.endMatch(result, winner);
        match.printMatchSummary();
    }

    private String selectGameType() {
        // Logic to select a game type (e.g., TicTacToe, Chess, Connect4)
        return "TicTacToe"; // Placeholder value
    }

    private String simulateMatchResult() {
        // Simulate match result (e.g., "Won" or "Draw")
        return "Won"; // Placeholder value
    }

    public void printQueueStatus() {
        System.out.println("Matchmaking Queue Status:");
        if (availablePlayers.isEmpty()) {
            System.out.println("No players in the queue.");
        } else {
            for (Player player : availablePlayers) {
                System.out.println("Player: " + player.getUsername() + ", Skill Level: " + player.getSkillLevel());
            }
        }
    }

    public List<Player> getAvailablePlayers() {
        return availablePlayers;
    }
}