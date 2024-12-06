package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.piece.chessPiece.AbstractChessPiece;
import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;


public class KingTest {
    
    @Test
    public void kingValidMovesTest1(){
        Chess chess = new Chess();
        //move pawn to be able to access king
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece k = (AbstractChessPiece)chess.getBoard()[0][4];
        int[][] valids = k.getValidMoves(chess.getBoard());
        int[][] expecteds = new int[][]{
                {1,4},};
        assertArrayEquals(valids, expecteds);
    }

    @Test
    public void kingValidMovesTest2(){
        //test king movement is blocked correctly
        Chess chess = new Chess();
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece k = (AbstractChessPiece)chess.getBoard()[0][4];
        int[][] valids = k.getValidMoves(chess.getBoard());
        //Expect no valid moves
        int[][] expecteds = new int[][]{
                };
        assertArrayEquals(valids, expecteds);
    }

    @Test
    public void kingMoveTest1(){
        //This shouldn't move the king
        Chess chess = new Chess();
        System.out.println(Arrays.deepToString(chess.getBoard()));
        AbstractChessPiece k = (AbstractChessPiece)chess.getBoard()[0][4];
        k.makeMove(chess.getBoard(), 1, 4);
        assertNotNull(chess.getBoard()[0][4]);
    }

    @Test
    public void kingMoveTest2(){
        //This should move the king
        Chess chess = new Chess();
        System.out.println(Arrays.deepToString(chess.getBoard()));
        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[0][4]).makeMove(chess.getBoard(), 1, 4);
        assertNull(chess.getBoard()[0][4]);
        assertNotNull(chess.getBoard()[1][4]);
    }

    @Test
    public void kingCapTest1(){
        Chess chess = new Chess();

        ((AbstractChessPiece)chess.getBoard()[1][4]).makeMove(chess.getBoard(), 3, 4);
        ((AbstractChessPiece)chess.getBoard()[0][4]).makeMove(chess.getBoard(), 1,4);
        System.out.println(Arrays.deepToString(chess.getBoard()));

        AbstractChessPiece p = (AbstractChessPiece)chess.getBoard()[6][5];
        p.makeMove(chess.getBoard(), 4, 5);
        p.makeMove(chess.getBoard(), 3, 5);
        p.makeMove(chess.getBoard(), 2, 5);
        AbstractChessPiece k = (AbstractChessPiece)chess.getBoard()[1][4];
        k.makeMove(chess.getBoard(), 2, 5);
        assertTrue(chess.getBoard()[2][5].getSide() == ChessSide.WHITE);
        System.out.println(Arrays.deepToString(chess.getBoard()));
    }

}
