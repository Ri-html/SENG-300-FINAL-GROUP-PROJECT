package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;

public class Chess extends AbstractBoardGame {

    public Chess() {
    }

    public void newGame(){ //is this void?

    }

    @Override
    public GameEndState validateGameEnds() {
        return GameEndState.Ongoing;
    }

}
