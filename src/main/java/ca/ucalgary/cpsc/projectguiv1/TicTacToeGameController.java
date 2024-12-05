package ca.ucalgary.cpsc.projectguiv1;


import UserAndProfile.TicTacToeProfile;
import UserAndProfile.User;

import gameLogic.TicTacToe;
import gameLogic.boardGames.AbstractBoardGame;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import leaderboard.tictactoeLeaderboard.TicTacToeLeaderboard;
import network.Network;


import java.io.IOException;
import java.util.*;

/**
 * @author Ethan Copeland
 * This class is responsible for the GUI functionality of Tic-Tac-Toe.
 * It is also responsible for the "AI" opponent for making moves.
 */
public class TicTacToeGameController {
    private User usrOne;
    private User usrTwo;

    private TicTacToe gameTicTacToe;

    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    private int amtOfMoves = 0;

    private boolean allFilled = false;

    private boolean oneAlert = false;

    private TicTacToeLeaderboard tttl;

    public static String otherPlayersName;

    @FXML
    Pane identity;
    @FXML
    Button exitBtn;

    @FXML
    Button sendBtn;

    @FXML
    TextField chatTxtFld;

    @FXML
    VBox chatBox;

    @FXML
    Label player1Name;

    @FXML
    Label winLabelP1;

    @FXML
    Label rankLabelP1;

    @FXML
    Label player2Name;

    @FXML
    Label winLabelP2;

    @FXML
    Label rankLabelP2;

    @FXML
    GridPane gamePane;

    @FXML
    Label infoLabel;

    @FXML
    ScrollPane chatScrlPane;

    @FXML
    Label xTxtLbl;

    @FXML
    Label oTxtLbl;

    @FXML
    Button startBtn;


    /**
     * Sets up the user which are to play, and gets the leaderboard that has their stats.
     */
    @Deprecated
    public TicTacToeGameController() {
        this.usrOne = HelloApplication.usrDb.getCurrentUser();

        // Network functionality
        List<User> allPlayers = HelloApplication.usrDb.getAllUsers();
        Network network = new Network(allPlayers);
        List<User> tttPlayers = network.findTictactoeRank(this.usrOne.getPlayerProfile().getTicTacToeProfile().getScoreRank());


        Collections.shuffle(tttPlayers);

        this.usrTwo = tttPlayers.getFirst();
        if (this.usrTwo.getUsername().equals(this.usrOne.getUsername())) {
            this.usrTwo = tttPlayers.get(1);
        }

        if(otherPlayersName != null){ // match with a specific user
            this.usrTwo = HelloApplication.usrDb.searchByUsername(otherPlayersName);
        }

        HelloApplication.usrDb.addUser(this.usrTwo);
        this.tttl = TicTacToeLeaderboard.getInstance();


        this.gameTicTacToe = new TicTacToe(2);
        this.gameTicTacToe.addPlayer(this.usrOne.getUsername());
        this.gameTicTacToe.addPlayer(this.usrTwo.getUsername());
    }


