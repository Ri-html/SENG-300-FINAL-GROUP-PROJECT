package gameLogic.piece.chessPiece;
import gameLogic.side.ChessSide;

public class Pawn extends AbstractChessPiece {
    private ChessSide side;
    public Pawn(ChessSide side) {
        this.side = side;
    }
}
