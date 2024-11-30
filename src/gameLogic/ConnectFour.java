package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGame;
import gameLogic.piece.Piece;

public class ConnectFour extends AbstractBoardGame {
    boolean isValid;
    String board[][]= new ConnectFourPiece[6][7];
    public ConnectFour(int player1, int player2,int playerNum) {
        super(playerNum);
    }

    /**
     * Takes a player id and column and places a piece in that column
     * @param int player id
     * @param int x for column to place in
     * @return bool success
     */
    private boolean placePiece(int player, int x) {
        isValid = isValidMove(x);
        if (isValid) {
            for (int i = board[0].length - 1; i >= 0; i--) {
                if (board[i][x] == null) {
                    if (player1 == player) {
                        board[i][x] = new ConnectFourPiece(ConnectFourSide.RED);
                    } else {
                        board[i][x] = new ConnectFourPiece(ConnectFourSide.YELLOW);
                    }
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if win has been reached
     * @return bool true on win or false on no win
     */
    public boolean checkWin() {
        if (checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin()) {
            return true;
        }
        return false;
    }

    /**
     * Checks board for win horizontally
     * @return true on win horizontal or false on no win
     */
    private boolean checkHorizontalWin() {
        //!TODO
    }

    /**
     * Checks board for win vertically
     * @return true on win vertical or false on no win
     */
    private boolean checkVerticalWin() {
        //!TODO
    }

    /**
     * Checks board for win diagonally
     * @return true on win diagonal or false on no win
     */
    private boolean checkDiagonalWin() {
        //!TODO
    }

    /**
     * Checks if board is full (no possible moves left)
     * @return true if board is full
     */
    private boolean isFull() {
        boolean full = true;
        for ( i=0; i < board[0].length; i++) {
            if (board[board.length - 1][i] == null) {
                full = false;
            }
        }
        return full;
    }

    /**
     * Returns board state
     */
    public ConnectFourPiece[][] getBoard() {
        return board;
    }

    /**
     * Checks if valid column to make move
     * @param x int of column to place
     */
    private boolean isValidMove(int x) {
        return board[0][x] == null;
    }

    /**
     * checks valid move
     * @param x checks column to place in
     * @param y ignored, artifact of abstract
     */
    public void checkMoves(int x, int y) {
        return isValidMove(x);
    }

    /**
     * Creates a new board for game
     * @return 2d array of ConnectFourPieces of the current board state
     */
    @Override
    protected Piece[][] setUpBoard() {
        this.board = new ConnectFourPiece[6][7];
        return getBoard();
    }

    /**
     * check for a game ending condition
     * @return GameEndState enum
     */
    @Override
    public GameEndState validateGameEnds() {
        if (checkWin()) {
            return GameEndState.VICTORY;
        } else if (isFull()){
            return GameEndState.DRAW;
        } else {
            return GameEndState.ONGOING;
        }
    }

    /**
     * Check if move requested is legal
     * @param moves coords of move to place
     * @return true if legal move
     */
    @Override
    public boolean validateMove(int[] moves) {
        isValidMove(moves[0]);
    }

    /**
     * Place move requested (does nothing if invalid move)
     * @param player1 id of player making move
     * @param moves coords of move
     */
    @Override
    public void makeMove(int player1, int[] moves) {
        if (validateMove(int[] moves)){
            placePiece(player1, moves[0]);
        }
    }

}
