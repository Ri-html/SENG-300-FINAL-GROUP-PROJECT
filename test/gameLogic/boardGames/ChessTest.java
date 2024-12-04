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
        testBoard[7][0] = new Rook(ChessSide.BLACK);
        testBoard[7][7] = new Rook(ChessSide.BLACK);
        testBoard[7][1] = new Knight(ChessSide.BLACK);
        testBoard[7][6] = new Knight(ChessSide.BLACK);
        testBoard[7][2] = new Bishop(ChessSide.BLACK);
        testBoard[7][5] = new Bishop(ChessSide.BLACK);
        testBoard[7][3] = new Queen(ChessSide.BLACK);
        testBoard[7][4] = new King(ChessSide.BLACK);
        testBoard[6][0] = new Pawn(ChessSide.BLACK);
        testBoard[6][1] = new Pawn(ChessSide.BLACK);
        testBoard[6][2] = new Pawn(ChessSide.BLACK);
        testBoard[6][3] = new Pawn(ChessSide.BLACK);
        testBoard[6][4] = new Pawn(ChessSide.BLACK);
        testBoard[6][5] = new Pawn(ChessSide.BLACK);
        testBoard[6][6] = new Pawn(ChessSide.BLACK);
        testBoard[6][7] = new Pawn(ChessSide.BLACK);

        //check pieces are in correct location
        for (int i = 0; i < chess.getBoard().length; i++) {
            //even though this should be square, just make sure
            for (int j = 0; j < chess.getBoard()[0].length; j++) {
                //check piece at each location is same type and colour
                if (chess.getBoard()[i][j] != null) {
                    assertEquals(chess.getBoard()[i][j].getSide(), testBoard[i][j].getSide());
                    assertEquals(chess.getBoard()[i][j].getClass(), testBoard[i][j].getClass());
                }
            }
        }
    }


}
