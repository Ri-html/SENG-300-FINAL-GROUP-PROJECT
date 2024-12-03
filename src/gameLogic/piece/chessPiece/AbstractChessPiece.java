package gameLogic.piece.chessPiece;

import gameLogic.Chess;
import gameLogic.piece.AbstractPiece;
import gameLogic.piece.Piece;
import gameLogic.piece.PieceType;
import gameLogic.side.ChessSide;
import gameLogic.side.Side;

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
     * @param newX X coordinate to move piece to
     * @param newY Y coordinate to move piece to
     * @return the new location of the piece [newX,newY], or [-1,-1] for move invalid/not made
     */
    public int[] makeMove(int newX, int newY){
        //get valid moves
        for (int i = 0; i < validMoves.length; i++) {
            //if move is in valid moves
            //make move
            //IMPLEMENT CAPTURE METHOD HERE IF WE CAPTURE WITH THIS MOVE
            return new int[]{newX, newY};
        }
        //return -1 for false
        return new int[]{-1, -1};
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    abstract int[][] getValidMoves(Piece[][] board);

    public void updateValidMoves(){
    }

    /**
     * Handles removing a piece from the board on capture
     * @param takes the piece that takes another piece
     * @param captured the piece that is captured and removed from the board
     */
    public void capture(AbstractChessPiece takes, AbstractChessPiece captured){
        //remove piece from board
        //store piece somewhere in 'captured' collection to be displayed
        Chess.capturedPieces.add(captured);
    }

    protected boolean isInBounds(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    protected boolean isValidSquare(Piece[][] board, int x, int y) {
        return board[x][y] == null || board[x][y].getSide() != this.getSide();
    }
}
