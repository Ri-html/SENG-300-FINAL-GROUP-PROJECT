package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.Game;
import gameLogic.player.AbstractPlayer;

public class TicTacToe extends AbstractBoardGame {
    public TicTacToe(int playerNum) {
        super(playerNum);
    }

    @Override
    public boolean validateVictory() {
        return false;
    }

}
