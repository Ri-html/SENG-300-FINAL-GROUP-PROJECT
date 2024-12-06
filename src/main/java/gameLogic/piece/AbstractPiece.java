package gameLogic.piece;

import gameLogic.side.Side;

import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    protected final Side side;
    protected char display;
    public AbstractPiece(Side side) {
        this.side = side;
    }

    /**
     * gets which player's side the piece belongs to
     * @return the side field
     */
    public Side getSide() {
        return side;
    }

    /**
     * print out string representation of the class
     * @return side+" "+display in string format
     */
    @Override
    public String toString() {
        return String.valueOf(side+" "+display);
    }

    /**
     * To compare whether two piece belongs to the same player
     * @param o another abstractPiece object
     * @return whether they are on the same side
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece that = (AbstractPiece) o;
        return display == that.display && Objects.equals(side, that.side);
    }

    /**
     * creates a hashcode of the object
     * @return an integer hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(side, display);
    }
}
