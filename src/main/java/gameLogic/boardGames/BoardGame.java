package gameLogic.boardGames;

import gameLogic.piece.Piece;

public interface BoardGame extends Game{
    void placeBoardPiece(Piece piece, int x, int y);
}
