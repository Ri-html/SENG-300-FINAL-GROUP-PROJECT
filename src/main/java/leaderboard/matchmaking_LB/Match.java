package leaderboard.matchmaking_LB;

public class Match {
    private Player player1;
    private Player player2;
    private String gameType;
    private String matchID;
    private String status; // Pending, In-Progress, Completed

    public Match(Player player1, Player player2, String gameType) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameType = gameType;
        this.matchID = generateMatchID();
        this.status = "Pending";
    }

    private String generateMatchID() {
        return java.util.UUID.randomUUID().toString();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameType() {
        return gameType;
    }

    public String getMatchID() {
        return matchID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void startMatch() {
        if (status.equals("Pending")) {
            status = "In-Progress";
            System.out.println("Match " + matchID + " between " + player1.getUsername() + " and " + player2.getUsername() + " has started.");
        } else {
            System.out.println("Match " + matchID + " cannot be started. Current status: " + status);
        }
    }

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


    public void printMatchSummary() {
        System.out.println("Match Summary:");
        System.out.println("Match ID: " + matchID);
        System.out.println("Game Type: " + gameType);
        System.out.println("Players: " + player1.getUsername() + " vs " + player2.getUsername());
        System.out.println("Status: " + status);
    }
}