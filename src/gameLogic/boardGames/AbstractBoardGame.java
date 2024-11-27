package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;

import java.util.ArrayList;

/**
 * AbstractBoardGame handles the common functionality between the
 * board games and sets up a framework of adding player, starting game, switching turns, notifying changes, and ending game
 * This class would encapsulate the entire process of running the game
 * The game instance would start as soon as the players are full which would notify the frontend
 * The class would send null or player as a string value when game instance ends
 */
public abstract class AbstractBoardGame implements BoardGame {
    public ArrayList<BoardGameObserver> boardSetupObservers;
    public ArrayList<BoardGameObserver> turnEndObservers;
    public ArrayList<BoardGameObserver> gameEndObservers;
    protected String winner = null;
    protected String gameID;
    protected int currentPlayer;
    protected final String[] players;
    private Piece[][] gameBoard;
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
    abstract protected Piece[][] setUpBoard();
        //validates if an ending condition is met
    abstract public GameEndState validateGameEnds();
        //validates if the move is legal
    abstract public boolean validateMove(int[] moves);
        //changes the board according to how pieces move in the game and the moves received by the networking team
    abstract public void makeMove(int[] moves);

    /**
     * Adds the player to the game and check if the game started
     * @param player A string id representing the user
     */
    public void addPlayer(String player) {
        if (addedPlayers>=playerNumber){
            System.err.println("Player is at full capacity");
        } else{
            players[addedPlayers++]=player;
        }
        checkStartCondition();
    }

    /**
     * Checks the starting condition and start the game if true
     */
    protected void checkStartCondition(){
        if (addedPlayers>=playerNumber && gameState==GameState.WAITING) {
            gameState = GameState.INPROGRESS;
            startGame();
            System.out.println("Player "+playerNumber+" has started");
        }
    }

    /**
     * sets up the board and notify both frontends of the board state
     * Also notify player one to make their move
     */
    private void startGame(){
        currentPlayer=0;
        gameBoard=setUpBoard();
    }

    /** receives the move
     * validate whether move is correct
     * make the move in the game instance
     * then check for end game condition
     * @param moves an array containing 2,4 integers representing [x,y] or [startingX,startingY,EndingX,EndingY]
     */
    private void gameTurn(int[] moves){
        if (validateMove(moves)){
            makeMove(moves);
        }
        checkForEndGame();
    }

    /**
     * checks whether the game end condition is met
     */
    protected void checkForEndGame(){
        if(validateGameEnds()!=null && validateGameEnds()!=GameEndState.Ongoing){
            endGame();
        }
    }

    /**
     * notify the winner to the leaderboard and both frontends
     */
    private void endGame(){
        if(validateGameEnds()==GameEndState.Draw){
            notify();
        } else if (validateGameEnds()==GameEndState.Victory) {
            winner = players[currentPlayer];
            notify();
        }
    }

    /**
     * receives the move and calls the gameTurn method
     * @param moves an array containing 2,4 integers representing [x,y] or [startingX,startingY,EndingX,EndingY]
     */
    public void update(int[] moves){
        gameTurn(moves);
    };

    public void switchCurrentPlayer(){

    }

    public void setWinner(){

    }

    @Override
    public void placeBoardPiece(AbstractPiece piece, int x, int y) {
    }

    /**
     * set the piece if
     * @param piece the piece to place
     * @param x row number
     * @param y the column number
     */
    protected void setPiece(Piece piece, int x, int y) {
        if(gameBoard[x][y]==null){
            gameBoard[x][y] = piece;
        }else{
            throw new IllegalArgumentException("Piece already occupied");
        }
    }

    public void setCurrentPlayer(int aCurrentPlayer){
        currentPlayer=aCurrentPlayer;
    }

    public int getCurrentPlayer() {
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

    @Override
    public String toString(){
        if(gameBoard==null) {
            return "null";
        } else{
            StringBuilder str = new StringBuilder();
            for (int i = 0; i<gameBoard.length; i++){
                for (int j=0; j<gameBoard.length;j++){
                    if (gameBoard[i][j]==null){
                        str.append(" ,");
                    }else {
                        str.append(gameBoard[i][j].toString());
                    }
                }
                str.append("\n");
            }
            return str.toString();
        }
    }
    public enum GameEndState{
        Victory, Draw, Ongoing
    }
    public enum GameState {
        WAITING, INPROGRESS, OVER
    }
}
