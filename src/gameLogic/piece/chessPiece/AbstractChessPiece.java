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
        getValidMoves(board);
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
                }
                setLocation(validMoves[i]);
                //clear old board location
                board[oldX][oldY] = null;
                //IMPLEMENT CAPTURE METHOD HERE IF WE CAPTURE WITH THIS MOVE
                return newLoc;
            }
        }
        //return -1 for move not made
        return new int[]{-1, -1};
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    abstract int[][] getValidMoves(Piece[][] board);

    public void updateValidMoves(){
    }


    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    protected boolean isValidSquare(Piece[][] board, int x, int y) {
        return board[x][y] == null || board[x][y].getSide() != this.getSide();
    }
}
