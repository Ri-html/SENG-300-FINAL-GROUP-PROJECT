import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import gameLogic.side.ConnectFourSide;
import org.junit.jupiter.api.Test;
import gameLogic.ConnectFour;
import gameLogic.piece.ConnectFourPiece;

public class ConnectFourTest {

    /*
    TESTS FOR BASIC SETUP
     */
    @Test
    void boardEmpty() {
        ConnectFour connectFour = new ConnectFour(2);
        ConnectFourPiece[][] testBoard = new ConnectFourPiece[6][7];
        for (int i = 0; i < connectFour.getBoard().length; i++) {
            for (int j = 0; j < connectFour.getBoard()[0].length; j++) {
               assertEquals(connectFour.getBoard()[i][j], testBoard[i][j]);
            }
        }
    }

    @Test
    void boardFull() {

    }


    /*
    TEST PLACING PIECES
     */
    @Test
    void placesPiece() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{1,0});
        assertNotNull(connectFour.getBoard()[connectFour.getBoard().length - 1][1]);
        assertEquals(
                ConnectFourSide.RED,
                connectFour.getBoard()[connectFour.getBoard().length-1][1].getSide()
        );
    }

    @Test
    void pieceFalls() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        assertEquals(
                ConnectFourSide.RED,
                connectFour.getBoard()[connectFour.getBoard().length - 1][0].getSide()
        );
    }
}
