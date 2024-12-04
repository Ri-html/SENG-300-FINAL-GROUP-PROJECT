package gameLogic.boardGames;

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
    public ArrayList<BoardGameObserver> boardSetupObservers=new ArrayList<>();
    public ArrayList<BoardGameObserver> turnEndObservers=new ArrayList<>();
    public ArrayList<BoardGameObserver> gameEndObservers=new ArrayList<>();
    protected String winner = null;
    protected String gameID;
    protected int currentPlayer;
    protected final String[] players;
    protected Piece[][] gameBoard;
    int addedPlayers=0;
    int playerNumber;
    int[]moves;
    private GameState gameState;

    public AbstractBoardGame(int playerNum, int x, int y) {
        gameState = GameState.WAITING;
        playerNumber = playerNum;
        players = new String[playerNum];
        //get a unique game id
        gameID= UUID.randomUUID().toString();
        gameBoard = new Piece[x][y];
    }

    //methods that should be implemented in the subclass
        //sets up the Board for the specific board game
    abstract protected Piece[][] setUpBoard();
        //validates if an ending condition is met
    abstract public int validateGameEnds();
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
     * @param move an array containing 2,4 integers representing [x,y] or [startingX,startingY,EndingX,EndingY]
     */
    private void gameTurn(String move){
        //parsing the move from string to integer
        String[] aMove = move.split(",");
        int[] moves=new int[aMove.length];
        for (int i=0;i<aMove.length;i++){
            moves[i]=Integer.parseInt(aMove[i]);
        }

        //uses the integer moves
        if (validateMove(moves)){
            makeMove(moves);
            checkForEndGame(move);
        }else{
            gameState=GameState.OVER;
            notifyGameEnd();
        }
    }

    /**
     * checks whether the game end condition is met
     */
    protected void checkForEndGame(String moves){
        if(validateGameEnds()!=-1){
            endGame();
        }else{
            switchCurrentPlayer();
            notifyTurnEnd(moves);
        }
    }

    /**
     * notify the winner to the leaderboard and both frontends
     */
    private void endGame(){
        if(validateGameEnds()==0){
            //draw
            gameState=GameState.OVER;
            notifyGameEnd();
        } else if (validateGameEnds()>0) {
            //victory
            gameState=GameState.OVER;
            winner = players[currentPlayer];
            notifyGameEnd();
        }
    }

    /**
     * receives the move and calls the gameTurn method
     * @param moves an array containing 2,4 integers representing [x,y] or [startingX,startingY,EndingX,EndingY]
     */
    public void updateMove(String moves){
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
                StringBuilder stringToSend=new StringBuilder();
                stringToSend.append("Setup"+"\n");
                stringToSend.append(gameID+"\n");
                stringToSend.append(this.toString()+"\n");
                observer.update(stringToSend.toString());
            }
    }
    public void notifyTurnEnd(String moves){
        for(BoardGameObserver observer : turnEndObservers){
            StringBuilder stringToSend=new StringBuilder();
            stringToSend.append("TurnEnd"+"\n");
            stringToSend.append(gameID).append("\n");
            stringToSend.append(moves).append("\n");
            observer.update(stringToSend.toString());
        }
    }
    public void notifyGameEnd(){
        for(BoardGameObserver observer : gameEndObservers){
            StringBuilder stringToSend=new StringBuilder();
            stringToSend.append("GameEnd"+"\n");
            stringToSend.append(gameID).append("\n");
            if(winner!=null){
                stringToSend.append("winner,").append(winner).append(",loser");
                for (String player: players) {
                    if (!player.equals(winner)) {
                        stringToSend.append(",").append(player);
                    }
                }
            }else{
                stringToSend.append("winner:none - loser: none");
            }
            stringToSend.append("\n");
            observer.update(stringToSend.toString());
        }
    }

    /**
     * print the string representation of the game board
     * @return string representation of the game board
     */
    @Override
    public String toString(){
        if(gameBoard==null) {
            return "null";
        } else{
            StringBuilder str = new StringBuilder();
            for (Piece[] pieces : gameBoard) {
                for (int j = 0; j < gameBoard.length - 1; j++) {
                    if (pieces[j] == null) {
                        str.append(" " + ",");
                    } else {
                        str.append(pieces[j].toString()).append(",");
                    }
                }
                if (pieces[gameBoard.length-1] == null) {
                    str.append(" ");
                } else {
                    str.append(pieces[gameBoard.length-1].toString());
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
