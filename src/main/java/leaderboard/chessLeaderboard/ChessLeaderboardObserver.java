package leaderboard.chessLeaderboard;

import gameLogic.boardGames.BoardGameObserver;

public class ChessLeaderboardObserver implements BoardGameObserver {
/**
 * An observer that listens for "GameEnd" messages and updates the Leaderboard accordingly.
 * When a game concludes, this observer extracts the winner ID and records a win for that player.
 */

public class LeaderboardObserver implements BoardGameObserver {
    private ChessLeaderboard leaderboard;

    public ChessLeaderboardObserver() {
        this.leaderboard = ChessLeaderboard.getInstance();
    }

    /**
     * Called by the game to notify observers of a state change.
     * If the message indicates that the game has ended, this method parses the winner's ID
     * and updates the leaderboard with a recorded win for that player.
     */

    @Override
    public void update(String message) {
        // Extract winner
        String[] lines = message.split("\n");
        if (lines.length >= 3 && lines[0].equals("GameEnd")) {
            String[] resultParts = lines[2].split(",");
            if (resultParts.length >= 2 && resultParts[0].equals("winner")) {
                String winnerId = resultParts[1];
                leaderboard.recordWin(winnerId);
            }
        }
    }
}
