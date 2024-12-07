package UserAndProfile;

/**
 * A class representing a record of a single game, including details about the game type,
 * opponent, result, and score.
 */
public class GameRecord {

    private String game; // The name of the game
    private String opponent; // The name of the opponent
    private String result; // The result of the game (e.g., "Win", "Lose", "Draw")
    private int score; // The score achieved in the game

    /**
     * Constructs a GameRecord object with the specified details.
     * 
     * @param game The name of the game.
     * @param opponent The name of the opponent.
     * @param result The result of the game (e.g., "Win", "Lose", "Draw").
     * @param score The score achieved in the game.
     */
    public GameRecord(String game, String opponent, String result, int score) {
        this.game = game;
        this.opponent = opponent;
        this.result = result;
        this.score = score;
    }

    /**
     * Gets the name of the opponent.
     * 
     * @return The name of the opponent.
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * Gets the result of the game.
     * 
     * @return The result of the game (e.g., "Win", "Lose", "Draw").
     */
    public String getResult() {
        return result;
    }

    /**
     * Gets the score achieved in the game.
     * 
     * @return The score of the game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the name of the game.
     * 
     * @return The name of the game.
     */
    public String getGame() {
        return game;
    }

    /**
     * Returns a string representation of the game record, including the game, opponent, result, and score.
     * 
     * @return A formatted string representation of the game record.
     */
    @Override
    public String toString() {
        return "Game = " + game + "\n" +
               "Opponent = " + opponent + "\n" +
               "Result = " + result + "\n" +
               "Score = " + score;
    }
}