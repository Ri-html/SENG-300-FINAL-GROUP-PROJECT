package leaderboard.matchmaking_LB;

public class Match {
    private Player player1;
    private Player player2;
    private String gameType;
    private String matchID;
    private String status; // Pending, In-Progress, Completed

    /**
     * Constructor to create a match between two players for a specified game type.
     * @param player1 The first player.
     * @param player2 The second player.
     * @param gameType The type of game to be played (e.g., TicTacToe, Chess, Connect4).
     */
    public Match(Player player1, Player player2, String gameType) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameType = gameType;
        this.matchID = generateMatchID();
        this.status = "Pending";
    }

    /**
     * Generates a unique match ID for the match.
     * @return A unique match ID.
     */
    private String generateMatchID() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Gets the first player of the match.
     * @return The first player.
     */
    public Player getPlayer1() {
        return player1;
    }


    /**
     * Gets the second player of the match.
     * @return The second player.
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Gets the game type of the match.
     * @return The game type.
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * Gets the match ID.
     * @return The match ID.
     */
    public String getMatchID() {
        return matchID;
    }

    /**
     * Gets the current status of the match.
     * @return The current status (e.g., Pending, In-Progress, Completed).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the match.
     * @param status The new status of the match.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Starts the match by changing its status to "In-Progress".
     * Prints a message indicating that the match has started.
     */
    public void startMatch() {
        if (status.equals("Pending")) {
            status = "In-Progress";
            System.out.println("Match " + matchID + " between " + player1.getUsername() + " and " + player2.getUsername() + " has started.");
        } else {
            System.out.println("Match " + matchID + " cannot be started. Current status: " + status);
        }
    }

    /**
     * Ends the match by changing its status to "Completed".
     * Prints the result of the match.
     * @param result The result of the match (e.g., "Won" or "Draw").
     * @param winner The winning player, if applicable.
     */
    public void endMatch(String result, Player winner) {
        if (status.equals("In-Progress")) {
            status = "Completed";
            System.out.println("Match " + matchID + " has ended.");
            if ("Won".equals(result) && winner != null) {
                System.out.println("Player " + winner.getUsername() + " has won the match!");
            } else if ("Draw".equals(result)) {
                System.out.println("The match ended in a draw.");
            }
            // Additional code to update players' statistics, leaderboard, etc.
        } else {
            System.out.println("Match " + matchID + " cannot be ended. Current status: " + status);
        }
    }


    /**
     * Prints a summary of the match, including match ID, game type, players, and status.
     */
    public void printMatchSummary() {
        System.out.println("Match Summary:");
        System.out.println("Match ID: " + matchID);
        System.out.println("Game Type: " + gameType);
        System.out.println("Players: " + player1.getUsername() + " vs " + player2.getUsername());
        System.out.println("Status: " + status);
    }
}