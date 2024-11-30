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
    //chess can only take 2 players
    public Chess() {
        super(2);
    }
    

    @Override
    protected Piece[][] setUpBoard() {
        Piece[][] board = this.gameBoard;
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
        return board;
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

}
