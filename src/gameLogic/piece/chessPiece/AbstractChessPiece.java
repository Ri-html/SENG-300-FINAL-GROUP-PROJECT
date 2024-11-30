package gameLogic.piece.chessPiece;

import gameLogic.piece.AbstractPiece;
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

    public void setLocation(int[] location) {
        this.location = location;
    }

    abstract int[][] getValidMoves();

    public void updateValidMoves(){
    }

    /**
     * Handles removing a piece from the board on capture
     * @param takes the piece that takes another piece
     * @param captured the piece that is captured and removed from the board
     */
    public void capture(AbstractChessPiece takes, AbstractChessPiece captured){

    }
}
