package gameLogic.piece.chessPiece;

import gameLogic.side.ChessSide;

public class Bishop extends AbstractChessPiece{
    private ChessSide side;
    public Bishop(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.bishop.getSymbol();
    }

    @Override
    int[][] getValidMoves() {
        while(//in bounds of board and no piece in way){

        }
    }

}
