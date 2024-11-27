package gameLogic.boardGames;
import gameLogic.piece.Piece;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class AbstractBoardGameTest {
    SomeBoardGame boardGame;
    @Before
    public void resetBoardGame(){
        boardGame=new SomeBoardGame(2);
    }
    @Test
    public void addPlayerTest(){
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.WAITING);
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.INPROGRESS);
        boardGame.addPlayer("rerere");
    }
}

class SomeBoardGame extends AbstractBoardGame {

    public SomeBoardGame(int playerNum) {
        super(playerNum);
    }

    @Override
    protected void setUpBoard(Piece[][] board) {
    }

    @Override
    public GameEndState validateGameEnds() {
        return null;
    }

    @Override
    public boolean validateMove(int[] moves) {
        return false;
    }

    @Override
    public void makeMove(int[] moves) {

    }
}
