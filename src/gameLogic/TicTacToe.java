package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.piece.Piece;

public class TicTacToe extends AbstractBoardGame {
    public TicTacToe(int playerNum) {
        super(playerNum);
    }

    @Override
    protected Piece[][] setUpBoard() {
        return new Piece[0][0];
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
