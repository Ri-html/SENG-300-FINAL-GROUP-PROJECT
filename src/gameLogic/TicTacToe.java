package gameLogic;

import gameLogic.boardGames.*;
import gameLogic.piece.*;
import gameLogic.side.TicTacToeSide;

public class TicTacToe extends AbstractBoardGame {
    public TicTacToe(int playerNum) {
        super(playerNum);
    }

    // Constants
    private static final int WIDTH = 3;

    @Override
    protected Piece[][] setUpBoard() {

        return new Piece[WIDTH][WIDTH];

    }

    @Override
    public GameEndState validateGameEnds() {

        // check for win
        for (int i = 0; i < WIDTH; i++) {
            // check columns
            if (gameBoard[i][0] != null && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2])
                return GameEndState.Victory;
            // check rows
            if (gameBoard[0][i] != null && gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i])
                return GameEndState.Victory;
        }
        // check diagonals
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2])
            return GameEndState.Victory;
        if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0])
            return GameEndState.Victory;

        // check for empty spaces
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (gameBoard[x][y] == null)
                    return GameEndState.Ongoing;
            }
        }

        // board is full
        return GameEndState.Draw;
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
