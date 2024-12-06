package gameLogic.boardGames;

import gameLogic.side.ConnectFourSide;
import gameLogic.ConnectFour;
import gameLogic.piece.ConnectFourPiece;
import static org.junit.Assert.*;
import org.junit.Test;


// TESTS FOR CONNECT FOUR IMPLEMENTATION OF BOARD GAME
public class ConnectFourTest {

    /*
    TESTS FOR BASIC SETUP
     */
    @Test
    public void boardEmpty() {
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
    public void placesPiece() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{1,0});
        assertNotNull(connectFour.getBoard()[connectFour.getBoard().length - 1][1]);
        assertEquals(
                ConnectFourSide.RED,
                connectFour.getBoard()[connectFour.getBoard().length-1][1].getSide()
        );
    }

    @Test
    public void pieceFalls() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        assertEquals(
                ConnectFourSide.RED,
                connectFour.getBoard()[connectFour.getBoard().length - 1][0].getSide()
        );
    }

    @Test
    public void pieceFallsOnTopOfPiece() {
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
    public void canPlaceOnEmptyBoard() {
        ConnectFour connectFour = new ConnectFour(2);
        assertTrue(connectFour.validateMove(new int[]{0,0}));
    }

    @Test
    public void cannotPlaceInvalidXIndex() {
        ConnectFour connectFour = new ConnectFour(2);
        assertFalse(connectFour.validateMove(new int[]{-1,0}));
        assertFalse(connectFour.validateMove(new int[]{connectFour.getBoard()[0].length, 0}));
    }

    @Test
    public void cannotPlaceOnFullColumn() {
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
    public void canPlaceOnHalfFullColumn() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeMove(new int[]{0,0});
        assertTrue(connectFour.validateMove(new int[]{0,0}));
    }

    /*
     * VALIDATE WIN CONDITIONS
     */

    @Test
    public void RecognizesOngoingBoard() {
        ConnectFour connectFour = new ConnectFour(2);
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
    }

    @Test
    public void RecognizesHorizontalWin() {
        ConnectFour connectFour = new ConnectFour(2);
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{1,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{2,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{3,0});
        //assertEquals("Victory", connectFour.validateGameEnds().name());
        assertEquals(1, connectFour.validateGameEnds());
    }

    @Test
    public void RecognizesVerticalWin() {
        ConnectFour connectFour = new ConnectFour(2);
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());
        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Victory", connectFour.validateGameEnds().name());
        assertEquals(1, connectFour.validateGameEnds());
    }

    @Test
    public void RecognizesDiagonalUpRightWin() {
        ConnectFour connectFour = new ConnectFour(2);
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.makeMove(new int[]{0,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        connectFour.makeMove(new int[]{2,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{3,0});
        connectFour.makeMove(new int[]{3,0});
        connectFour.makeMove(new int[]{3,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{3,0});

        //assertEquals("Victory", connectFour.validateGameEnds().name());
        assertEquals(1, connectFour.validateGameEnds());
    }

    @Test
    public void RecognizesDiagonalUpLeftWin() {
        ConnectFour connectFour = new ConnectFour(2);
        assertEquals(-1, connectFour.validateGameEnds());
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());

        connectFour.makeMove(new int[]{3,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{2,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        connectFour.makeMove(new int[]{1,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{1,0});
        //assertEquals("Ongoing", connectFour.validateGameEnds().name());
        assertEquals(-1, connectFour.validateGameEnds());

        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.makeMove(new int[]{0,0});
        connectFour.switchCurrentPlayer();
        connectFour.makeMove(new int[]{0,0});

        System.out.println(connectFour);

        //assertEquals("Victory", connectFour.validateGameEnds().name());
        assertEquals(1, connectFour.validateGameEnds());
    }

    @Test
    public void drawOnBoardFullNoWins() {
        ConnectFour connectFour = new ConnectFour(2);
        for (int i = 0; i <= connectFour.getBoard().length; i++) {
            for (int j = 0; j < connectFour.getBoard()[0].length; j++) {
                connectFour.makeMove(new int[]{i,j});
                connectFour.makeMove(new int[]{i,j});
                connectFour.switchCurrentPlayer();
            }
        }
        assertEquals(0, connectFour.validateGameEnds());
    }

    @Test
    public void randomMoveWorks() {
        ConnectFour connectFour = new ConnectFour(2);
        connectFour.makeRandomMove();
        boolean moveMade = false;
        for (int i = 0; i < connectFour.getBoard()[0].length; i++) {
            if (connectFour.getBoard()[connectFour.getBoard().length-1] != null) {
                moveMade = true;
            }
        }
        assertTrue(moveMade);
    }
}
