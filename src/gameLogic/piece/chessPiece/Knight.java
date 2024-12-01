package gameLogic.piece.chessPiece;
import gameLogic.piece.*;
import gameLogic.side.ChessSide;

import java.util.*;

public class Knight extends AbstractChessPiece{
    private ChessSide side;
    public Knight(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.knight.getSymbol();
    }

    @Override
    public int[][] getValidMoves(Piece[][] board) {

        final int[][] KNIGHT_MOVES = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        List<int[]> validMoves = new ArrayList<>();

        int x = this.getLocation()[0];
        int y = this.getLocation()[1];

        for (int[] move : KNIGHT_MOVES) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (isInBounds(newX, newY) && isValidSquare(board, newX, newY)) {
                validMoves.add(new int[] {newX, newY});
            }
        }

        return validMoves.toArray(new int[validMoves.size()][2]);
    }

}
