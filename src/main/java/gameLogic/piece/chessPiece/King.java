package gameLogic.piece.chessPiece;

import gameLogic.piece.Piece;
import gameLogic.side.ChessSide;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class King extends AbstractChessPiece{
    private ChessSide side;
    private boolean hasMoved = false;
    public King(ChessSide side) {
        super(side);
        display=ChessPieceDisplay.king.getSymbol();
    }

    @Override
    public int[][] getValidMoves(Piece[][] board) {
        List<int[]> validMoves = new ArrayList<>();

        final int[][] KING_MOVES = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        int x = this.getLocation()[0];
        int y = this.getLocation()[1];

        for (int[] move : KING_MOVES) {
            int newX = x + move[0];
            int newY = y + move[1];

            if(isInBounds(newX, newY) && isValidSquare(board, newX, newY)) {
                    validMoves.add(new int[]{newX, newY});
            }
        }

        return validMoves.toArray(new int[validMoves.size()][2]);
    }

    // Logic needed to facilitate castling
    public boolean hasMoved() {
        return hasMoved;
    }
    public void setMoved() {
        this.hasMoved = true;
    }

    // Castle logic (for king side)
    private boolean canCastleKingSide(Piece[][] board, int kingX, int kingY)
    {
        // Check that king has not moved AND rook (king side) is in the furthest corner AND has not moved
        if (!this.hasMoved() && board[kingX][7] instanceof Rook && !((Rook) board[kingX][7]).hasMoved())
        {
            // Check if the path is clear between king and rook
            for (int i = kingY + 1; i < 7; i++) {
                if (board[kingX][i] != null) {
                    return false;
                }
            }

            // Need to add logic to check that the king is not in check currently
            // Need to add logic to check that castling won't put king in check
            return false;
        }

        return false;
    }

    private boolean canCastleQueenSide(Piece[][] board, int kingX, int kingY)
    {
        // Moving once invalidates castling chance
        if (this.hasMoved()) return false;

        return false;
    }
}
