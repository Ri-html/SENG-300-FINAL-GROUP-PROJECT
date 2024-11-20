package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;
import gameLogic.player.AbstractPlayer;

import java.util.ArrayList;

public class AbstractBoardGame implements BoardGame {
    public ArrayList<BoardGameObserver> observers;
    private String winner;
    private String gameID;
    private AbstractPlayer currentPlayer;
    private AbstractPlayer[] players;
    protected Piece[][] gameBoard;

    public void attach(BoardGameObserver observer){
        observers.add(observer);
    }

    public void detach(BoardGameObserver observer){
        observers.remove(observer);
    }
    public void notifyObservers(Piece piece){};
    //cannot add a notify() method, Object's notify() is final.
    public Piece getPiece(){
        return null;
    }

    public void switchCurrentPlayer(){

    }

    public void setWinner(){

    }

    @Override
    public void placeBoardPiece(AbstractPiece piece, int x, int y) {
    }

    @Override
    public void setUpBoard() {
    }
}
