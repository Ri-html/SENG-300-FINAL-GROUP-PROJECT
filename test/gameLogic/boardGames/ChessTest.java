package gameLogic.boardGames;

import gameLogic.side.ChessSide;
import org.junit.Test;
import gameLogic.Chess;
import gameLogic.piece.chessPiece.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessTest {

    //test board setup
    @Test
    public void boardSetup(){
        Chess chess = new Chess();
        AbstractChessPiece[][] testBoard = new AbstractChessPiece[8][8];
        //manually add all white pieces
        testBoard[0][0] = new Rook(ChessSide.WHITE);
        testBoard[0][7] = new Rook(ChessSide.WHITE);
        testBoard[0][1] = new Knight(ChessSide.WHITE);
        testBoard[0][6] = new Knight(ChessSide.WHITE);
        testBoard[0][2] = new Bishop(ChessSide.WHITE);
        testBoard[0][5] = new Bishop(ChessSide.WHITE);
        testBoard[0][3] = new Queen(ChessSide.WHITE);
        testBoard[0][4] = new King(ChessSide.WHITE);
        testBoard[1][0] = new Pawn(ChessSide.WHITE);
        testBoard[1][1] = new Pawn(ChessSide.WHITE);
        testBoard[1][2] = new Pawn(ChessSide.WHITE);
        testBoard[1][3] = new Pawn(ChessSide.WHITE);
        testBoard[1][4] = new Pawn(ChessSide.WHITE);
        testBoard[1][5] = new Pawn(ChessSide.WHITE);
        testBoard[1][6] = new Pawn(ChessSide.WHITE);
        testBoard[1][7] = new Pawn(ChessSide.WHITE);

        //manually add all black pieces
        testBoard[7][0] = new Rook(ChessSide.WHITE);
        testBoard[7][7] = new Rook(ChessSide.WHITE);
        testBoard[7][1] = new Knight(ChessSide.WHITE);
        testBoard[7][6] = new Knight(ChessSide.WHITE);
        testBoard[7][2] = new Bishop(ChessSide.WHITE);
        testBoard[7][5] = new Bishop(ChessSide.WHITE);
        testBoard[7][3] = new Queen(ChessSide.WHITE);
        testBoard[7][4] = new King(ChessSide.WHITE);
        testBoard[6][0] = new Pawn(ChessSide.WHITE);
        testBoard[6][1] = new Pawn(ChessSide.WHITE);
        testBoard[6][2] = new Pawn(ChessSide.WHITE);
        testBoard[6][3] = new Pawn(ChessSide.WHITE);
        testBoard[6][4] = new Pawn(ChessSide.WHITE);
        testBoard[6][5] = new Pawn(ChessSide.WHITE);
        testBoard[6][6] = new Pawn(ChessSide.WHITE);
        testBoard[6][7] = new Pawn(ChessSide.WHITE);

        //check pieces are in correct location
        for (int i = 0; i < chess.getBoard().length; i++) {
            //even though this should be square, just make sure
            for (int j = 0; j < chess.getBoard()[0].length; j++) {
                //check piece at each location is same type and colour
                assertEquals(chess.getBoard()[i][j].getSide(), testBoard[i][j].getSide());
                assertEquals(chess.getBoard()[i][j].getClass(), testBoard[i][j].getClass());
            }
        }
    }

    //Test pawn valid moves
    @Test
    public void pawnValidMovesTest(){
        Chess chess = new Chess();
        AbstractChessPiece[][] testBoard = new AbstractChessPiece[8][8];
        testBoard[1][2] = new Pawn(ChessSide.WHITE);
        //its valid moves should be to (2,2) and (3,2)
        testBoard[1][2].
    }

    //Test pawn movement
    @Test
    public void pawnMoveTest1(){
        Chess chess = new Chess();
        AbstractChessPiece[][] board = new AbstractChessPiece[8][8];
        board[1][2] = new Pawn(ChessSide.WHITE);
        //check initial double move forward
        board[1][2].makeMove(3, 2);
        //check old location has been cleared correctly
        //is an empty square null or something else?
        assertNull(board[1][2]);
        assertNotNull(board[3][2]); //check piece has moved to (3,2)
        //check single move forward

    }

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
