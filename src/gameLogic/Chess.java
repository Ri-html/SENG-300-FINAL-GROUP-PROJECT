package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.piece.chessPiece.*;
import gameLogic.side.ChessSide;

import java.util.ArrayList;

public class Chess extends AbstractBoardGame {

    public static ArrayList<Piece> capturedPieces;

    //somehow set game board to be [8][8]?

    //constructor
    //chess can only take 2 players
    public Chess() {
        super(2);
        setUpBoard();
    }
    

    @Override
    protected Piece[][] setUpBoard() {
        Piece[][] board = new Piece[8][8];
        //need to assign pieces to a player
        //add white pieces to board
        board[0][0] = new Rook(ChessSide.WHITE);
        board[0][1] = new Knight(ChessSide.WHITE);
        board[0][2] = new Bishop(ChessSide.WHITE);
        board[0][3] = new Queen(ChessSide.WHITE);
        board[0][4] = new King(ChessSide.WHITE);
        board[0][5] = new Bishop(ChessSide.WHITE);
        board[0][6] = new Knight(ChessSide.WHITE);
        board[0][7] = new Rook(ChessSide.WHITE);
        //add white pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(ChessSide.WHITE);
        }
        //add black pieces to board
        board[7][0] = new Rook(ChessSide.BLACK);
        board[7][1] = new Knight(ChessSide.BLACK);
        board[7][2] = new Bishop(ChessSide.BLACK);
        board[7][3] = new Queen(ChessSide.BLACK);
        board[7][4] = new King(ChessSide.BLACK);
        board[7][5] = new Bishop(ChessSide.BLACK);
        board[7][6] = new Knight(ChessSide.BLACK);
        board[7][7] = new Rook(ChessSide.BLACK);
        //add black pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(ChessSide.BLACK);
        }
        this.gameBoard = board;
        return board;
    }

    /**
     * Returns gameBoard state
     */
    public Piece[][] getBoard() {
        return gameBoard;
    }

    @Override
    public GameEndState validateGameEnds() {
        return GameEndState.Ongoing;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return false;
    }

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
