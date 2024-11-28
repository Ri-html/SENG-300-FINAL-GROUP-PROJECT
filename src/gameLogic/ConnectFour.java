package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGame;
import gameLogic.piece.Piece;

public class ConnectFour extends AbstractBoardGame {
    boolean isValid;
    String board[][]= new String[6][7];
    public ConnectFour(int player1, int player2,int playerNum) {
        super(playerNum);
    }

    private boolean placePiece(int player, int x) {
        isValid = isValidMove(x);
        for (int i = board[0].length-1; i >= 0; i--) {
            if (board[i][x] == 'X') {
                if (player1 == player) {
                    board[i][x] = 'R';
                } else {
                    board[i][x] = 'Y';
                }
                return;
            }
        }
    }

    public boolean checkWin() {
        if (checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin()) {
            return true;
        }
        return false;
    }

    private boolean checkHorizontalWin() {
        //!TODO
    }

    private boolean checkVerticalWin() {
        //!TODO
    }

    private boolean checkDiagonalWin() {
        //!TODO
    }

    private boolean isFull() {
        //!TODO
    }

    public char[][] getBoard() {
        return board;
    }

    private boolean isValidMove(int x) {
        return board[0][x] == 'X';
    }
*/
    public void checkMoves(int x, int y) {
        return isValidMove(x);
    }

    @Override
    protected Piece[][] setUpBoard() {
        return new Piece[3][3];
    }

    @Override
    public GameEndState validateGameEnds() {
        if (checkWin()) {
            return GameEndState.Victory;
        } else if (isFull()){
            return GameEndState.DRAW;
        } else {
            return GameEndState.Ongoing;
        }
    }

    @Override
    public boolean validateMove(int[] moves) {
        isValidMove(moves[0]);
    }

    @Override
    public void makeMove(int player1, int[] moves) {
        validateMove(int[] moves);
        placePiece(player1, moves[0]);
    }

}