    public void exitBtnFunc() throws IOException { // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tic_Tac_Toe_Main_Menu_View.fxml"));
        // This scene is big enough to fit all the javafx GUI
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Tic-Tac-Toe");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    /**
     * This sets up the gridPane in the center of the window,
     * and adds functionality for gameplay
     */
    public void setupGrid() {
        // Setup the text to let the user know which side they are playing
        this.xTxtLbl.setTextFill(Color.BLUE);
        this.xTxtLbl.setFont(new Font("Comic Sans", 100));
        this.xTxtLbl.setAlignment(Pos.TOP_CENTER);
        this.xTxtLbl.setText("X");

        // Setup the text to let the user know which side they are playing
        this.oTxtLbl.setTextFill(Color.RED);
        this.oTxtLbl.setFont(new Font("Comic Sans", 100));
        this.oTxtLbl.setText("O  ");


        // Adding clickable panes to the grid
        if (this.setup == false) {
            // Looping through the entire gridPane
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    Pane currPane = new Pane();
                    // Coordinates of the pane relative to inside gridPane
                    int xCoord = x;
                    int yCoord = y;
                    this.gamePane.add(currPane, y, x);
                    arrOfPanes.add(currPane);
                    arrOfPaneCoords.add(y + " " + x);
                    // For each gridPane added, add clicking functionality to them one by one
                    currPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, yCoord};
                        try {
                            makeMove(coordsArr, currPane);
                        } catch (IOException ioe) {
                            System.out.println("io exception on setup");
                        }
                    });
                }
            }
            // Make whoever starts first random
            Random randInt = new Random();
            this.gameTicTacToe.setCurrentPlayer(randInt.nextInt(2));
            if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
                // Let the users know they have to click to start
                this.infoLabel.setFont(new Font("Comic Sans", 20));
                this.infoLabel.setText("Click to start!");

            } else {
                // Let the users know they have to click to start
                this.infoLabel.setFont(new Font("Comic Sans", 20));
                this.infoLabel.setText("Click to start!");
            }
            // Update the GUI with the user's information
            this.player1Name.setText(this.usrOne.getUsername());
            this.player2Name.setText(this.usrTwo.getUsername());
            this.winLabelP1.setText("Wins: " + this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins());
            this.winLabelP2.setText("Wins: " + this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins());

            // Update the player rank as the game starts
            if(this.tttl.getPlayerRank(this.usrOne.getUsername()) != -1) {
                this.rankLabelP1.setText("Rank: " + this.tttl.getPlayerRank(this.usrOne.getUsername()));
            }else{
                this.rankLabelP1.setText("Rank: 0");
            }
            if(this.tttl.getPlayerRank(this.usrTwo.getUsername()) != -1) {
                this.rankLabelP2.setText("Rank: " + this.tttl.getPlayerRank(this.usrTwo.getUsername()));
            }else{
                this.rankLabelP2.setText("Rank: 0");
            }

            // This setup is only needed once so this stops the loop
            this.setup = true;
        }

    }

    /**
     * @param coordsArr The coordinates of the pane in the gridPane
     * @param currPane The pane located at the coordingates in the gridPane
     * @throws IOException Input/Output exception
     *  This method places the X or O on the board based on user clicks
     */
    public void makeMove(int[] coordsArr, Pane currPane) throws IOException {
        // Try statement for stopping the user from placing in a pane/tile that is already occupied
        try {
            // If it is player one's turn, allow them to play
            if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
                this.gameTicTacToe.makeMove(coordsArr);
                this.amtOfMoves++;
                this.gameTicTacToe.switchCurrentPlayer();

                // Then update the GUI to give feedback for the players move
                Label xLbl = new Label();
                xLbl.setTextFill(Color.BLUE);
                xLbl.setFont(new Font("Comic Sans", 100));
                xLbl.setText("  X");
                xLbl.setAlignment(Pos.CENTER);
                currPane.getChildren().add(xLbl);

                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
                checkEndCon();
                // Make a random move because that is what the second player does
                makeRandMove();
                checkEndCon();
            } else { // If it is not the first users turn, make a random move because that is what the second player does
                makeRandMove();
                checkEndCon();
            }

        } catch (IllegalArgumentException iae) { // Give the user a warning that you cannot place a piece on an occupied tile
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tile Already Occupied");
            alert.show();

        }
    }

    /**
     * This function allows a chatting feature
     */
    public void sendBtnFunc() {
        String chatTxt = "";
        // Send the chat from the user that is current
        if (this.gameTicTacToe.getCurrentPlayer().equals(this.usrOne.getUsername())) {
            chatTxt += this.usrOne.getUsername() + ": ";
        } else {
            chatTxt += this.usrTwo.getUsername() + ": ";
        }
        // Then, update the GUI
        chatTxt += this.chatTxtFld.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }

    /**
     * This method is the logic behind the second players random moves
     */
    public void makeRandMove() {

        // Find a random pane/tile
        Random rand = new Random();
        int idx = rand.nextInt(9);
        Pane randPane = this.arrOfPanes.get(idx);
        String randCoords = this.arrOfPaneCoords.get(idx);
        Scanner sc = new Scanner(randCoords);
        int randXCoord = sc.nextInt();
        int randYCoord = sc.nextInt();
        int[] rCoordArr = {randYCoord, randXCoord};
        boolean foundSpot = false;
        // Do not attempt a move if all tiles are full
        if(this.amtOfMoves >= 9){
            allFilled = true;
        }

        // While loop so the code continuously tries to find an empty tile
        while ((foundSpot == false) && (allFilled == false)) {
            try {
                this.gameTicTacToe.makeMove(rCoordArr);
                this.gameTicTacToe.switchCurrentPlayer();
                Label oLbl = new Label();
                oLbl.setTextFill(Color.RED);
                oLbl.setFont(new Font("Comic Sans", 100));
                oLbl.setText("  O");
                oLbl.setAlignment(Pos.CENTER);
                randPane.getChildren().add(oLbl);
                this.infoLabel.setText(this.gameTicTacToe.getCurrentPlayer() + "'s move!");
                checkEndCon();
                this.amtOfMoves++;
                foundSpot = true;
            } catch (IllegalArgumentException iae) {
                // Since the current pane is occupied, find another one
                idx = rand.nextInt(9);
                randPane = this.arrOfPanes.get(idx);
                randCoords = this.arrOfPaneCoords.get(idx);
                sc = new Scanner(randCoords);
                randXCoord = sc.nextInt();
                randYCoord = sc.nextInt();
                rCoordArr[1] = randXCoord;
                rCoordArr[0] = randYCoord;
            }

        }

    }

    /**
     * Checks the game to see if there is a winner and loser
     */
    public void checkEndCon() {
        if(this.oneAlert == false) { // This is to make sure that only one pop-up, pops up
            if (this.gameTicTacToe.validateGameEnds() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("X's Win!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 1);
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });

                this.oneAlert = true;
            } else if (this.gameTicTacToe.validateGameEnds() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner!");
                alert.setHeaderText("O's Win!");
                alert.show();
                alert.setOnHidden(dialogEvent -> {
                    saveEndData('W', 2);
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });
                this.oneAlert = true;
            } else if (this.gameTicTacToe.validateGameEnds() == 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Draw!");
                alert.setHeaderText("This Game Has Reached A Stalemate");
                alert.show();

                alert.setOnHidden(dialogEvent -> {
                    try {
                        exitBtnFunc(); // Once the game is declared over, quit the screen
                    } catch (IOException ioe) {
                        System.out.println("IOExecption tictactoe exit btn func");
                    }
                });
                this.oneAlert = true;
            }
        }
    }

    /**
     * @param result result of the match
     * @param player the number associated with the player that won/lost, 1 is current user, 2 is other user
     */
    public void saveEndData(char result, int player){
        this.usrTwo = HelloApplication.usrDb.searchByUsername(this.usrTwo.getUsername());

        if((result == 'W') && (player == 1)) { // Update all the backend upon current user winning
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrOne.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrOne.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "W",  1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrOne.getUsername(), "L",  -1);
            this.tttl.recordWin(this.usrOne.getUsername());
            this.tttl.recordLoss(this.usrTwo.getUsername());

        }else if ((result == 'W') && (player == 2)){ // Update all the backend upon current user winning
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().setTotalWins(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getTotalWins() + 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().setTotalLosses(this.usrOne.getPlayerProfile().getTicTacToeProfile().getTotalLosses() + 1);
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateRanking(this.usrTwo.getPlayerProfile().getTicTacToeProfile().getScoreRank(), this.usrTwo.getPlayerProfile().getTicTacToeProfile().getWinRateRank());
            this.usrTwo.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrOne.getUsername(), "W", 1);
            this.usrOne.getPlayerProfile().getTicTacToeProfile().updateGameHistoryReal(this.usrTwo.getUsername(), "L", -1);
            this.tttl.recordWin(this.usrTwo.getUsername());
            this.tttl.recordLoss(this.usrOne.getUsername());

        }
        otherPlayersName = null;

    }
}




