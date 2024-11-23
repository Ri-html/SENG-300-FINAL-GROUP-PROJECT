package gameLogic.boardGames;

import gameLogic.player.AbstractPlayer;

public interface Game {
    boolean validateVictory(); //interface methods public by default

    void setCurrentPlayer();

    void addPlayer(AbstractPlayer player);
}
