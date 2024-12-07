package leaderboard.matchmaking_LB;

import java.util.List;
import java.util.ArrayList;

    /**
     * Constructor to initialize the matchmaking system.
     */
public class Matchmaking{
    private List<Player> availablePlayers;

    /**
     * Adds a player to the matchmaking queue.
     * @param player The player to be added to the queue.
     */
    public void addPlayerToQueue(Player player) {
        
    }

    /**
     * Removes a player from the matchmaking queue.
     * @param player The player to be removed from the queue.
     */
    public void removePlayerFromQueue(Player player) {
        // Remove player from matchmaking queue
    }

    public Match findMatch(Player player) {
        // Logic for finding a suitable match based on criteria like skill level
        return null; // Return a new Match instance if suitable players are found
    }

    private boolean isSkillMatch(Player player1, Player player2) {
        // Example skill match logic (e.g., players with similar skill levels)
        int skillDifference = Math.abs(player1.getSkillLevel() - player2.getSkillLevel());
        return skillDifference <= 2; // Example threshold for skill match
    }

    /**
     * Prints the current status of the matchmaking queue, including players and their skill levels.
     */
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
/**
 * Known issues here:
 * No function printGameSummary
 **/
/*
    public void attemptMatchmaking() {
        List<Player> matchedPlayers = new ArrayList<>();
        for (Player player : availablePlayers) {
            Match match = findMatch(player);
            if (match != null) {
                GameSession session = new GameSession(match);
                session.printGameSummary();
                matchedPlayers.add(player);
                matchedPlayers.add(match.getPlayer2());
            }
        }
        availablePlayers.removeAll(matchedPlayers);
    }
    */
}