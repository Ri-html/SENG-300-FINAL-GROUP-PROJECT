package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.piece.ConnectFourPiece;
import gameLogic.side.ConnectFourSide;
import gameLogic.piece.Piece;

public class ConnectFour extends AbstractBoardGame {
    private static final int WINLENGTH = 4;
    public ConnectFour(int playerNum) {
        super(playerNum, 6, 7);
        this.gameBoard = new ConnectFourPiece[6][7];
    }

    /**
     * Takes a player id and column and places a piece in that column
     *
     * @param x      int for column to place in
     */
    private void placePiece(int x) {
        for (int i = gameBoard.length - 1; i >= 0; i--) {
            if (gameBoard[i][x] == null) {
                if (currentPlayer == 0) {
                    gameBoard[i][x] = new ConnectFourPiece(ConnectFourSide.RED);
                } else {
                    gameBoard[i][x] = new ConnectFourPiece(ConnectFourSide.YELLOW);
                }
                return;
            }
        }
    }

    /**
     * Checks if win has been reached
     * @return 0 if no win, 1 if RED win, 2 if YELLOW win
     */
    public int checkWin() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                // guard
                if (gameBoard[i][j] == null) {
                    continue;
                }
                switch (gameBoard[i][j].getSide()) {
                    case ConnectFourSide.RED:
                        if (horizontalWinHelper(ConnectFourSide.RED, j, i)) {
                            return 1;
                        }
                        if (verticalWinHelper(ConnectFourSide.RED, j, i)) {
                            return 1;
                        }
                        if (diagonalWinHelper(ConnectFourSide.RED, j, i)) {
                            return 1;
                        }
                        break;
                    case ConnectFourSide.YELLOW:
                        if (horizontalWinHelper(ConnectFourSide.YELLOW, j, i)) {
                            return 2;
                        }
                        if (verticalWinHelper(ConnectFourSide.YELLOW, j, i)) {
                            return 2;
                        }
                        if (diagonalWinHelper(ConnectFourSide.YELLOW, j, i)) {
                            return 2;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return 0;
    }

    private boolean horizontalWinHelper(ConnectFourSide side, int x, int y) {
        // check left
        int length = 1;
        int i = x;
        while (length < WINLENGTH) {
            i--;
            // overflow guard
            if (i < 0) {
                break;
            }
            // if no piece, break
            if (gameBoard[y][i] == null) {
                break;
            }
            // if piece to left is wrong colour stop checking for win
            if (gameBoard[y][i].getSide() != side) {
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
        i = x;
        while (length < WINLENGTH) {
            i++;
            // overflow guard
            if (i >= gameBoard[0].length) {
                break;
            }
            // if no piece, break
            if (gameBoard[y][i] == null) {
                break;
            }
            // if piece to right is wrong colour stop checking for win
            if (gameBoard[y][i].getSide() != side) {
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

    private boolean verticalWinHelper(ConnectFourSide side, int x, int y) {
        // check up
        int length = 1;
        int j = y;
        while (length < WINLENGTH) {
            j--;
            // overflow guard
            if (j < 0) {
                break;
            }
            // if no piece, break
            if (gameBoard[j][x] == null) {
                break;
            }
            // if piece up is wrong colour stop checking for win
            if (gameBoard[j][x].getSide() != side) {
                break;
            }
            // piece up is right colour, keep looking
            length++;
        }
        // if length of sequence is long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        length = 1;
        // check down
        j = y;
        while (length < WINLENGTH) {
            j++;
            // overflow guard
            if (j >= gameBoard.length) {
                break;
            }
            // if no piece, break
            if (gameBoard[j][x] == null) {
                break;
            }
            // if piece down is wrong colour stop checking for win
            if (gameBoard[j][x].getSide() != side) {
                break;
            }
            // piece down is correct colour, keep looking
            length++;
        }
        // if length of sequence was long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        return false;
    }

    /**
     * check for diagonal win given a location and piece
     * @param side the side to check the win for
     * @param x the x location of the piece
     * @param y the y location of the piece
     * @return true if win
     */
    private boolean diagonalWinHelper(ConnectFourSide side, int x, int y) {
        // check up left
        int length = 1;
        int i = x, j = y;
        while (length < WINLENGTH) {
            // overflow guard
            i--;
            j--;
            if (i < 0 || j < 0) {
                break;
            }
            // if no piece, break
            if (gameBoard[j][i] == null) {
                break;
            }
            // if piece to left is wrong colour stop checking for win
            if (gameBoard[j][i].getSide() != side) {
                break;
            }
            // piece to up left is right colour, keep looking
            length++;
        }
        // if length of sequence is long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        // check up right
        length = 1;
        i = x;
        j = y;
        while (length < WINLENGTH) {
            // overflow guard
            i++;
            j--;
            if (i >= gameBoard[0].length || j < 0) {
                break;
            }
            // if no piece, break
            if (gameBoard[j][i] == null) {
                break;
            }
            // if piece to left is wrong colour stop checking for win
            if (gameBoard[j][i].getSide() != side) {
                break;
            }
            // piece to down left is right colour, keep looking
            length++;
        }
        // if length of sequence is long enough return that a win was found.
        if (length >= WINLENGTH) {
            return true;
        }
        return false;
    }

    /**
     * Checks if gameBoard is full (no possible moves left)
     * @return true if gameBoard is full
     */
    private boolean isFull() {
        boolean full = true;
        for ( int i = 0; i < gameBoard[0].length; i++) {
            if (gameBoard[gameBoard.length - 1][i] == null) {
                full = false;
                break;
            }
        }
        return full;
    }

    /**
     * Returns gameBoard state
     */
    public Piece[][] getBoard() {
        return gameBoard;
    }

    /**
     * Checks if valid column to make move
     * @param x int of column to place
     */
    private boolean isValidMove(int x) {
        if (x < 0 || x >= gameBoard[0].length) {
            return false;
        }
        return gameBoard[0][x] == null;
    }


    /**
     * Creates a new gameBoard for game
     * @return 2d array of ConnectFourPieces of the current gameBoard state
     */
    @Override
    protected Piece[][] setUpBoard() {
        this.gameBoard = new ConnectFourPiece[6][7];
        return getBoard();
    }

    /**
     * check for a game ending condition
     * @return int 1 if RED won, 2 if YELLOW won
     */
    @Override
    public int validateGameEnds() {
        int x = checkWin();
        if (x>0) {
            //then we have a winner
            return x;
        } else if (isFull()){
            //draw
            return 0;
        } else {
            //game ongoing
            return -1;
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
        placePiece(moves[0]);
    }

    /**
     * Returns the current gameBoard state as a string
     * @return String of current gameBoard state
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece[] connectFourPieces : gameBoard) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (connectFourPieces[j] == null) {
                    stringBuilder.append("X");
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
     * returns the current gameBoard state as a character array
     * @return char[][] of gameBoard state
     */
    public char[][] toCharArray() {
        char[][] charArray = new char[gameBoard.length][gameBoard[0].length];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                switch (gameBoard[i][j].getSide()) {
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
