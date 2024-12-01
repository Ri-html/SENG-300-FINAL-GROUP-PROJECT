package gameLogic.piece.chessPiece;

import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;

import java.sql.Array;
import java.util.ArrayList;

public class King extends AbstractChessPiece{
    private ChessSide side;
    public King(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.king.getSymbol();
    }

    @Override
    public int[][] getValidMoves(Piece[][] board) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        int y = this.getLocation()[0];
        int x = this.getLocation()[1];
        //get all moves 1 square around the king
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //filter out current position as it doesn't take a move
                if(!(x==0 && y==0)){
                    //check move is in bounds of board
                    if(x+i <=0 && x+i <= 7){
                        if(y+j <=0 && y+j <= 7){
                            //add move to list of valid moves
                            validMoves.add(new int[] {x+i, y+j});
                        }
                    }
                }
            }
        }
        return validMoves.toArray(new int[validMoves.size()][]);
    }
}
