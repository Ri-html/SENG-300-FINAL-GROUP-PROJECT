package leaderboard.chessLeaderboard;

import gameLogic.boardGames.BoardGameObserver;

public class LeaderboardObserver implements BoardGameObserver {
    private ChessLeaderboard leaderboard;

    public LeaderboardObserver() {
        this.leaderboard = ChessLeaderboard.getInstance();
    }

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
