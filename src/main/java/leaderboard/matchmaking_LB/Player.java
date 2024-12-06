package leaderboard.matchmaking_LB;

public class Player {
    private String playerID;
    private String username;
    private int skillLevel;
    private String status; // Available, In-Game, etc.

    public Player(String username, int skillLevel) {
        this.playerID = generatePlayerID();
        this.username = username;
        this.skillLevel = skillLevel;
        this.status = "Available";
    }

    protected String generatePlayerID() {
        // Generate a unique player ID (e.g., UUID)
        return java.util.UUID.randomUUID().toString();
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return playerID.equals(player.playerID);
    }

    @Override
    public int hashCode() {
        return playerID.hashCode();
    }
}


