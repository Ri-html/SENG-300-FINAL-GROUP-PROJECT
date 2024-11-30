package gameLogic.piece.chessPiece;

import gameLogic.side.ChessSide;

import java.sql.Array;

public class King extends AbstractChessPiece{
    private ChessSide side;
    public King(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.king.getSymbol();
    }

    @Override
    public int[][] getValidMoves() {
        int[][] squaresCanMove = new int[8][2];
        int y = this.getLocation()[0];
        int x = this.getLocation()[1];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //filter out current position as it doesn't take a move
                if(!(x==0 && y==0)){
                    //check piece is in bounds of board
                    if(x+i <=0 && x+i <= 7){
                        if(y+j <=0 && y+j <= 7){

                        }
                    }
                }
            }
        }
        int[][] validMoves = new int[8][2];

    }
}
