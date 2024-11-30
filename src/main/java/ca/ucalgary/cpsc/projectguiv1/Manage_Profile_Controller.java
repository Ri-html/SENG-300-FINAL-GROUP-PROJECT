package ca.ucalgary.cpsc.projectguiv1;

import authProfile.ChessProfile;
import authProfile.TicTacToeProfile;
import authProfile.ConnectFourProfile;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Manage_Profile_Controller {

    @FXML
    TextArea gameHistory1;

    @FXML
    TextArea gameHistory2;

    @FXML
    Text game1;

    @FXML
    Text game2;


    private ChessProfile chessProfile = new ChessProfile();
    private TicTacToeProfile ticTacToeProfile = new TicTacToeProfile();
    private ConnectFourProfile connectFourProfile = new ConnectFourProfile();



    private void displayGameHistory() {
        game1.setText("Chess");
        gameHistory1.setText("Opponent: " + chessProfile.getLastOpponent() + "\n" +
                             "Result: " + chessProfile.getLastGameResult() + "\n" +
                             "Score: " + chessProfile.getLastGameScore());

        game2.setText("Tic-Tac-Toe");
        gameHistory2.setText("Opponent: " + ticTacToeProfile.getLastOpponent() + "\n" +
                "Result: " + ticTacToeProfile.getLastGameResult() + "\n" +
                "Score: " + ticTacToeProfile.getLastGameScore());

    }



}
