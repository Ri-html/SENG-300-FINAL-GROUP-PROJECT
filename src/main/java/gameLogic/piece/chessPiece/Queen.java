package gameLogic.piece.chessPiece;
import gameLogic.side.ChessSide;

public class Queen extends AbstractChessPiece {
    private ChessSide side;
    public Queen(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.queen.getSymbol();
    }
}
