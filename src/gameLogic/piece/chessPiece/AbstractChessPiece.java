package gameLogic.piece.chessPiece;

import gameLogic.piece.AbstractPiece;
import gameLogic.piece.PieceType;

public class AbstractChessPiece extends AbstractPiece {
    protected PieceType type = PieceType.ChessPieceType;


    public void checkChessMove(){ //cannot have an abstract method in a non-abstract class
    };
}
