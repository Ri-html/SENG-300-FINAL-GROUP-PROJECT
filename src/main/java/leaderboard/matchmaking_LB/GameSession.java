package leaderboard.matchmaking_LB;

public class GameSession {
    private leaderboard.matchmaking_LB.Match match;
    private String sessionID;
    private String status; // In-Progress, Completed

     /**
     * Constructor to create a new game session for the given match.
     * @param match The match associated with this session.
     */
    public GameSession(Match match) {
        this.match = match;
        this.sessionID = generateSessionID();
        this.status = "In-Progress";
        System.out.println("Match " + sessionID + " between " + match.getPlayer1().getUsername() + " and " + match.getPlayer2().    getUsername() + " has started.");
    }

    /**
     * Generates a unique session ID for the game session.
     * @return A unique session ID.
     */
    private String generateSessionID() {
        // Generate unique session ID logic
        return "some_unique_id";
    }

     /**
     * Ends the game session, updates the match status, and displays the result.
     * @param result The result of the game (e.g., "Won" or "Draw").
     * @param winner The winning player, if applicable.
     */
     public void endMatch(String result, Player winner) {
        if (status.equals("In-Progress")) {
            status = "Completed";
            System.out.println("Match " + sessionID + " has ended.");
            if ("Won".equals(result) && winner != null) {
                System.out.println("Player " + winner.getUsername() + " has won the match!");
            } else if ("Draw".equals(result)) {
                System.out.println("The match ended in a draw.");
            }
            returnPlayersToQueue();
        } else {
            System.out.println("Match " + sessionID + " cannot be ended. Current status: " + status);
        }
    }

    /**
     * Returns players to the matchmaking queue after the game session ends.
     */
    private void returnPlayersToQueue() {
        System.out.println("Returning players to the main menu.");
    }
    /**
     * Prints a summary of the game session.
     */
    public void printMatchSummary() {
        System.out.println("Match Summary:");
        System.out.println("Match ID: " + sessionID);
        System.out.println("Game Type: " + match.getGameType());
        System.out.println("Players: " + match.getPlayer1().getUsername() + " vs " + match.getPlayer2().getUsername());
        System.out.println("Status: " + status);
    }
}
