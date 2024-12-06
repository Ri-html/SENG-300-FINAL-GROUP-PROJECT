package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.side.ChessSide;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class PawnTest {
    //Test pawn valid moves, might not need this
    @Test
    public void pawnValidMovesTest(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[1][2]);
        int[][] pexp = new int[][] {{2,2},{3,2}};
        assertArrayEquals(p.getValidMoves(chess.getBoard()), pexp);
        AbstractChessPiece p1 =((AbstractChessPiece)chess.getBoard()[1][4]);
        int[][] p1exp = new int[][] {{2,4},{3,4}};
        assertArrayEquals(p1.getValidMoves(chess.getBoard()), p1exp);
        AbstractChessPiece p2 =((AbstractChessPiece)chess.getBoard()[6][4]);
        int[][] p2exp = new int[][] {{5,4},{4,4}};
        assertArrayEquals(p2.getValidMoves(chess.getBoard()), p2exp);
    }

    //Test white pawn single move
    @Test
    public void pawnMoveTest1(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[1][2]);
        p.makeMove(chess.getBoard(), 2, 2);
        assertNotNull(chess.getBoard()[2][2]);
        //check location has updated
        int[] loc = ((AbstractChessPiece)chess.getBoard()[2][2]).getLocation();
        assertArrayEquals(loc, new int[] {2,2});
    }

    //Test white pawn double move
    @Test
    public void pawnMoveTest2(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[1][2]);
        p.makeMove(chess.getBoard(), 3, 2);
        assertNotNull(chess.getBoard()[3][2]);
        //check location has updated
        int[] loc = ((AbstractChessPiece)chess.getBoard()[3][2]).getLocation();
        assertArrayEquals(loc, new int[] {3,2});
    }

    //Test black pawn single move
    @Test
    public void pawnMoveTest3(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[6][2]);
        p.makeMove(chess.getBoard(), 5, 2);
        assertNotNull(chess.getBoard()[5][2]);
        //check location has updated
        int[] loc = ((AbstractChessPiece)chess.getBoard()[5][2]).getLocation();
        assertArrayEquals(loc, new int[] {5,2});
    }

    //Test black pawn double move
    @Test
    public void pawnMoveTest4(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[6][2]);
        p.makeMove(chess.getBoard(), 4, 2);
        assertNotNull(chess.getBoard()[4][2]);
        //check location has updated
        int[] loc = ((AbstractChessPiece)chess.getBoard()[4][2]).getLocation();
        assertArrayEquals(loc, new int[] {4,2});
    }

    //Test pawn capturing
    @Test
    public void pawnCapTest1(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[6][2]);
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 3, 2);
        //this is not a valid capture move, so it won't be made
        p.makeMove(chess.getBoard(), 2, 3);
        assertNotNull(chess.getBoard()[3][2]);
    }

    //Test pawn capturing
    @Test
    public void pawnCapTest2(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[6][2]);
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 3, 2);
        p.makeMove(chess.getBoard(), 2, 2);
        p.makeMove(chess.getBoard(), 1, 3);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        assertTrue(chess.getBoard()[1][3].getSide() == ChessSide.BLACK);
    }


    @Test
    public void pawnCapTest3(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[6][2]);
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 3, 2);
        p.makeMove(chess.getBoard(), 2, 2);
        p.makeMove(chess.getBoard(), 1, 1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        assertTrue(chess.getBoard()[1][1].getSide() == ChessSide.BLACK);
    }

    @Test
    public void pawnCapTest4(){
        Chess chess = new Chess();
        AbstractChessPiece p =((AbstractChessPiece)chess.getBoard()[1][2]);
        p.makeMove(chess.getBoard(), 3, 2);
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 5, 2);
        p.makeMove(chess.getBoard(), 6, 1);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        assertTrue(chess.getBoard()[6][1].getSide() == ChessSide.WHITE);
    }
}
