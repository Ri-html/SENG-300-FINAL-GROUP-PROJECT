package gameLogic.boardGames;

import gameLogic.piece.Piece;

import java.util.UUID;

/**
 * Handles common functionality of board games.
 *
 */
public abstract class SimpleAbstractBoardGame {
    protected String winner = null;
    protected String gameID;
    protected int currentPlayer;
    protected final String[] players;
    protected Piece[][] gameBoard;
    int addedPlayers=0;
    int playerNumber;
    int[]moves;
    private AbstractBoardGame.GameState gameState;

    public SimpleAbstractBoardGame(int playerNum, int x, int y) {
        gameState = AbstractBoardGame.GameState.WAITING;
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

}
