package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.player.ChessPlayer;
import gameLogic.piece.chessPiece.*;
import gameLogic.side.ChessSide;

import java.util.ArrayList;

public class Chess extends AbstractBoardGame {

    public ArrayList<BoardGameObserver> boardSetupObservers;
    public ArrayList<BoardGameObserver> turnEndObservers;
    public ArrayList<BoardGameObserver> gameEndObservers;
    private String winner = null;
    private String gameID;
    private int currentPlayer;
    private String[] players;
    //define size of board
    protected Piece[][] gameBoard = new Piece[8][8];
    int addedPlayers=0;
    int playerNumber;
    int[]moves;
    private GameState gameState = GameState.WAITING;

    public Chess() {
        super(2);
        this.gameState = GameState.INPROGRESS;
        setUpBoard(this.gameBoard);
        //generate gameid to be this object's hexcode
    }
    

    @Override
    protected void setUpBoard(Piece[][] board) {
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
        for (int i = 0; i < 7; i++) {
            board[7][i] = new Pawn(ChessSide.BLACK);

        }
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
