package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;
import gameLogic.piece.PieceType;
import gameLogic.side.ChessSide;
import gameLogic.side.Side;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractChessPiece extends AbstractPiece {
    int[][] validMoves;
    private int[] location; //in [x, y]
    protected PieceType type = PieceType.ChessPieceType;
    private AbstractChessPiece capturedPiece;

    public AbstractChessPiece(ChessSide side) {
        super(side);
    }

    public void checkChessMove(){
    };
    public int[] getLocation() {
        return location;
    }


    /**
     * Handles moving a piece on the board
     * @param board the game board
     * @param newX X coordinate to move piece to
     * @param newY Y coordinate to move piece to
     * @return the new location of the piece [newX,newY], or [-1,-1] for move invalid/not made
     */
    public int[] makeMove(Piece[][] board, int newX, int newY){
        //get valid moves
        this.validMoves = getValidMoves(board);
        for (int i = 0; i < validMoves.length; i++) {
            //if move in validMoves
            int[] newLoc = new int[]{newX, newY};
            if (Arrays.equals(validMoves[i], newLoc)) {
                //make move
                int oldX = location[0];
                int oldY = location[1];
                //if there is a piece at the  new board location
                if(board[newX][newY]!= null){
                    //capture the piece
                    Chess.capturedPieces.add(board[newX][newY]);
                    capturedPiece = (AbstractChessPiece) board[newX][newY];
                }
                setLocation(validMoves[i]);
                //clear old board location
                board[oldX][oldY] = null;
                board[newX][newY] = this;
                return newLoc;
            }
        }
        //return -1 for move not made
        return new int[]{-1, -1};
    }

    /**
     * Undo a move that has been made by makeMove. Used in validateGameEnds()
     * @param board
     * @param oldX
     * @param oldY
     */
    public void undoMove(Piece[][] board, int oldX, int oldY){
        int x = this.getLocation()[0];
        int y = this.getLocation()[1];
        // Restore the board
        board[oldX][oldY] = this;
        board[x][y] = capturedPiece;
        this.setLocation(new int[]{oldX, oldY});
        capturedPiece = null; // Clear the captured piece
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public abstract int[][] getValidMoves(Piece[][] board);


    public void updateValidMoves(){
    }


    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    protected boolean isValidSquare(Piece[][] board, int x, int y) {
        return board[x][y] == null || board[x][y].getSide() != this.getSide();
    }


    public boolean isInCheck(Piece[][] board)
    {

        ChessSide side = (ChessSide) getSide();
        int[] kingPosition = findKing(board, side);
        int[][] straightDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Check up and down
        for (int[] direction : straightDirections)
        {
            int x = kingPosition[0];
            int y = kingPosition[1];

            while (isInBounds(x += direction[0], y += direction[1]))
            {
                if (board[x][y] != null)
                {
                    if ((board[x][y] instanceof Rook || board[x][y] instanceof Queen) && board[x][y].getSide() != side)
                    {
                        return true;
                    }
                    else
                    {
                        break;
                    }
                }

            }
        }

        int[][] diagonalDirections = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // Check diagonal
        for (int[] direction : diagonalDirections)
        {
            int x = kingPosition[0];
            int y = kingPosition[1];
            while (isInBounds(x += direction[0], y += direction[1]))
            {
                if (board[x][y] != null)
                {
                    if ((board[x][y] instanceof Bishop || board[x][y] instanceof Queen) && board[x][y].getSide() != side)
                    {
                        return true;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }

        int[][] knightMoves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        // Check L's
        for (int[] move : knightMoves)
        {
            int x = kingPosition[0] + move[0];
            int y = kingPosition[1] + move[1];
            if (isInBounds(x, y) && board[x][y] instanceof Knight && board[x][y].getSide() != side)
            {
                return true;
            }
        }

        int pawnDirection = (side == ChessSide.WHITE) ? 1 : -1;
        int[][] pawnMoves = {{pawnDirection, -1}, {pawnDirection, 1}};

        // Check pawn
        for (int[] move : pawnMoves)
        {
            int x = kingPosition[0] + move[0];
            int y = kingPosition[1] + move[1];
            if (isInBounds(x, y) && board[x][y] instanceof Pawn && board[x][y].getSide() != side)
            {
                return true;
            }
        }

        // All G
        return false;
    }
//    /**
//     * Checks if the current player's king is in check after a move.
//     * @param board The current game board.
//     * @param newX The x-coordinate of the potential move.
//     * @param newY The y-coordinate of the potential move.
//     * @return True if the king is in check after the move, false otherwise.
//     */
//    public boolean isInCheck(Piece[][] board, int newX, int newY)
//    {
//        // Create a copy of the board using the addToBoard method
//        Piece[][] tempBoard = new Piece[board.length][];
//        Chess chess = new Chess();
//
//        for (int i = 0; i < board.length; i++)
//        {
//            tempBoard[i] = new Piece[board[i].length];
//            for (int j = 0; j < board[i].length; j++)
//            {
//                if (board[i][j] != null)
//                {
//                    chess.addToBoard(tempBoard, (AbstractChessPiece) board[i][j], i, j);
//                }
//            }
//        }
//
//        // Simulate the move on the temporary board
//        int currentX = getLocation()[0];
//        int currentY = getLocation()[1];
//
//        // Update tempboard with potential move
//        tempBoard[newX][newY] = tempBoard[currentX][currentY];
//
//        // Clear the old
//        tempBoard[currentX][currentY] = null;
//
//        // Find the king's position
//        ChessSide side = (ChessSide) getSide();
//        int[] kingPosition = findKing(tempBoard, side);
//
//        // Check Opp color
//        ChessSide opponent = side == ChessSide.WHITE ? ChessSide.BLACK : ChessSide.WHITE;
//
//        // Check if the king is attacked
//        return isSquareAttacked(tempBoard, kingPosition[0], kingPosition[1], opponent);
//    }

    /**
     * Searches for the king piece belonging to the specified side on the chess board.
     *
     * @param board The current state of the chess board represented as a 2D array of Piece objects.
     * @param side  The side (either white or black) for which the king's position is to be found.
     * @return An array containing the x-coordinate (row index) and y-coordinate (column index) of the king's position.
     *         Returns null if the king is not found on the board.
     */
    public static int[] findKing(Piece[][] board, ChessSide side)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] instanceof King && board[i][j].getSide() == side)
                {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

//    /**
//     * Determines whether a square on the chess board is under attack by the opponent's pieces.
//     *
//     * @param board    The current state of the chess board.
//     * @param Kingx    The x-coordinate (row index) of the king's location on the board.
//     * @param Kingy    The y-coordinate (column index) of the king's location on the board.
//     * @param opponent The side (either white or black) of the opponent whose pieces are checking the king.
//     * @return true if the square is under attack by any of the opponent's pieces; otherwise, false.
//     */
//    private boolean isSquareAttacked(Piece[][] board, int Kingx, int Kingy, ChessSide opponent)
//    {
//        for (Piece[] row : board)
//        {
//            for (Piece piece : row)
//            {
//                if (piece != null && piece.getSide() == opponent)
//                {
//                    if (piece instanceof AbstractChessPiece)
//                    {
//                        for (int[] potentialMove : ((AbstractChessPiece) piece).getValidMoves(board))
//                        {
//                            if (potentialMove[0] == Kingx && potentialMove[1] == Kingy)
//                            {
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }


}
