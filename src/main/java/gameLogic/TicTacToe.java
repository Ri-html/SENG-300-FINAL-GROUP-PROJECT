package gameLogic;

import gameLogic.boardGames.*;
import gameLogic.piece.*;
import gameLogic.side.TicTacToeSide;

public class TicTacToe extends AbstractBoardGame {
    public TicTacToe(int playerNum) {
        super(playerNum, 3, 3);
    }

    // Constants
    private static final int WIDTH = 3;

    @Override
    protected Piece[][] setUpBoard() {

        return new Piece[WIDTH][WIDTH];

    }

    /**
     * Validate the game has ended
     * @return 0 for draw. 1 if X wins. 2 if Y wins. -1 if ongoing
     */
    @Override
    public int validateGameEnds() {

        // check for win
        for (int i = 0; i < WIDTH; i++) {
            // check columns
            if (gameBoard[i][0] != null && gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][0].equals(gameBoard[i][2]))
                return gameBoard[i][0].getSide()==TicTacToeSide.X ? 1 : 2;
            // check rows
            if (gameBoard[0][i] != null && gameBoard[0][i].equals(gameBoard[1][i]) && gameBoard[0][i].equals(gameBoard[2][i]))
                return gameBoard[0][i].getSide()==TicTacToeSide.X ? 1 : 2;
        }
        // check diagonals
        if (gameBoard[0][0] != null && gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2]))
            return gameBoard[0][0].getSide()==TicTacToeSide.X ? 1 : 2;
        if (gameBoard[0][2] != null && gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals(gameBoard[2][0]))
            return gameBoard[0][2].getSide()==TicTacToeSide.X ? 1 : 2;

        // check for empty spaces
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (gameBoard[x][y] == null)
                    return -1;
            }
        }

        // board is full
        return 3;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return gameBoard[moves[0]][moves[1]] == null;
    }

    @Override
    public void makeMove(int[] moves) {
        if (currentPlayer == 0) {
            placeBoardPiece(new TicTacToePiece(TicTacToeSide.X), moves[0], moves[1]);
        } else {
            placeBoardPiece(new TicTacToePiece(TicTacToeSide.O), moves[0], moves[1]);
        }
    }
}
