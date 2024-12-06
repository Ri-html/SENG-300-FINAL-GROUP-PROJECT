package leaderboard.matchmaking_LB;

public class GameSession {
    private leaderboard.matchmaking_LB.Match match;
    private String sessionID;
    private String status; // In-Progress, Completed

    public GameSession(Match match) {
        this.match = match;
        this.sessionID = generateSessionID();
        this.status = "In-Progress";
        System.out.println("Match " + sessionID + " between " + match.getPlayer1().getUsername() + " and " + match.getPlayer2().    getUsername() + " has started.");
    }

    private String generateSessionID() {
        // Generate unique session ID logic
        return "some_unique_id";
    }

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

    private void returnPlayersToQueue() {
        System.out.println("Returning players to the main menu.");
    }

    public void printMatchSummary() {
        System.out.println("Match Summary:");
        System.out.println("Match ID: " + sessionID);
        System.out.println("Game Type: " + match.getGameType());
        System.out.println("Players: " + match.getPlayer1().getUsername() + " vs " + match.getPlayer2().getUsername());
        System.out.println("Status: " + status);
    }
}
