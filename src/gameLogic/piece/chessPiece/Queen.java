package gameLogic.piece.chessPiece;
import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractChessPiece {
    private ChessSide side;
    public Queen(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.queen.getSymbol();
    }

    @Override
    int[][] getValidMoves(Piece[][] board) {

        List<int[]> validMoves = new ArrayList<>();

        final int[][] QUEEN_MOVES = {
                {-1,  0}, { 1,  0}, { 0, -1}, { 0,  1},                 // rook moves
                {-1, -1}, {-1,  1}, { 1, -1}, { 1,  1}                  // bishop moves
        };

        for (int[] direction : QUEEN_MOVES) {
            int dx = direction[0];
            int dy = direction[1];
            int newX = this.getLocation()[0] + dx;
            int newY = this.getLocation()[1] + dy;

            // keep checking in current direction until blocked or out of bounds
            while (isInBounds(newX, newY)) {
                if (isValidSquare(board, newX, newY)) {
                    validMoves.add(new int[] {newX, newY});
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
