package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;

import java.util.ArrayList;
import java.util.UUID;

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
    protected Piece[][] gameBoard;
    int addedPlayers=0;
    int playerNumber;
    int[]moves;
    private GameState gameState;

    public AbstractBoardGame(int playerNum) {
        gameState = GameState.WAITING;
        playerNumber = playerNum;
        players = new String[playerNum];
        //get a unique game id
        gameID= UUID.randomUUID().toString();
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
            System.out.println("Game has started");
        }
    }

    /**
     * sets up the board and notify both frontends of the board state
     * Also notify player one to make their move
     */
    private void startGame(){
        currentPlayer=0;
        gameBoard=setUpBoard();
        notifyBoardSetup();
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
            checkForEndGame();
        }else{
            gameState=GameState.OVER;
            notifyGameEnd();
        }
    }

    /**
     * checks whether the game end condition is met
     */
    protected void checkForEndGame(){
        if(validateGameEnds()!=null && validateGameEnds()!=GameEndState.Ongoing){
            endGame();
        }else{
            switchCurrentPlayer();
            notifyTurnEnd();
        }
    }

    /**
     * notify the winner to the leaderboard and both frontends
     */
    private void endGame(){
        if(validateGameEnds()==GameEndState.Draw){
            gameState=GameState.OVER;
            notifyGameEnd();
        } else if (validateGameEnds()==GameEndState.Victory) {
            gameState=GameState.OVER;
            winner = players[currentPlayer];
            notifyGameEnd();
        }
    }

    /**
     * receives the move and calls the gameTurn method
     * @param moves an array containing 2,4 integers representing [x,y] or [startingX,startingY,EndingX,EndingY]
     */
    public void update(int[] moves){
        gameTurn(moves);
    };

    /**
     * switch the player's turn
     */
    public void switchCurrentPlayer() {
        if (currentPlayer<playerNumber-1){
            currentPlayer++;
        }else{
            currentPlayer=0;
        }
    }

    /**
     * set the piece if
     * @param piece the piece to place
     * @param x row number
     * @param y the column number
     */
    @Override
    public void placeBoardPiece(Piece piece, int x, int y) {
        if(gameBoard[x][y]==null){
            gameBoard[x][y] = piece;
        }else{
            throw new IllegalArgumentException("Piece already occupied");
        }
    }

    public void setCurrentPlayer(int aCurrentPlayer){
        currentPlayer=aCurrentPlayer;
    }

    public String getCurrentPlayer() {
        return players[currentPlayer];
    }

    public GameState getGameState() {
        return gameState;
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
