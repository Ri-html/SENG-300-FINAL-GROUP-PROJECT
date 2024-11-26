package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.player.ChessPlayer;
import gameLogic.piece.chessPiece.*;

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

    }
    

    @Override
    protected void setUpBoard(Piece[][] board) {
        //add pieces to board
        board[0][0] =;
        board[0][1] =;
            //add pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn();
        }
    }

    @Override
    public boolean validateVictory() {
        return false;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return false;
    }

    @Override
    public void makeMove(int[] moves) {

    }

}
