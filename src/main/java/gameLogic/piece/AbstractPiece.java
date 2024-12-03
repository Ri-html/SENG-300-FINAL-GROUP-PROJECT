package gameLogic.piece;

import gameLogic.side.Side;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece that = (AbstractPiece) o;
        return display == that.display && Objects.equals(side, that.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, display);
    }
}
