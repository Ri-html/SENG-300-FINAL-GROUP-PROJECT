package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.piece.chessPiece.*;
import gameLogic.side.ChessSide;

import java.util.ArrayList;

public class Chess extends AbstractBoardGame {

    public static ArrayList<Piece> capturedPieces;

    //constructor
    //chess can only take 2 players, board is 8x8
    public Chess() {
        super(2, 8, 8);
        setUpBoard();
    }
    

    @Override
    protected Piece[][] setUpBoard() {
        //need to assign pieces to a player
        //add white pieces to board
        gameBoard[0][0] = new Rook(ChessSide.WHITE);
        gameBoard[0][1] = new Knight(ChessSide.WHITE);
        gameBoard[0][2] = new Bishop(ChessSide.WHITE);
        gameBoard[0][3] = new Queen(ChessSide.WHITE);
        gameBoard[0][4] = new King(ChessSide.WHITE);
        gameBoard[0][5] = new Bishop(ChessSide.WHITE);
        gameBoard[0][6] = new Knight(ChessSide.WHITE);
        gameBoard[0][7] = new Rook(ChessSide.WHITE);
        //add white pawns
        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = new Pawn(ChessSide.WHITE);
        }
        //add black pieces to board
        gameBoard[7][0] = new Rook(ChessSide.BLACK);
        gameBoard[7][1] = new Knight(ChessSide.BLACK);
        gameBoard[7][2] = new Bishop(ChessSide.BLACK);
        gameBoard[7][3] = new Queen(ChessSide.BLACK);
        gameBoard[7][4] = new King(ChessSide.BLACK);
        gameBoard[7][5] = new Bishop(ChessSide.BLACK);
        gameBoard[7][6] = new Knight(ChessSide.BLACK);
        gameBoard[7][7] = new Rook(ChessSide.BLACK);
        //add black pawns
        for (int i = 0; i < 8; i++) {
            gameBoard[6][i] = new Pawn(ChessSide.BLACK);
        }
        return gameBoard;
    }

    /**
     * Returns gameBoard state
     */
    public Piece[][] getBoard() {
        return gameBoard;
    }

    @Override
    public int validateGameEnds() {
        return -1;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return false;
    }


    /**
     * Deprecated - use makeMove in AbstractChessPiece
     */
    @Override
    public void makeMove(int[] moves) {

    }

    // Helper function that will determine if king is in check.
    public boolean isInCheck(ChessSide side)
    {
        int kingX = -1;
        int kingY = -1;

        // Find the king's position
        // This can be delted, it's just brute force
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = gameBoard[i][j];
                if (piece instanceof King && piece.getSide() == side)
                {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }

        // Need to add logic to look for attacking opp's

        return false;
    }

}
