package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.Game;
import gameLogic.player.AbstractPlayer;

public class Chess extends AbstractBoardGame {

    public Chess() {
    }

    public void newGame(){ //is this void?

    }

    @Override
    public boolean validateVictory() {
        return false;
    }

}
