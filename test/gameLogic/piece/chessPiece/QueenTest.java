package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.piece.chessPiece.AbstractChessPiece;
import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;


public class QueenTest {

    @Test
    public void queenValidMovesTest1(){
        Chess chess = new Chess();
        // Move pawn to let queen move
        ((AbstractChessPiece)chess.getBoard()[1][2]).makeMove(chess.getBoard(), 2, 2);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        int[][] valids = b.getValidMoves(chess.getBoard());
        int[][] expecteds = new int[][]{
                {1,2},{2,1},{3,0}};
        assertArrayEquals(valids, expecteds);
    }

    @Test
    public void queenValidMovesTest2() {
        Chess chess = new Chess();
        // Move pawn to let queen move
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 2, 4);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        int[][] valids = b.getValidMoves(chess.getBoard());
        int[][] expecteds = new int[][]{
                {1,4},{2,5},{3,6}, {4,7}};
        assertArrayEquals(valids, expecteds);
    }
    @Test
    public void queenMoveTest1() {
        Chess chess = new Chess();
        //move pawn to be able to access queen
        ((AbstractChessPiece)chess.getBoard()[1][2]).makeMove(chess.getBoard(), 2, 2);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        b.makeMove(chess.getBoard(), 2, 1);
        assertNotNull(chess.getBoard()[2][1]);
    }

    @Test
    public void queenMoveTest2() {
        Chess chess = new Chess();
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        b.makeMove(chess.getBoard(), 0, 4);
        assertNotNull(chess.getBoard()[0][3]);
    }

    @Test
    public void queenMoveTest3() {
        Chess chess = new Chess();
        //move pawn to be able to access queen
        ((AbstractChessPiece)chess.getBoard()[1][3]).makeMove(chess.getBoard(), 3, 3);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        b.makeMove(chess.getBoard(), 2, 3);
        assertNotNull(chess.getBoard()[2][3]);
    }

    @Test
    public void queenCapTest1() {
        Chess chess = new Chess();
        //move pawn to be able to access queen
        ((AbstractChessPiece)chess.getBoard()[1][2]).makeMove(chess.getBoard(), 2, 2);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        b.makeMove(chess.getBoard(), 3, 0);
        // Capture pawn
        b.makeMove(chess.getBoard(), 6, 0);
        assertTrue(chess.getBoard()[6][0].getSide() == ChessSide.WHITE);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }

    @Test
    public void queenCapTest2() {
        Chess chess = new Chess();
        //move pawn to be able to access queen
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 2, 4);
        ((AbstractChessPiece)chess.getBoard()[6][3]).makeMove(chess.getBoard(), 5, 3);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][3];
        b.makeMove(chess.getBoard(), 3, 6);
        // Capture bishop
        b.makeMove(chess.getBoard(), 7, 2);
        assertTrue(chess.getBoard()[7][2].getSide() == ChessSide.WHITE);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }
}
