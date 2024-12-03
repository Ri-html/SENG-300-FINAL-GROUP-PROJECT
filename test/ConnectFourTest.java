import gameLogic.side.ConnectFourSide;
import org.junit.jupiter.api.Test;
import gameLogic.ConnectFour;
import gameLogic.piece.ConnectFourPiece;

import static org.junit.jupiter.api.Assertions.*;

// TESTS FOR CONNECT FOUR IMPLEMENTATION OF BOARD GAME
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

    @Test
    void pieceFallsOnTopOfPiece() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        assertNotNull(connectFour.getBoard()[connectFour.getBoard().length - 1][0]);
        assertNotNull(connectFour.getBoard()[connectFour.getBoard().length - 2][0]);
    }


    /*
    VALIDATE MOVES WORKS
     */

    @Test
    void canPlaceOnEmptyBoard() {
        ConnectFour connectFour = new ConnectFour(2);
        assertTrue(connectFour.validateMove(new int[]{0,0}));
    }

    @Test
    void cannotPlaceOnFullColumn() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        assertFalse(connectFour.validateMove(new int[]{0,0}));
    }

    @Test
    void canPlaceOnHalfFullColumn() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        assertTrue(connectFour.validateMove(new int[]{0,0}));
    }

    /*
     * VALIDATE WIN CONDITIONS
     */

    @Test
    void RecognizesOngoingBoard() {
        ConnectFour connectFour = new ConnectFour(2);
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
    }

    @Test
    void RecognizesHorizontalWin() {
        ConnectFour connectFour = new ConnectFour(2);
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{1,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{2,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{3,0});
        assertEquals("Victory", connectFour.validateGameEnds().name());
    }

    @Test
    void RecognizesVerticalWin() {
        ConnectFour connectFour = new ConnectFour(2);
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());
        connectFour.makeMove(new int[]{0,0});
        assertEquals("Victory", connectFour.validateGameEnds().name());
    }

    @Test
    void RecognizesDiagonalUpRightWin() {
        ConnectFour connectFour = new ConnectFour(2);
        assertEquals("Ongoing", connectFour.validateGameEnds().name());

        connectFour.makeMove(new int[]{0,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        connectFour.makeMove(new int[]{2,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        assertEquals("Ongoing", connectFour.validateGameEnds().name());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{3,0});
        connectFour.makeMove(new int[]{3,0});
        connectFour.makeMove(new int[]{3,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{3,0});

        assertEquals("Victory", connectFour.validateGameEnds().name());
    }

    @Test
    void drawOnBoardFullNoWins() {
        ConnectFour connectFour = new ConnectFour(2);
        for (int i = 0; i <= connectFour.getBoard().length; i++) {
            for (int j = 0; j < connectFour.getBoard()[0].length; j++) {
                connectFour.makeMove(new int[]{i,j});
                connectFour.makeMove(new int[]{i,j});
                connectFour.switchCurrentPlayer();
            }
        }
        System.out.println(connectFour);
        assertEquals("Draw", connectFour.validateGameEnds().name());
    }
}
