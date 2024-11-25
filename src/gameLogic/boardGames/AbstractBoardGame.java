package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;
import gameLogic.player.AbstractPlayer;

import java.util.ArrayList;

/**
 * AbstractBoardGame handles the common functionality between the
 * board games and sets up a framework of adding player, starting game, switching turns, notifying changes, and ending game
 */
public abstract class AbstractBoardGame implements BoardGame {
    public enum GameState {
        WAITING,
        INPROGRESS,
        OVER
    }
    public ArrayList<BoardGameObserver> boardSetupObservers;
    public ArrayList<BoardGameObserver> turnEndObservers;
    public ArrayList<BoardGameObserver> gameEndObservers;
    private String winner = null;
    private String gameID;
    private String currentPlayer;
    private String[] players;
    protected Piece[][] gameBoard;
    int addedPlayers=0;
    int playerNumber;
    int[]moves;
    private GameState gameState;

    public AbstractBoardGame(int playerNum) {
        gameState = GameState.WAITING;
        playerNumber = playerNum;
        players = new String[playerNum];
    }

    //methods that should be implemented in the subclass
    abstract protected void setUpBoard(Piece[][] board);
    abstract public boolean validateVictory();
    abstract public boolean validateMove(int[] moves);
    abstract public void makeMove(int[] moves);

    public void addPlayer(String player) {
        if (addedPlayers>=playerNumber){
            System.err.println("Player is at full capacity");
        } else{
            players[addedPlayers++]=player;
        }
        checkStartCondition();
    }
    protected void checkStartCondition(){
        if (addedPlayers>=playerNumber && gameState==GameState.WAITING) {
            gameState = GameState.INPROGRESS;
            startGame();
        }
    }
    private void startGame(){
        setUpBoard(gameBoard);
    }
    private void gameTurn(int[] moves){
        if (validateMove(moves)){
            makeMove(moves);
        }else{
            gameState = GameState.OVER;
        }

    }
    private void endGame(){
        if(validateVictory()){
            notify();
        }
    }
    public void switchCurrentPlayer(){

    }

    public void setWinner(){

    }

    @Override
    public void placeBoardPiece(AbstractPiece piece, int x, int y) {
    }

    public Piece getPiece(int x, int y){};
    public void setCurrentPlayer(String aCurrentPlayer){
        currentPlayer=aCurrentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    //methods related to observers
    public void attachBoardSetupObserver(BoardGameObserver observer){
        boardSetupObservers.add(observer);
    }
    public void detachBoardSetupObserver(BoardGameObserver observer){
        boardSetupObservers.remove(observer);
    }
    public void attachTurnEndObserver(BoardGameObserver observer){turnEndObservers.add(observer);}
    public void detachTurnEndObserver(BoardGameObserver observer){turnEndObservers.remove(observer);}
    public void attachGameEndObserver(BoardGameObserver observer){gameEndObservers.add(observer);}
    public void detachGameEndObserver(BoardGameObserver observer){gameEndObservers.remove(observer);}
    public void notifyBoardSetup(){
            for(BoardGameObserver observer : boardSetupObservers){
                observer.update(gameBoard);
            }
    }
    public void notifyTurnEnd(){
        for(BoardGameObserver observer : turnEndObservers){
            observer.update(moves);
        }
    }
    public void notifyGameEnd(){
        for(BoardGameObserver observer : gameEndObservers){
            observer.update(winner);
        }
    }
}
