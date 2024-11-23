package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.Game;
import gameLogic.player.AbstractPlayer;

public class TicTacToe extends AbstractBoardGame {
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
