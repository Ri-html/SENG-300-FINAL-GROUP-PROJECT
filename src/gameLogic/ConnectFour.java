package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.Game;
import gameLogic.player.AbstractPlayer;

public class ConnectFour extends AbstractBoardGame {

    public ConnectFour(int player1, int player2) {
        super();
        board = new int[6][7];
    }

    public void start() {
        gameState = gameLogic.gameState.INPROGRESS;
        alertPlayer(currentPlayer);
    }

    public void placePiece(int player, int x) {
        isValid = isValidMove(x);
        for (int i = 6; i >= 0; i--) {
            if (board[i][x] == 'X') {
                if (player1 == player) {
                    board[i][x] = 'R'
                } else {
                    board[i][x] = 'Y'
                }
                return;
            }
        }
        checkWin();
    }

    public checkWin() {
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

    public checkMoves(int x, int y) {

    }

    @Override
    public boolean validateVictory() {
        return false;
    }

}
