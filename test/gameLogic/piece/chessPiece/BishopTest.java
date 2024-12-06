package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.side.ChessSide;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class BishopTest {
    //Test bishop valid moves
    @Test
    public void bishopValidMovesTest1(){
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        int[][] valids = b.getValidMoves(chess.getBoard());
        int[][] expecteds = new int[][]{
                {1,4},{2,3},{3,2},{4,1},{5,0}};
        assertArrayEquals(valids, expecteds);
    }

    @Test
    public void bishopValidMovesTest2(){
        //test bishop movement is blocked correctly
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4,1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        int[][] valids = b.getValidMoves(chess.getBoard());
        int[][] expecteds = new int[][]{
                {1,4},{2,3},{3,2},{4,1}};
        assertArrayEquals(valids, expecteds);
    }

    //Test bishop movement
    @Test
    public void bishopMoveTest1(){
        //this shouldn't move the bishop
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4,1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        b.makeMove(chess.getBoard(), 3, 4);
        assertNotNull(chess.getBoard()[0][5]);
    }

    @Test
    public void bishopMoveTest2(){
        //this should move the bishop
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4,1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        b.makeMove(chess.getBoard(), 1, 4);
        assertNotNull(chess.getBoard()[1][4]);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }

    //test capturing
    @Test
    public void bishopCapTest1(){
        //this should move the bishop
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4,1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        b.makeMove(chess.getBoard(), 4, 1);
        assertTrue(chess.getBoard()[4][1].getSide() == ChessSide.WHITE);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }

    @Test
    public void bishopCapTest2(){
        //this should move the bishop
        Chess chess = new Chess();
        //move pawn to be able to access bishop
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[6][1]).makeMove(chess.getBoard(), 4,1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece b = (AbstractChessPiece)chess.getBoard()[0][5];
        b.makeMove(chess.getBoard(), 3, 2);
        //bishop is now at (3,2)
        //capture pawn at (6,5)
        b.makeMove(chess.getBoard(), 6, 5);
        assertTrue(chess.getBoard()[6][5].getSide() == ChessSide.WHITE);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }
}
