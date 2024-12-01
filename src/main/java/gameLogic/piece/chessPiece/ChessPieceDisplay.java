package gameLogic.piece.chessPiece;

public enum ChessPieceDisplay {
    bishop('b'),
    king('e'),
    knight('k'),
    pawn('p'),
    queen('q'),
    rook('r');

    public final char symbol;
    ChessPieceDisplay(char ch) {
        symbol = ch;
    }

    public char getSymbol() {
        return symbol;
    }
}
