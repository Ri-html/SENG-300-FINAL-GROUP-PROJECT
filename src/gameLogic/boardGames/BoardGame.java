package gameLogic.boardGames;

import gameLogic.piece.AbstractPiece;

public interface BoardGame {
    void placeBoardPiece(AbstractPiece piece, int x, int y);

    void setUpBoard();
}
