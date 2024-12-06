package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.side.ChessSide;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class RookTest
{
    //Test pawn valid moves, might not need this
    @Test
    public void RookValidMovesTest(){
        Chess chess = new Chess();

        // Retrieve the Rook at position [0][0] (a1 in chess notation)
        AbstractChessPiece rook = ((AbstractChessPiece)chess.getBoard()[0][0]);

        // Get the valid moves for the Rook
        int[][] rookValidMoves = rook.getValidMoves(chess.getBoard());

        // Expected moves based on the initial setup
        // (None since the rook is blocked by pawns)
        int[][] expectedMoves = new int[][] {};

        // Assert that the valid moves match the expected ones
        assertArrayEquals("The Rook's valid moves do not match the expected moves.",
                expectedMoves, rookValidMoves);

        System.out.println("Valid moves for Rook at [0][0]:");
        for (int[] move : rookValidMoves)
        {
            System.out.println("Move to: [" + move[0] + "][" + move[1] + "]");
        }
    }

    @Test
    public void RookCapTure()
    {
        Chess chess = new Chess();
        AbstractChessPiece br =((AbstractChessPiece)chess.getBoard()[7][0]);
        AbstractChessPiece wr =((AbstractChessPiece)chess.getBoard()[0][0]);
        AbstractChessPiece p1 =((AbstractChessPiece)chess.getBoard()[1][1]);
        AbstractChessPiece p2 =((AbstractChessPiece)chess.getBoard()[6][0]);
        AbstractChessPiece k =((AbstractChessPiece)chess.getBoard()[7][4]);

        p1.makeMove(chess.getBoard(), 3, 1);
        p2.makeMove(chess.getBoard(), 4, 0);

        p2.makeMove(chess.getBoard(), 3, 1);
        p2.makeMove(chess.getBoard(), 3, 1);
        br.makeMove(chess.getBoard(), 1, 0);
        wr.makeMove(chess.getBoard(), 1, 0);


        wr.makeMove(chess.getBoard(), 7, 0);
        wr.makeMove(chess.getBoard(), 7, 1);
        wr.makeMove(chess.getBoard(), 7, 2);
        wr.makeMove(chess.getBoard(), 7, 3);
        k.makeMove(chess.getBoard(), 7, 4);

        for (Object[] row : chess.getBoard())
        {
            System.out.println(Arrays.toString(row));
        }
    }

}
