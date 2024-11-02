package gameLogic.player;

import gameLogic.piece.Piece;

public abstract class AbstractPlayer {
    private enum side{} //make this work with chessSide and other sides - they should extend
    private String userID;
    private Piece[][] pieces;
    private BoardGame boardGame;

    public enum getSide{};
    public Piece selectPiece;
    public void addPiece(Piece piece){};


}
