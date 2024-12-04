package UserAndProfile;

public class GameRecord {
    private String opponent;  // 对手的名字
    private String result;    // 比赛结果（如 "Win", "Lose", "Draw"）
    private int score;        // 游戏得分

    // Constructor
    public GameRecord(String opponent, String result, int score) {
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

    // toString 方法，方便打印记录
    @Override
    public String toString() {
        return "GameRecord{" +
                "opponent='" + opponent + '\'' +
                ", result='" + result + '\'' +
                ", score=" + score +
                '}';
    }
}
