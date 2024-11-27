package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGame;
import gameLogic.piece.Piece;

public class ConnectFour extends AbstractBoardGame {
    boolean isValid;
    String board[][]=new String[6][7];
    public ConnectFour(int player1, int player2,int playerNum) {
        super(playerNum);
    }

/*
    public void placePiece(int player, int x) {
        isValid = isValidMove(x);
        for (int i = 6; i >= 0; i--) {
            if (board[i][x] == 'X') {
                if (player1 == player) {
                    board[i][x] = 'R';
                } else {
                    board[i][x] = 'Y';
                }
                return;
            }
        }
        checkWin();
    }

    public void checkWin() {
        checkHorizontalWin();
        checkVerticalWin();
        checkDiagonalWin();
    }

    public char[][] getBoard() {
        return board;
    }

    private boolean isValidMove(int x) {
        return board[0][x] == 'X';
    }
*/
    public void checkMoves(int x, int y) {

    }

    @Override
    protected Piece[][] setUpBoard() {
        return new Piece[3][3];
    }

    @Override
    public GameEndState validateGameEnds() {
        return GameEndState.Ongoing;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return false;
    }

    @Override
    public void makeMove(int[] moves) {

    }

}
