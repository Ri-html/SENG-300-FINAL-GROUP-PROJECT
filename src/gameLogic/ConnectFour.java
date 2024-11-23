package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.Game;
import gameLogic.player.AbstractPlayer;

public class ConnectFour extends AbstractBoardGame {
    private char[][] board;
    private int player1;
    private int player2;
    private int currentPlayer;
    private gameState gameState;

    public ConnectFour(int player1, int player2) {
       board = new int[6][7];
       player1 = player1;
       player2 = player2;
       currentPlayer = player1;
       gameState = gameState.START;
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

    @Override
    public void setCurrentPlayer() {

    }

    @Override
    public void addPlayer(AbstractPlayer player) {

    }
}

enum gameState {
    START,
    INPROGRESS,
    OVER
}