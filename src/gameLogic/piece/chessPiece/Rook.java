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

        /*
        // Pull the current location of the rook
        // I think getLocation works like this
        int x = getLocation()[0];
        int y = getLocation()[1];

        // Store all "viable" moves so we can check if the desired move is in this list
        ArrayList<int[]> validMoveList = new ArrayList<>();


        /*
        The logic for each "loop" is as follows: Fix the current location
        to the desired row or file (depends on right left search or up down search)
        then just loop within the bounnds of our board (8x8).

        We then check the following:

        i) current square is empty, if it is we add it to validMoveList

        ii) if square is not empty, then there is a piece there, we check if it is opposite color
        if it is we add it to validMoveList.

        iii) If it's our own piece, break.
        */

        /*

        // Check moves for  (HORI. L)
        for (int i = y - 1; i >= 0; i--)
        {
            Piece piece = Chess.getBoard()[x][i];

            if (piece == null)
            {
                validMoveList.add(new int[]{x, i});
            }

            else if (piece.getSide() != getSide())
            {
                validMoveList.add(new int[]{x, i});
                break;
            }

            else
            {
                break;
            }
        }

        // Check moves for  (HORI. R)
        for (int i = y + 1; i < 8; i++)
        {
            Piece piece = Chess.getBoard()[x][i];

            if (piece == null)
            {
                validMoveList.add(new int[]{x, i});
            }

            else if (piece.getSide() != getSide())
            {
                validMoveList.add(new int[]{x, i});
                break;
            }

            else
            {
                break;
            }
        }

        // Check moves for  (VERT. UP)
        for (int i = x - 1; i >= 0; i--)
        {
            Piece piece = Chess.getBoard()[i][y];

            if (piece == null)
            {
                validMoveList.add(new int[]{i, y});
            }

            else if (piece.getSide() != getSide())
            {
                validMoveList.add(new int[]{i, y});
                break;
            }

            else
            {
                break;
            }
        }

        // Check moves for  (VERT. DOWN)
        for (int i = x + 1; i < 8; i++)
        {
            Piece piece = Chess.getBoard()[i][y];

            if (piece == null)
            {
                validMoveList.add(new int[]{i, y});
            }

            else if (piece.getSide() != getSide())
            {
                validMoveList.add(new int[]{i, y});
                break;
            }

            else
            {
                break;
            }
        }

        // Convert into our 2D array, this will hold the Coordinates
        // of all viable moves for our rook
        int[][] validMovesArray = new int[validMoveList.size()][2];

        for (int i = 0; i < validMoveList.size(); i++)
        {
            validMovesArray[i] = validMoveList.get(i);
        }

        return validMovesArray;

        */
    }
}