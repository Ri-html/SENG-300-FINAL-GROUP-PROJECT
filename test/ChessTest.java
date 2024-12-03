import gameLogic.side.ChessSide;
import org.junit.jupiter.api.Test;
import gameLogic.Chess;
import gameLogic.piece.chessPiece.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessTest {

    //test empty board
    @Test
    void boardEmpty(){
        Chess chess = new Chess();
        AbstractChessPiece[][] testBoard = new AbstractChessPiece[8][8];
        for (int i = 0; i < chess.getBoard().length; i++) {
            //even though this should be square, just make sure
            for (int j = 0; j < chess.getBoard()[0].length; j++) {
                assertEquals(chess.getBoard()[i][j], testBoard[i][j]);
            }
        }
    }

    //test board setup state
}
