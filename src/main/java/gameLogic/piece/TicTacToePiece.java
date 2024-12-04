package gameLogic.piece;

import gameLogic.side.Side;

public class TicTacToePiece extends AbstractPiece{
    public TicTacToePiece(Side side) {
        super(side);
    }

    @Override
    public String toString() {
        return side.toString();
    }
}