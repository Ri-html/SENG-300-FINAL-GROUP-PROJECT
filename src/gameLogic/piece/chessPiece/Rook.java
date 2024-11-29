package gameLogic.piece.chessPiece;
import gameLogic.side.ChessSide;

public class Rook extends AbstractChessPiece {
    private ChessSide side;
    public Rook(ChessSide side){
        super(side);
        display=ChessPieceDisplay.rook.getSymbol();
    }

}
