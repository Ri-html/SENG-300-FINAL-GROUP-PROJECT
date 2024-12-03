package gameLogic.piece.chessPiece;
import gameLogic.piece.*;
import gameLogic.side.ChessSide;

import java.util.*;

public class Pawn extends AbstractChessPiece {
    private ChessSide side;
    public Pawn(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.pawn.getSymbol();
    }

    @Override
    int[][] getValidMoves(Piece[][] board) {

        List<int[]> validMoves = new ArrayList<>();

        // Pawn direction - -1 for white (moves up), 1 for black (moves down)
        final int DIRECTION = this.getSide() == ChessSide.WHITE ? -1 : 1;
        // Starting row: row 1 for BLACK, row 6 for WHITE
        final int START_ROW = this.getSide() == ChessSide.WHITE ? 6 : 1;
        // move directions for captures
        final int[] PAWN_MOVES = {-1, 1};

        int x = this.getLocation()[0];
        int y = this.getLocation()[1];
        int newY = y + DIRECTION;


        // check forward move (1 square)
        if (isInBounds(x, newY) && board[x][newY] == null) {
            validMoves.add(new int[] {x, newY});

            // check forward move (2 squares) if on starting row
            if (y == START_ROW && board[x][newY + DIRECTION] == null) {
                validMoves.add(new int[] {x, newY + DIRECTION});
            }
        }

        // check diagonal capture moves
        for (int dx : PAWN_MOVES) {
            int newX = x + dx;
            if (isInBounds(newX, newY) && board[newX][newY] != this.getSide()) {
                validMoves.add(new int[] {newX, newY});
            }
        }

        return validMoves.toArray(new int[validMoves.size()][2]);
    }
}
