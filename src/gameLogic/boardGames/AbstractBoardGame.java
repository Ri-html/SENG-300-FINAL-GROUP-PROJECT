package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;
import gameLogic.player.AbstractPlayer;

import java.util.ArrayList;

public abstract class AbstractBoardGame implements BoardGame {
    public ArrayList<BoardGameObserver> observers;
    private String winner;
    private String gameID;
    private String currentPlayer;
    private String[] players;
    protected Piece[][] gameBoard;
    int addedPlayers=0;
    int playerNumber;
    private GameState gameState;

    public AbstractBoardGame(int playerNum) {
        gameState = GameState.WAITING;
        playerNumber = playerNum;
        players = new String[playerNum];
    }

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

    abstract public boolean validateVictory();

    public void setCurrentPlayer(String aCurrentPlayer){
        currentPlayer=aCurrentPlayer;
    }

    public void addPlayer(String player) {
        if (addedPlayers>=playerNumber){
            System.err.println("Player is at full capacity");
        } else if (addedPlayers==playerNumber-1) {
            players[addedPlayers++]=player;
            gameState=GameState.INPROGRESS;
        } else{
            players[addedPlayers++]=player;
        }
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

}

enum GameState {
    WAITING,
    INPROGRESS,
    OVER
}