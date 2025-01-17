package leaderboard.connect4Leaderboard;

import gameLogic.boardGames.BoardGameObserver;

/**
 * Observer class for the Connect Four leaderboard.
 * Updates the leaderboard based on game-end messages from the game logic.
 */
public class Connect4LeaderboardObserver implements BoardGameObserver {
    // Reference to the singleton instance of the Connect Four leaderboard
    private Connect4Leaderboard leaderboard;

    /**
     * Constructor to initialize the observer with the leaderboard instance.
     */
    public Connect4LeaderboardObserver() {
        this.leaderboard = Connect4Leaderboard.getInstance();
    }

    /**
     * Updates the leaderboard based on the game message received from the game logic.
     *
     * @param message The game message containing details about the game outcome.
     */
    @Override
    public void update(String message) {
        // Split the incoming message into lines
        String[] lines = message.split("\n");

        // Ensure the message contains at least three lines and starts with "GameEnd"
        if (lines.length >= 3 && lines[0].equals("GameEnd")) {
            // Split the third line to extract result details
            String[] resultParts = lines[2].split(",");

            // Ensure the result contains at least two parts and indicates a winner
            if (resultParts.length >= 2 && resultParts[0].equals("winner")) {
                // Extract the winner's ID and record the win
                String winnerId = resultParts[1];
                leaderboard.recordWin(winnerId);

                // If a loser is specified, extract the ID and record the loss
                if (resultParts.length >= 4 && resultParts[2].equals("loser")) {
                    String loserId = resultParts[3];
                    leaderboard.recordLoss(loserId);
                }
            }
        }
    }
}
