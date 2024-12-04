package ca.ucalgary.cpsc.projectguiv1;

import UserAndProfile.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Home_Page_Controller implements Initializable {
    @FXML
    GridPane identity;

    @FXML
    Button gameHistory;
    @FXML
    Label mainTitle;

    @FXML
    Button chessSelection;

    @FXML
    Button tictactoeSelection;

    @FXML
    Button connect4Selection;

    @FXML
    Label rankingChessLbl;

    @FXML
    Label rankingTicTacToeLbl;

    @FXML
    Label rankingConnect4Lbl;

    @FXML
    TextField searchBar;

    @FXML
    ListView<String> listView;
    private User user1;

    public Home_Page_Controller(){
        user1 = new User("1", "john", "john@email.com");
    }

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("John", "Macy", "Sarah")
    );

    @FXML
    void search(ActionEvent event){
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        listView.getItems().addAll(words);

        // Labels under games
        rankingTicTacToeLbl.setText("Ranking: " + user1.getPlayerProfile().getTicTacToeProfile().getScoreRank());
        rankingConnect4Lbl.setText("Ranking: " + user1.getPlayerProfile().getConnectFourProfile().getScoreRank());
        rankingChessLbl.setText("Ranking: "+ user1.getPlayerProfile().getChessProfile().getScoreRank());
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings){
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }


    public void selectionFxn(String file) throws IOException { // Switch to sign up page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage newStg = new Stage();
        newStg.sizeToScene();
        newStg.setTitle("Main Menu");
        newStg.setScene(scene);
        newStg.show();
        Stage stgWindw = (Stage) this.identity.getScene().getWindow();
        stgWindw.close();

    }

    public void chessSelectionFxn() throws IOException {
        String file = "Chess_Main_Menu_View.fxml";
        selectionFxn(file);
    }

    public void ticTacToeSelectionFxn() throws IOException {
        String file = "Tic_Tac_Toe_Main_Menu_View.fxml";
        selectionFxn(file);
    }

    public void connect4SelectionFxn() throws IOException {
        String file = "Connect_4_Main_Menu_View.fxml";
        selectionFxn(file);
    }

    public void gameHistorySelectionFxn() throws IOException {
        String file = "Game_History_Screen.fxml";
        selectionFxn(file);
    }

}
