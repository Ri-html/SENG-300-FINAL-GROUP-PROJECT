package ca.ucalgary.cpsc.projectguiv1;

import authProfile.User;
import gameLogic.Chess;
import gameLogic.boardGames.BoardGameObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class ChessGameController extends AbstractGameController {

    //Chat related properties
    @FXML
    ScrollPane chatScrlPane;

    @FXML
    VBox chatBox;

    @FXML
    TextField chatTxtField;

    @FXML
    Button sendBtn;

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
    AnchorPane afterlife1;

    @FXML
    AnchorPane afterlife2;

    private boolean setup = false;

    private ArrayList<Pane> arrOfPanes = new ArrayList<>();

    private ArrayList<String> arrOfPaneCoords = new ArrayList<>();

    @FXML
    public void initialize() {
        game=new Chess();
        game.addPlayer(user1.getUsername());
        game.addPlayer(user2.getUsername());
        player1Name.setText(user1.getUsername());
        player2Name.setText(user2.getUsername());
        this.currentPlayer=user1.getUsername();
        rankLabelP1.setText(String.valueOf(user1.getPlayerProfile().getChessProfile().getScoreRank()));
        rankLabelP2.setText(String.valueOf(user2.getPlayerProfile().getChessProfile().getScoreRank()));

    }

    @FXML
    public void exitBtnFunc() throws IOException { // Switch to chess main menu
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Chess_Main_Menu_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu Chess Game");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();
    }


    @FXML
    public void handleClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        //int row = (int) (y / CELL_SIZE); // Calculate row index
        //int col = (int) (x / CELL_SIZE); // Calculate column index
    }

    public void sendBtnFunc() {
        String chatTxt = "";
        if (game.getCurrentPlayer().equals(user1.getUsername())) {
            chatTxt += user1.getUsername() + ": ";
        } else {
            chatTxt += user2.getUsername() + ": ";
        }
        chatTxt += chatTxtField.getText();
        this.chatBox.getChildren().add(new Label(chatTxt));
        this.chatScrlPane.setContent(this.chatBox);
    }

    @Override
    public void setUpBoard(String board) {
        if(this.setup == false){
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    Pane currPane = new Pane();
                    int xCoord = x;
                    int yCoord = y;
                    this.gamePane.add(currPane, xCoord, yCoord);
                    arrOfPanes.add(currPane);
                    arrOfPaneCoords.add(y + " " + x);
                    currPane.setOnMouseClicked(mouseEvent -> {
                        int[] coordsArr = {xCoord, yCoord};
                        try {
                            makeMove(coordsArr, currPane);
                        } catch (IOException ioe) {
                            System.out.println("io exception");
                        }
                    });
                }
            }

            this.setup = true;
        }
    }

    public void makeMove(int[] coordsArr, Pane currPane) throws IOException{

    }
}
