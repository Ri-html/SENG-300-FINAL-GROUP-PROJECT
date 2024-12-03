package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import gameLogic.ConnectFour;
import gameLogic.boardGames.AbstractBoardGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class Connect4GameController {

    private User user1;
    private User user2;
    private ConnectFour gameConnect4;
    private boolean setup = false;

    @FXML
    Pane identity;

    @FXML
    GridPane gamePane;

    @FXML
    Button exitBtn;

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
    ScrollPane chatScrlPane;

    @FXML
    VBox chatBox;

    @FXML
    TextField chatTxtField;

    @FXML
    Button sendBtn;

    @FXML
    Label infoLabel;


    @Deprecated
    public Connect4GameController(){
        this.user1 = new User("0", "FirstUser", "example@gmail.com");
        this.user2 = new User("1", "SecondUser", "otherExample@gmail.com");

        this.gameConnect4 = new ConnectFour(2);
        this.gameConnect4.addPlayer(this.user1.getUsername());
        this.gameConnect4.addPlayer(this.user2.getUsername());

    }

    public void setupGrid(){
        if(this.setup == false){
            for(int x = 0; x < 7; x++){
                for(int y = 0; y < 6; y++){
                    Pane newPane = new Pane();
                    int xCoord = x;
                    int yCoord = y;
                    this.gamePane.add(newPane, xCoord, yCoord);
                    newPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, yCoord};
                        try {
                            makeMove(coordsArr, newPane);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }
            }
            Random randInt = new Random();
            this.gameConnect4.setCurrentPlayer(randInt.nextInt(2));

            if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
                infoLabel.setText(this.user1.getUsername() + "'s move!");
            } else{
                infoLabel.setText(this.user2.getUsername() + "'s move!");
            }

            // Set Labels
            this.player1Name.setText(this.user1.getUsername());
            this.player2Name.setText(this.user2.getUsername());
            this.winLabelP1.setText("Wins: " + this.user1.getPlayerProfile().getConnectFourProfile().getTotalWins());
            this.winLabelP2.setText("Wins: " + this.user2.getPlayerProfile().getConnectFourProfile().getTotalWins());
            this.rankLabelP1.setText("Rank: " + this.user1.getPlayerProfile().getConnectFourProfile().getScoreRank());
            this.rankLabelP2.setText("Rank: "+ this.user2.getPlayerProfile().getConnectFourProfile().getScoreRank());
            this.setup = true;
        }
    }

    public void makeMove(int[] coordsArr, Pane newPane) throws IOException {
        this.gameConnect4.makeMove(coordsArr); // gets x coordinate and drops into that column
        this.gameConnect4.switchCurrentPlayer();
        // making move appear on screen
        if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
            newPane.getChildren().add(new Label(" RED "));
            this.infoLabel.setText(this.gameConnect4.getCurrentPlayer() + "'s move!");
        } else {
            newPane.getChildren().add(new Label(" YELLOW "));
            this.infoLabel.setText(this.gameConnect4.getCurrentPlayer() + "'s move!");
        }

        if(this.gameConnect4.validateGameEnds().equals(AbstractBoardGame.GameEndState.Draw)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("This Game Has Reached A Stalemate");
            alert.setTitle("Draw!");
            exitBtnFunc();

        } else if(this.gameConnect4.validateGameEnds().equals(AbstractBoardGame.GameEndState.Victory)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You Won!");
            alert.setTitle("Winner!");
            exitBtnFunc();

        }
    }

    public void exitBtnFunc() throws IOException { // Switch to tic tac toe main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Connect_4_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Connect-4");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }

    public void sendBtnFunc(){
        String chatTxt = "";
        if(this.gameConnect4.getCurrentPlayer().equals(this.user1.getUsername())){
            chatTxt += this.user1.getUsername() + ": ";
        }else{
            chatTxt += this.user2.getUsername() + ": ";
        }

        chatTxt += this.chatTxtField.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }
}
