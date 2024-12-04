package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.chessLeaderboard.PlayerStats;

import java.io.IOException;
import java.util.List;

public class Leaderboard_Controller {
    @FXML
    private GridPane identity;

    @FXML
    private Button back_button;

    @FXML
    private Button player_1st_button;

    @FXML
    private Button player_2nd_button;

    @FXML
    private Button player_3rd_button;

    @FXML
    private Button player_4th_button;

    @FXML
    private Button player_5th_button;

    @FXML
    private Button player_6th_button;

    @FXML
    private Button player_7th_button;

    @FXML
    private Button player_8th_button;

    @FXML
    private Button player_9th_button;

    @FXML
    private Button player_10th_button;

    @FXML
    private Button player_11th_button;

    @FXML
    private Button player_12th_button;

    @FXML
    private Button player_13th_button;

    // Array to hold all player buttons for easy management
    private Button[] playerButtons;

    @FXML
    public void initialize() {
        // Initialize the array of player buttons
        playerButtons = new Button[]{
                player_1st_button,
                player_2nd_button,
                player_3rd_button,
                player_4th_button,
                player_5th_button,
                player_6th_button,
                player_7th_button,
                player_8th_button,
                player_9th_button,
                player_10th_button,
                player_11th_button,
                player_12th_button,
                player_13th_button
        };

        // Get the leaderboard instance (Singleton pattern)
        ChessLeaderboard leaderboard = ChessLeaderboard.getInstance();

        // Retrieve the top 13 players
        List<PlayerStats> topPlayers = leaderboard.getTopPlayers(13);

        // Update the text of each button with player information
        for (int i = 0; i < topPlayers.size(); i++) {
            PlayerStats player = topPlayers.get(i);
            Button button = playerButtons[i];
            // Set the button text as "Player Name - Wins: TotalWins"
            button.setText(player.getPlayerId() + " - Wins: " + player.getTotalWins());
        }

        // Hide buttons that are not used (if there are fewer than 13 players)
        for (int i = topPlayers.size(); i < playerButtons.length; i++) {
            playerButtons[i].setVisible(false);
        }
    }

    /**
     * Loads a new FXML file and displays it in a new window.
     *
     * @param file  The FXML file to load.
     * @param title The title of the new window.
     * @throws IOException If the file cannot be loaded.
     */
    public void loadFileFunc(String file, String title) throws IOException {
        // Remove spaces from the title
        String processedTitle = title.replaceAll(" ", "");

        // Determine the FXML file to load based on the processed title
        String fxmlFile;
        switch (processedTitle) {
            case "ChessLeaderboard":
                fxmlFile = "ChessLeaderboard.fxml";
                break;
            case "TicTacToeLeaderboard":
                fxmlFile = "TicTacToeLeaderboard.fxml";
                break;
            case "Connect4Leaderboard":
                fxmlFile = "Connect4Leaderboard.fxml";
                break;
            default:
                fxmlFile = "Homepage.fxml"; // Load a default board if title doesn't match
                break;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle(title);
        newStg.setScene(scene);
        newStg.show();

        // Close the current window
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * Returns to the homepage by loading the corresponding FXML file.
     *
     * @throws IOException If the file cannot be loaded.
     */
    public void exitBtnFunc() throws IOException {
        String file = "Homepage.fxml";
        loadFileFunc(file, "Homepage");
    }

}
