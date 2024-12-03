package gameLogic;

import gameLogic.piece.Piece;
import gameLogic.piece.TicTacToePiece;
import gameLogic.side.TicTacToeSide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setUpBoard() {
        TicTacToe game = new TicTacToe(2);
        Piece[][] board = game.setUpBoard();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertNull(board[x][y]);
            }
        }
    }

    @Test
    public void validateGameEnds() {
        TicTacToe game = new TicTacToe(2);
        Piece[][] board = game.setUpBoard();
    }

    @Test
    public void validateMove() {
    }

    @Test
    public void makeMove() {
        TicTacToe game = new TicTacToe(2);
        Piece[][] board = game.setUpBoard();
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.setCurrentPlayer(0);
        //game.checkStartCondition();
        //int[] move = {0,0};
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                game.makeMove(new int[]{x, y});
                assertEquals(board[x][y], new TicTacToePiece(TicTacToeSide.X));

            }
        }


    }
}