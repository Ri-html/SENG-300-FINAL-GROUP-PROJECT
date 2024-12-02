package gameLogic.piece.chessPiece;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.PieceType;
import gameLogic.side.Side;

public abstract class AbstractChessPiece extends AbstractPiece {
    int[][] validMoves;
    private int[] location; //in [x, y]
    protected PieceType type = PieceType.ChessPieceType;

    public AbstractChessPiece(Side side) {
        super(side);
    }

    public void checkChessMove(){ //cannot have an abstract method in a non-abstract class
    };
    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int[][] getValidMoves(){ //why is this void in class diagram?
        // maybe return array of tuples with all possible moves x,y
        return validMoves;
    }

    public void updateValidMoves(){
    }
}
