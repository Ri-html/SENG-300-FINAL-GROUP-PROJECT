package gameLogic.piece;

import gameLogic.side.Side;

public abstract class AbstractPiece implements Piece {
    private final Side side;
    private String display;
    public AbstractPiece(Side side, String display) {
        this.side = side;
        this.display = display;
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
