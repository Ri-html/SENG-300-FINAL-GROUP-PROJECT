package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import UserAndProfile.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import leaderboard.chessLeaderboard.ChessLeaderboard;
import leaderboard.connect4Leaderboard.Connect4Leaderboard;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;


import java.io.IOException;
import java.util.List;

public class Leaderboard_Controller {
    @FXML
    private GridPane identity;

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
                player_1st_button, player_2nd_button, player_3rd_button,
                player_4th_button, player_5th_button, player_6th_button,
                player_7th_button, player_8th_button, player_9th_button,
                player_10th_button, player_11th_button, player_12th_button, player_13th_button
        };

        // Use Platform.runLater to delay execution until the Scene is fully initialized
        javafx.application.Platform.runLater(() -> {
            Stage stage = (Stage) identity.getScene().getWindow();
            String windowTitle = stage.getTitle().replaceAll(" ", ""); // Remove spaces
            stage.setWidth(600);
            stage.setHeight(800);
            stage.setResizable(false);

            // Dynamically determine which leaderboard to load
            switch (windowTitle) {
                case "ChessLeaderboard":
                    loadChessLeaderboard();
                    break;
                case "Connect4Leaderboard":
                    loadConnect4Leaderboard();
                    break;
                case "Tic-Tac-ToeLeaderboard":
                    loadTicTacToeLeaderboard();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown leaderboard type: " + windowTitle);
            }
        });
    }


    /**
     * Loads data for Connect4 Leaderboard and updates the UI.
     */
    private void loadConnect4Leaderboard() {
        Connect4Leaderboard.initializeSampleData();
        Connect4Leaderboard leaderboard = Connect4Leaderboard.getInstance();
        List<?> topPlayers = leaderboard.getTopPlayers(); // Default returns 10 players
        updateLeaderboardUI(topPlayers);
    }

    /**
     * Loads data for Chess Leaderboard and updates the UI.
     */
    private void loadChessLeaderboard() {
        ChessLeaderboard.initializeSampleData();
        ChessLeaderboard leaderboard = ChessLeaderboard.getInstance();
        List<?> topPlayers = leaderboard.getTopPlayers(13); // Get top 13 players
        updateLeaderboardUI(topPlayers);
    }

    /**
     * Loads data for Tic Tac Toe Leaderboard and updates the UI.
     */
    private void loadTicTacToeLeaderboard() {
        TicTacToeLeaderboard.initializeSampleData();
        TicTacToeLeaderboard leaderboard = TicTacToeLeaderboard.getInstance();
        List<?> topPlayers = leaderboard.getTopPlayers(); // Default returns 10 players
        updateLeaderboardUI(topPlayers);
    }


    /**
     * Updates the leaderboard UI based on the provided player data.
     *
     * @param topPlayers The list of top players to display.
     */
    /**
     * Updates the leaderboard UI based on the provided player data.
     *
     * @param topPlayers The list of top players to display, with raw Object type.
     */
    private void updateLeaderboardUI(List<?> topPlayers) {
        for (int i = 0; i < topPlayers.size(); i++) {
            Object player = topPlayers.get(i);

            String buttonText;
            if (player instanceof leaderboard.chessLeaderboard.PlayerStats) {
                // Handle Chess PlayerStats
                leaderboard.chessLeaderboard.PlayerStats chessPlayer =
                        (leaderboard.chessLeaderboard.PlayerStats) player;
                buttonText = chessPlayer.getPlayerId() + " - Wins: " + chessPlayer.getTotalWins();
            } else if (player instanceof leaderboard.connect4Leaderboard.PlayerStats) {
                // Handle Connect4 PlayerStats
                leaderboard.connect4Leaderboard.PlayerStats connect4Player =
                        (leaderboard.connect4Leaderboard.PlayerStats) player;
                buttonText = connect4Player.getPlayerId() + " - Wins: " + connect4Player.getTotalWins();
            } else if (player instanceof leaderboard.tictactoeLeaderboard.PlayerStats) {
                // Handle TicTacToe PlayerStats
                leaderboard.tictactoeLeaderboard.PlayerStats tttPlayer =
                        (leaderboard.tictactoeLeaderboard.PlayerStats) player;
                buttonText = tttPlayer.getPlayerId() + " - Wins: " + tttPlayer.getTotalWins();
            } else {
                // If the object is not a known PlayerStats type, skip it
                continue;
            }

            // Update button text
            Button button = playerButtons[i];
            button.setText(buttonText);
        }

        // Hide unused buttons
        for (int i = topPlayers.size(); i < playerButtons.length; i++) {
            playerButtons[i].setVisible(false);
        }
    }



    /**
     * Loads a new FXML file and displays it in a new window.
     *
     * @param file  The name of the FXML file to load (e.g., "leaderboard.fxml" or "homepage.fxml").
     * @param title The title of the new window.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public void loadFileFunc(String file, String title) throws IOException {
        // Create the FXMLLoader with the provided FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));

        // Load the scene from the FXML file
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        // Create and set up the new stage
        Stage newStage = new Stage();
        newStage.sizeToScene();
        newStage.setTitle(title);
        newStage.setScene(scene);
        newStage.show();

        // Close the current window
        Stage currentStage = (Stage) this.identity.getScene().getWindow();
        currentStage.close();
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


    /**
     * Handles the event triggered when the user clicks to view another user's profile.
     * This method retrieves the username from the button's text, fetches the corresponding
     * User object from the database, and navigates to the user's profile page.
     *
     * @param actionEvent The action event triggered by the button click
     * @throws IOException If the FXML file cannot be loaded
     */
    public void viewOtherUserProfile(javafx.event.ActionEvent actionEvent) throws IOException {
        // Get the button object from the event source
        Button button = (Button) actionEvent.getSource();

        // Retrieve the text displayed on the button
        String buttonText = button.getText(); // For example: "Alice - Wins: 5"

        // Extract the part before the " - " separator as the player's name
        String playerName = buttonText.split(" - ")[0]; // Result: playerName = "Alice"

        // Set the viewed username in UserSession
        UserSession.getInstance().setViewedUsername(playerName);

        // Retrieve the User object from UserDatabase
        UserDatabase db = UserDatabase.getInstance();
        User viewedUser = db.searchByUsername(playerName);

        if (viewedUser == null) {
            System.out.println("User not found in database: " + playerName);
            return;
        }

        // Navigate to the user's profile page
        navigateToProfilePage(viewedUser);

        // Close the current Leaderboard window
        Stage currentStage = (Stage) this.identity.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Navigates to the profile page of a specific user.
     *
     * @param user The User object representing the profile to display
     * @throws IOException If the FXML file for the profile page cannot be loaded
     */
    public void navigateToProfilePage(User user) throws IOException {
        // Load the FXML file for the profile page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Other_User_Profile.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the user object
        View_Other_User_Profile_Controller controller = loader.getController();
        controller.setUser(user);

        // Set up the new scene and stage
        Scene scene = new Scene(root, 800, 500);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Viewing " + user.getUsername() + "'s Profile");
        newStage.show();
    }
}
