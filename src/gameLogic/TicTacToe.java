package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;

public class TicTacToe extends AbstractBoardGame {
    public TicTacToe(int playerNum) {
        super(playerNum);
    }

    @Override
    public GameEndState validateGameEnds() {
        return GameEndState.Ongoing;
    }

}
