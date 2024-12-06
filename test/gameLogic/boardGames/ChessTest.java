package gameLogic.boardGames;

import gameLogic.side.ChessSide;
import org.junit.Test;
import gameLogic.Chess;
import gameLogic.piece.chessPiece.*;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        AbstractChessPiece p1 = ((AbstractChessPiece)chess.getBoard()[1][1]);
        assertArrayEquals(p1.getLocation(), new int[]{1, 1});
    }


    @Test
    public void TestisInCheck1() {
        Chess chess = new Chess();
        chess.addPlayer("a - white");
        chess.addPlayer("b - black");
        AbstractChessPiece knight = (AbstractChessPiece) chess.getBoard()[7][1];
        AbstractChessPiece king = (AbstractChessPiece) chess.getBoard()[0][4];

        knight.makeMove(chess.getBoard(), 5, 2);
        knight.makeMove(chess.getBoard(), 3, 1);
        knight.makeMove(chess.getBoard(), 2, 3);

        String boardString = Arrays.deepToString(chess.getBoard());
        boardString = boardString.replace("], ", "],\n");
        System.out.println(boardString);

        // inCheck is working,
        boolean inCheck = king.isInCheck(chess.getBoard());
        System.out.println("is in check? " + inCheck);

        //black to move should end game here.
        chess.setCurrentPlayer(1);
        System.out.println("curr player: " + chess.getCurrentPlayer());

        int gameState = chess.validateGameEnds();
        System.out.println(gameState);
        //black wins because white king in check & black to move
        assertEquals(gameState, 2);
    }

    @Test
    public void TestisInCheck2() {
        Chess chess = new Chess();
        chess.addPlayer("a - white");
        chess.addPlayer("b - black");

        AbstractChessPiece knight = (AbstractChessPiece) chess.getBoard()[7][1];
        AbstractChessPiece king = (AbstractChessPiece) chess.getBoard()[0][4];

        knight.makeMove(chess.getBoard(), 5, 2);
        knight.makeMove(chess.getBoard(), 3, 1);
        knight.makeMove(chess.getBoard(), 2, 3);

        String boardString = Arrays.deepToString(chess.getBoard());
        boardString = boardString.replace("], ", "],\n");
        System.out.println(boardString);

        // inCheck is working,
        boolean inCheck = king.isInCheck(chess.getBoard());
        System.out.println(Arrays.deepToString(chess.getBoard()));
        System.out.println("is in check? " + inCheck);

        //white to move should not end the game.
        chess.setCurrentPlayer(0);
        System.out.println("curr player: " + chess.getCurrentPlayer());
        //this loop printed all valid moves for each piece on the board for manual checking
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 7; j++) {
//                if(chess.getBoard()[i][j] != null) {
//                    int[][] valids = ((AbstractChessPiece) chess.getBoard()[i][j]).getValidMoves(chess.getBoard());
//                    System.out.println("piece " + chess.getBoard()[i][j].getClass() + " is side " + chess.getBoard()[i][j].getSide() + " at pos (" + i + ","+ j + ")");
//                    System.out.println(Arrays.deepToString(valids));
//                }
//            }
//        }
        int gameState = chess.validateGameEnds();
        System.out.println(gameState);
        //white has valid move to capture knight, so game should continue
        assertEquals(-1, gameState);
    }



    //game doesn't end on startup
    @Test
    public void validateGameEndsTest1(){
        Chess chess = new Chess();
        assertEquals(chess.validateGameEnds(), -1);
    }

    //check game doesn't end for some pawn check
    @Test
    public void validateGameEndsTest2(){
        Chess chess = new Chess();
        chess.addPlayer("a - white");
        chess.addPlayer("b - black");

        AbstractChessPiece p = (AbstractChessPiece) chess.getBoard()[6][2];
        AbstractChessPiece k = (AbstractChessPiece) chess.getBoard()[0][4];
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 3, 2);
        p.makeMove(chess.getBoard(), 2, 2);
        p.makeMove(chess.getBoard(), 1, 3);
        chess.setCurrentPlayer(0);
        //validate game end should return -1 because pawn can be captured
        assertEquals(-1,chess.validateGameEnds());
    }

    @Test
    public void validateGameEndsTest3(){
        Chess chess = new Chess();
        chess.addPlayer("a - white");
        chess.addPlayer("b - black");

        AbstractChessPiece p = (AbstractChessPiece) chess.getBoard()[6][2];
        AbstractChessPiece k = (AbstractChessPiece) chess.getBoard()[0][4];
        p.makeMove(chess.getBoard(), 4, 2);
        p.makeMove(chess.getBoard(), 3, 2);
        p.makeMove(chess.getBoard(), 2, 2);
        p.makeMove(chess.getBoard(), 1, 3);
        //black to move
        chess.setCurrentPlayer(1);
        //validate game end should return 2 because B checkmate W king
        assertEquals(2,chess.validateGameEnds());
    }

}
