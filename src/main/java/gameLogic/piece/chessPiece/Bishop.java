package gameLogic.piece.chessPiece;

import gameLogic.piece.*;
import gameLogic.side.ChessSide;

import java.util.*;

public class Bishop extends AbstractChessPiece{
    private ChessSide side;
    public Bishop(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.bishop.getSymbol();
    }

    @Override
    public int[][] getValidMoves(Piece[][] board) {

        List<int[]> validMoves = new ArrayList<>();

        final int[][] BISHOP_MOVES = {
                {-1, -1},
                {-1,  1},
                { 1, -1},
                { 1,  1}
        };

        for (int[] direction : BISHOP_MOVES) {
            int dx = direction[0];
            int dy = direction[1];
            int newX = this.getLocation()[0] + dx;
            int newY = this.getLocation()[1] + dy;

            // keep checking in current direction until blocked or out of bounds
            while (isInBounds(newX, newY)) {
                if (isValidSquare(board, newX, newY)) {
                        validMoves.add(new int[]{newX, newY});
                    // stop if the square contains an opponent's piece
                    if (board[newX][newY] != null) break;
                } else {
                    break;  // not valid square, i.e. path is blocked by our piece
                }

                // increment to check next square
                newX += dx;
                newY += dy;
            }
        }

        return validMoves.toArray(new int[validMoves.size()][2]);
    }

}
