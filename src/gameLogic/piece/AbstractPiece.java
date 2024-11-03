package gameLogic.piece;

import gameLogic.player.AbstractPlayer;

public abstract class AbstractPiece implements Piece {
    private int[] location; //should this be an x, y?

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void moveLocation(){ //can we not just use setLocation for this?
    }

    public void setType(){
    }

    public PieceType getType(){
        return null;
    }

    public void getValidMoves(){ //why is this void in class diagram?
                                // maybe return array of tuples with all possible moves x,y
    }

    public void updateValidMoves(){
    }

    public void setPlayer(AbstractPlayer player){
    }
}
