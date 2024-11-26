package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.player.ChessPlayer;

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
    private GameState gameState;

    public Chess(ChessPlayer player1, ChessPlayer player2) {
        this.GameState = GameState.INPROGRESS;
        setUpBoard();
    }
    

    @Override
    protected void setUpBoard(Piece[][] board) {

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
