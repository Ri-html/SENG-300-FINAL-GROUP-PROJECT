package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.side.ChessSide;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PawnTest {
    //Test pawn valid moves, might not need this
    @Test
    public void pawnValidMovesTest(){
        Chess chess = new Chess();
        AbstractChessPiece[][] testBoard = new AbstractChessPiece[8][8];
        testBoard[1][2] = new Pawn(ChessSide.WHITE);
        //its valid moves should be to (2,2) and (3,2)
        //testBoard[1][2].
    }

    //Test pawn movement
    @Test
    public void pawnMoveTest1(){
        //this fails because getValidMoves() returns null
        Chess chess = new Chess();

    }

    //Test pawn capturing
    @Test
    public void pawnMoveTest2(){
        Chess chess = new Chess();
        AbstractChessPiece[][] board = new AbstractChessPiece[8][8];
        board[1][2] = new Pawn(ChessSide.WHITE);
        board[6][3] = new Pawn(ChessSide.BLACK);
        //test pawn capturing

        //test capture by en passant rule
    }
}
