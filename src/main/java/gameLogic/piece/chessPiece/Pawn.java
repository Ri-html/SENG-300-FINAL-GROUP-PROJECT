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
    public int[][] getValidMoves(Piece[][] board) {

        List<int[]> validMoves = new ArrayList<>();

        // Pawn direction - 1 for white (moves up), -1 for black (moves down)
        final int DIRECTION = this.getSide() == ChessSide.WHITE ? 1 : -1;
        // Starting row: row 1 for WHITE, row 6 for BLACK
        final int START_ROW = this.getSide() == ChessSide.WHITE ? 1 : 6;
        // move directions for captures
        final int[] PAWN_MOVES = {-1, 1};

        //x is height here
        int x = this.getLocation()[0];
        int y = this.getLocation()[1];
        int newH = x + DIRECTION;


        // check forward move (1 square)
        if (isInBounds(newH, y) && board[newH][y] == null) {
                validMoves.add(new int[]{newH, y});

            // check forward move (2 squares) if on starting row
            if (x == START_ROW && board[newH+DIRECTION][y] == null) {

                    validMoves.add(new int[]{newH + DIRECTION, y});

            }
        }

        // check diagonal capture moves
        for (int dy : PAWN_MOVES) {
            int newHoriz = y + dy;
            if (isInBounds(newH, newHoriz)) {
                if(board[newH][newHoriz] != null) {
                    if (board[newH][newHoriz].getSide() != this.getSide()) {
                            validMoves.add(new int[]{newH, newHoriz});

                    }
                }
            }
        }

        return validMoves.toArray(new int[validMoves.size()][2]);
    }
}
