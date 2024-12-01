package gameLogic.piece.chessPiece;

public enum ChessPieceDisplay {
    bishop('B'),
    king('K'),
    knight('N'),
    pawn('P'),
    queen('Q'),
    rook('R');

    public final char symbol;
    ChessPieceDisplay(char ch) {
        symbol = ch;
    }

    public char getSymbol() {
        return symbol;
    }
}
