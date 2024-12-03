package ca.ucalgary.cpsc.projectguiv1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home_Page_Controller {
    @FXML
    GridPane identity;

    @FXML
    Label mainTitle;

    @FXML
    TextField searchBar;

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

}
