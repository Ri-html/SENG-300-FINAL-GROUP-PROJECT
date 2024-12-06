package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.side.ChessSide;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class KnightTest {
    @Test
    public void knightValidMovesTest1(){
        Chess chess = new Chess();
        //a knight has 2 valid moves at start of game, jumping over pawn line
        AbstractChessPiece k = ((AbstractChessPiece)chess.getBoard()[0][1]);
        int[][] valids = k.getValidMoves(chess.getBoard());
        int[][] exp = new int[][] {{2,2},{2,0}};
        assertArrayEquals(valids, exp);
        System.out.println(Arrays.deepToString(valids));
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }

    @Test
    public void knightValidMovesTest2(){
        Chess chess = new Chess();
        //check more moves
        AbstractChessPiece k = ((AbstractChessPiece)chess.getBoard()[0][1]);
        k.makeMove(chess.getBoard(), 2,2);
        assertTrue(((AbstractChessPiece)chess.getBoard()[2][2]).getClass() == Knight.class);
        System.out.println(Arrays.deepToString(k.getValidMoves(chess.getBoard())));
        int[][] valids = k.getValidMoves(chess.getBoard());
        int[][] exp = new int[][] {
                {4,3},{4,1},{0,1},{3,4},{3,0}};
    }

    @Test
    public void knightValidMovesTest3(){
        Chess chess = new Chess();
        AbstractChessPiece k = ((AbstractChessPiece)chess.getBoard()[0][1]);
        k.makeMove(chess.getBoard(), 2,2);
        //move pawn
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4, 1);
        //capture pawn with knight
        k.makeMove(chess.getBoard(), 4,1);
        assertTrue(chess.getBoard()[4][1].getClass() == Knight.class);
    }

}
