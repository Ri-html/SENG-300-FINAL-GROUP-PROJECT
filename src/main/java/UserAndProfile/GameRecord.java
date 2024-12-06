package UserAndProfile;

public class GameRecord {
    private String game;
    private String opponent;  // 对手的名字
    private String result;    // 比赛结果（如 "Win", "Lose", "Draw"）
    private int score;        // 游戏得分

    // Constructor
    public GameRecord(String game, String opponent, String result, int score) {
        this.game = game;
        this.opponent = opponent;
        this.result = result;
        this.score = score;
    }

    // Getters
    public String getOpponent() {
        return opponent;
    }

    public String getResult() {
        return result;
    }

    public int getScore() {
        return score;
    }

    public String getGame() {
        return game;
    }

    // toString 方法，方便打印记录
    @Override
    public String toString() {
        return "Game = " + game + "\n" +
                "Opponent = " + opponent + "\n" +
                "Result = " + result + "\n" +
                "Score = " + score;
    }
}
