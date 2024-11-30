package gameLogic.piece.chessPiece;
import gameLogic.side.ChessSide;

public class Knight extends AbstractChessPiece{
    private ChessSide side;
    public Knight(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.knight.getSymbol();
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }

}
