package UserAndProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerProfile {

    private ChessProfile chessProfile;
    private TicTacToeProfile ticTacToeProfile;
    private ConnectFourProfile connectFourProfile;
    // New field to store selected modules
    private List<String> selectedModules;

    // Define available modules as constants
    // These are the modules the user can choose to display
    public static final List<String> AVAILABLE_MODULES = Arrays.asList(
            "Games Played",
            "Player Stats",
            "Win/Loss Record",
            "Ranking"
    );

    // 1. Get all game records for each individual game
    public List<GameRecord> getChessGameRecords() {
        return chessProfile.getAllGameRecords();
    }

    public List<GameRecord> getTicTacToeGameRecords() {
        return ticTacToeProfile.getAllGameRecords();
    }

    public List<GameRecord> getConnectFourGameRecords() {
        return connectFourProfile.getAllGameRecords();
    }

    // 2. Get all game records across all games
    public List<GameRecord> getAllGameRecords() {
        List<GameRecord> allGameRecords = new ArrayList<>();
        allGameRecords.addAll(chessProfile.getAllGameRecords());
        allGameRecords.addAll(ticTacToeProfile.getAllGameRecords());
        allGameRecords.addAll(connectFourProfile.getAllGameRecords());
        return allGameRecords;
    }


    // Constructor
    public PlayerProfile() {
        this.chessProfile = new ChessProfile();
        this.ticTacToeProfile = new TicTacToeProfile();
        this.connectFourProfile = new ConnectFourProfile();

        // Initialize with all modules selected by default
        this.selectedModules = new ArrayList<>(AVAILABLE_MODULES);

    }

    // Access methods for each game profile
    public ChessProfile getChessProfile() {
        return chessProfile;
    }

    public TicTacToeProfile getTicTacToeProfile() {
        return ticTacToeProfile;
    }

    public ConnectFourProfile getConnectFourProfile() {
        return connectFourProfile;
    }

    // Methods to display each profile section data based on selectedModules
    public void displayProfile() {
        if (selectedModules.contains("Games Played")) {
            displayGameHistory();
        }
        if (selectedModules.contains("Player Stats")) {
            displayPlayerStatus();
        }
        if (selectedModules.contains("Win/Loss Record")) {
            displayWinLoseRecord();
        }
        if (selectedModules.contains("Ranking")) {
            displayRanking();
        }
    }

    // Methods to display each profile section data

    // Game history display method
    public void displayGameHistory() {
        System.out.println("Game History for Chess: " + chessProfile.getLastOpponent() + ", " + chessProfile.getLastGameResult() + ", " + chessProfile.getLastGameScore());
        System.out.println("Game History for TicTacToe: " + ticTacToeProfile.getLastOpponent() + ", " + ticTacToeProfile.getLastGameResult() + ", " + ticTacToeProfile.getLastGameScore());
        System.out.println("Game History for ConnectFour: " + connectFourProfile.getLastOpponent() + ", " + connectFourProfile.getLastGameResult() + ", " + connectFourProfile.getLastGameScore());
    }

    // Player status display method
    public void displayPlayerStatus() {
        System.out.println("Chess Status: Total Games = " + chessProfile.getTotalGamesPlayed() + ", Total Score = " + chessProfile.getTotalScore() + ", Win Rate = " + chessProfile.getWinRate());
        System.out.println("TicTacToe Status: Total Games = " + ticTacToeProfile.getTotalGamesPlayed() + ", Total Score = " + ticTacToeProfile.getTotalScore() + ", Win Rate = " + ticTacToeProfile.getWinRate());
        System.out.println("ConnectFour Status: Total Games = " + connectFourProfile.getTotalGamesPlayed() + ", Total Score = " + connectFourProfile.getTotalScore() + ", Win Rate = " + connectFourProfile.getWinRate());
    }

    // Win/Lose record display method
    public void displayWinLoseRecord() {
        System.out.println("Chess Win/Lose Record: Wins = " + chessProfile.getTotalWins() + ", Losses = " + chessProfile.getTotalLosses() + ", Total Games = " + chessProfile.getTotalGamesPlayed());
        System.out.println("TicTacToe Win/Lose Record: Wins = " + ticTacToeProfile.getTotalWins() + ", Losses = " + ticTacToeProfile.getTotalLosses() + ", Total Games = " + ticTacToeProfile.getTotalGamesPlayed());
        System.out.println("ConnectFour Win/Lose Record: Wins = " + connectFourProfile.getTotalWins() + ", Losses = " + connectFourProfile.getTotalLosses() + ", Total Games = " + connectFourProfile.getTotalGamesPlayed());
    }

    // Ranking display method
    public void displayRanking() {
        System.out.println("Chess Ranking: Total Score Rank = " + chessProfile.getScoreRank() + ", Win Rate Rank = " + chessProfile.getWinRateRank());
        System.out.println("TicTacToe Ranking: Total Score Rank = " + ticTacToeProfile.getScoreRank() + ", Win Rate Rank = " + ticTacToeProfile.getWinRateRank());
        System.out.println("ConnectFour Ranking: Total Score Rank = " + connectFourProfile.getScoreRank() + ", Win Rate Rank = " + connectFourProfile.getWinRateRank());
    }

    // Update methods for each profile section

    public void updateGameHistory(String gameType, String opponent, String result, int score) {
        switch (gameType) {
            case "Chess":
                chessProfile.updateGameHistory(opponent, result, score);
                break;
            case "TicTacToe":
                ticTacToeProfile.updateGameHistory(opponent, result, score);
                break;
            case "ConnectFour":
                connectFourProfile.updateGameHistory(opponent, result, score);
                break;
            default:
                System.out.println("Unknown game type.");
                break;
        }
    }

    public void updatePlayerStatus(String gameType, int totalGames, int score, double winRate) {
        switch (gameType) {
            case "Chess":
                chessProfile.updatePlayerStatus(totalGames, score, winRate);
                break;
            case "TicTacToe":
                ticTacToeProfile.updatePlayerStatus(totalGames, score, winRate);
                break;
            case "ConnectFour":
                connectFourProfile.updatePlayerStatus(totalGames, score, winRate);
                break;
            default:
                System.out.println("Unknown game type.");
                break;
        }
    }

    public void updateWinLoseRecord(String gameType, int wins, int losses) {
        switch (gameType) {
            case "Chess":
                chessProfile.updateWinLoseRecord(wins, losses);
                break;
            case "TicTacToe":
                ticTacToeProfile.updateWinLoseRecord(wins, losses);
                break;
            case "ConnectFour":
                connectFourProfile.updateWinLoseRecord(wins, losses);
                break;
            default:
                System.out.println("Unknown game type.");
                break;
        }
    }

    public void updateRanking(String gameType, int scoreRank, int winRateRank) {
        switch (gameType) {
            case "Chess":
                chessProfile.updateRanking(scoreRank, winRateRank);
                break;
            case "TicTacToe":
                ticTacToeProfile.updateRanking(scoreRank, winRateRank);
                break;
            case "ConnectFour":
                connectFourProfile.updateRanking(scoreRank, winRateRank);
                break;
            default:
                System.out.println("Unknown game type.");
                break;
        }
    }

    // New methods to manage selected modules

    /**
     * Gets the list of currently selected modules.
     *
     * @return List of selected module names.
     */
    public List<String> getSelectedModules() {
        return new ArrayList<>(selectedModules);
    }

    /**
     * Sets the list of selected modules.
     *
     * @param modules List of module names to be selected.
     * @return true if the modules were successfully updated, false otherwise.
     */
    public boolean setSelectedModules(List<String> modules) {
        // Validate the provided modules against available modules
        for (String module : modules) {
            if (!AVAILABLE_MODULES.contains(module)) {
                System.out.println("Invalid module: " + module);
                return false;
            }
        }
        this.selectedModules = new ArrayList<>(modules);
        return true;
    }

    /**
     * Adds a module to the selected modules list.
     *
     * @param module Name of the module to add.
     * @return true if added successfully, false if the module is invalid or already selected.
     */
    public boolean addModule(String module) {
        if (!AVAILABLE_MODULES.contains(module)) {
            System.out.println("Invalid module: " + module);
            return false;
        }
        if (!selectedModules.contains(module)) {
            selectedModules.add(module);
            return true;
        }
        return false;
    }

    /**
     * Removes a module from the selected modules list.
     *
     * @param module Name of the module to remove.
     * @return true if removed successfully, false if the module was not selected.
     */
    public boolean removeModule(String module) {
        return selectedModules.remove(module);
    }


}

