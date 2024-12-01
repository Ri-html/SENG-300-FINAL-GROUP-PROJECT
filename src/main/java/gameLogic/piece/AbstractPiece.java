package gameLogic.piece;

import gameLogic.side.Side;

public abstract class AbstractPiece implements Piece {
    private final Side side;
    protected char display;
    public AbstractPiece(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    /**
     * print out string representation of the class
     * @return side+" "+display in string format
     */
    @Override
    public String toString() {
        return side.toString()+" "+display;
    }
}
