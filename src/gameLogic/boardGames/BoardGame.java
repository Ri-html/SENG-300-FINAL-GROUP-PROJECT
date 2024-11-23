package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;

public interface BoardGame extends Game{
    void placeBoardPiece(AbstractPiece piece, int x, int y);

    void setUpBoard();
}
