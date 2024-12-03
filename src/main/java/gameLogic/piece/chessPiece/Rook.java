package gameLogic.piece.chessPiece;
import gameLogic.piece.*;
import gameLogic.side.ChessSide;

import java.util.*;

/*
    This class handles the logic for ROOK movements. It takes the initial
    (x,y) location of the ROOK on the board and then "brute force" checks
    for viable moves; Up, Down, Right, or Left. The array of valid moves
    is then returned.

    Author: Noah Pinel
 */

public class Rook extends AbstractChessPiece
{
    private ChessSide side;
    private boolean hasMoved = false;
    public Rook(ChessSide side){
        super(side);
        display=ChessPieceDisplay.rook.getSymbol();
    }

    @Override
    public int[][] getValidMoves(Piece[][] board)
    {

        List<int[]> validMoves = new ArrayList<>();

        final int[][] ROOK_MOVES = {
                {-1,  0},
                { 1,  0},
                { 0, -1},
                { 0,  1}
        };

        for (int[] direction : ROOK_MOVES) {
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

    // Logic needed to facilitate castling
    public boolean hasMoved() {
        return hasMoved;
    }
    public void setMoved() {
        this.hasMoved = true;
    }
}