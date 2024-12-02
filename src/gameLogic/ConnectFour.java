package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.piece.ConnectFourPiece;
import gameLogic.side.ConnectFourSide;
import gameLogic.piece.Piece;

public class ConnectFour extends AbstractBoardGame {
    boolean isValid;
    private static final int WINLENGTH = 4;
    ConnectFourPiece[][] board = new ConnectFourPiece[6][7];
    public ConnectFour(int playerNum) {
        super(playerNum);
    }

    /**
     * Takes a player id and column and places a piece in that column
     *
     * @param player int player id
     * @param x      int for column to place in
     */
    private void placePiece(int player, int x) {
        isValid = isValidMove(x);
        if (isValid) {
            for (int i = board[0].length - 1; i >= 0; i--) {
                if (board[i][x] == null) {
                    if (currentPlayer == 0) {
                        board[i][x] = new ConnectFourPiece(ConnectFourSide.RED);
                    } else {
                        board[i][x] = new ConnectFourPiece(ConnectFourSide.YELLOW);
                    }
                    return;
                }
            }
        }
    }

    /**
     * Checks if win has been reached
     * @return bool true on win or false on no win
     */
    public boolean checkWin() {
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    /**
     * Checks board for win horizontally
     * @return true on win horizontal or false on no win
     */
    private boolean checkHorizontalWin() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j].getSide()) {
                    case ConnectFourSide.RED:
                        if (horizontalWinHelper(ConnectFourSide.RED, i, j)) {
                            return true;
                        }
                        break;
                    case ConnectFourSide.YELLOW:
                        if (horizontalWinHelper(ConnectFourSide.YELLOW, i, j)) {
                            return true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    private boolean horizontalWinHelper(ConnectFourSide side, int x, int y) {
        // check left
        int length = 1;
        while (length < WINLENGTH) {
            // overflow guard
            if (x == 0) {
                break;
            }
            x--;
            // if piece to left is wrong colour stop checking for win
            if (board[x][y].getSide() != side) {
                break;
            }
            // piece to left is right colour, keep looking
            length++;
        }
        // if length of sequence is long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        length = 1;
        // check right
        while (length < WINLENGTH) {
            // overflow guard
            if (x == board[0].length - 1) {
                break;
            }
            x++;
            // if piece to right is wrong colour stop checking for win
            if (board[x][y].getSide() != side) {
                break;
            }
            // piece to right is correct colour, keep looking
            length++;
        }
        // if length of sequence was long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        return false;
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
        for ( int i = 0; i < board[0].length; i++) {
            if (board[board.length - 1][i] == null) {
                full = false;
                break;
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
     * @return true if valid move
     */
    public boolean checkMoves(int x, int y) {
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
            return GameEndState.Victory;
        } else if (isFull()){
            return GameEndState.Draw;
        } else {
            return GameEndState.Ongoing;
        }
    }

    /**
     * Check if move requested is legal
     * @param moves coords of move to place
     * @return true if legal move
     */
    @Override
    public boolean validateMove(int[] moves) {
        return isValidMove(moves[0]);
    }

    /**
     * Place move requested (does nothing if invalid move)
     * @param moves coords of move
     */
    @Override
    public void makeMove(int[] moves) {
        if (validateMove(moves)){
            placePiece(currentPlayer, moves[0]);
        }
    }

    /**
     * Returns the current board state as a string
     * @return String of current board state
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ConnectFourPiece[] connectFourPieces : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (connectFourPieces[j] == null) {
                    stringBuilder.append(" ");
                } else if (connectFourPieces[j].getSide() == ConnectFourSide.RED) {
                    stringBuilder.append('R');
                } else if (connectFourPieces[j].getSide() == ConnectFourSide.YELLOW) {
                    stringBuilder.append('Y');
                } else {
                    stringBuilder.append("E");
                }
                stringBuilder.append(", ");
            }
            stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length()-1);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * returns the current board state as a character array
     * @return char[][] of board state
     */
    public char[][] toCharArray() {
        char[][] charArray = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j].getSide()) {
                    case ConnectFourSide.RED:
                        charArray[i][j] = 'R';
                        break;
                    case ConnectFourSide.YELLOW:
                        charArray[i][j] = 'Y';
                        break;
                    case null:
                        charArray[i][j] = ' ';
                    default:
                        charArray[i][j] = 'E';
                }
            }
        }
        return charArray;
    }
}
