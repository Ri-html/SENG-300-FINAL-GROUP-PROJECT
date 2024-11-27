package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;

import java.util.ArrayList;

/**
 * AbstractBoardGame handles the common functionality between the
 * board games and sets up a framework of adding player, starting game, switching turns, notifying changes, and ending game
 */
public abstract class AbstractBoardGame implements BoardGame {
    public ArrayList<BoardGameObserver> boardSetupObservers;
    public ArrayList<BoardGameObserver> turnEndObservers;
    public ArrayList<BoardGameObserver> gameEndObservers;
    private String winner = null;
    private String gameID;
    private int currentPlayer;
    private final String[] players;
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

        //sets up the Board for the specific board game
    abstract protected void setUpBoard(Piece[][] board);
        //validates if an ending condition is met
    abstract public GameEndState validateGameEnds();
        //validates if the move is legal
    abstract public boolean validateMove(int[] moves);
        //changes the board according to how pieces move in the game and the moves received by the networking team
    abstract public void makeMove(int[] moves);

    /**
     * Adds the player to the game and check if the game started
     * @param player
     */
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
        currentPlayer=0;
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
        if(validateGameEnds()==GameEndState.Draw){
            notify();
        } else if (validateGameEnds()==GameEndState.Victory) {
            winner = players[currentPlayer];
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

    public enum GameEndState{
        Victory, Draw, Ongoing
    }
    public enum GameState {
        WAITING, INPROGRESS, OVER
    }
}
