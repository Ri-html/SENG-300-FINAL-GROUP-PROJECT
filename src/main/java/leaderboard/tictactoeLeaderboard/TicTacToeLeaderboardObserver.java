package leaderboard.tictactoeLeaderboard;

import gameLogic.boardGames.BoardGameObserver;

/**
 * An observer class that listens for game end events in Tic Tac Toe and updates the leaderboard.
 */
public class TicTacToeLeaderboardObserver implements BoardGameObserver {
    // Reference to the singleton instance of the Tic Tac Toe leaderboard
    private TicTacToeLeaderboard leaderboard;

    /**
     * Constructor to initialize the observer with the Tic Tac Toe leaderboard instance.
     */
    public TicTacToeLeaderboardObserver() {
        this.leaderboard = TicTacToeLeaderboard.getInstance();
    }

    /**
     * Updates the leaderboard based on the message received from the observed game.
     * The message is expected to contain game end details, including the winner and loser.
     * 
     * @param message The message containing game end details.
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
