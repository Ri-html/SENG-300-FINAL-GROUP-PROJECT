package gameLogic.piece.chessPiece;

import gameLogic.side.ChessSide;

public class King extends AbstractChessPiece{
    private ChessSide side;
    public King(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.king.getSymbol();
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }
}
