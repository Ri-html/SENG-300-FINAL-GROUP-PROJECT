package gameLogic.piece;

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
    public PieceType getType(){}


}
