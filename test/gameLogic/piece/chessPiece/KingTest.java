package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.piece.chessPiece.AbstractChessPiece;
import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;
import org.junit.Test;
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

}
