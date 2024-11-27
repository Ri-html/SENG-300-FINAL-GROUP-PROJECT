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
        //the game should not be running with only one player
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.WAITING);
        //the game should be running with two players
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.INPROGRESS);
        //check to see if error message is send when we add too much players
        boardGame.addPlayer("rerere");
    }

    @Test
    public void startGameTest(){
        String now= """
                 , , ,
                 , , ,
                 , , ,
                """;
        boardGame.addPlayer("rrr");
        boardGame.addPlayer("rrr");
        assertEquals(now,boardGame.toString());
    }

    @Test
    public void switchPlayerTest(){
        String player1="rrr";
        String player2="rr";
        boardGame.addPlayer(player1);
        boardGame.addPlayer(player2);
        assertEquals(boardGame.getCurrentPlayer(),player1);
        boardGame.switchCurrentPlayer();
        assertEquals(boardGame.getCurrentPlayer(),player2);
        boardGame.switchCurrentPlayer();
        assertEquals(boardGame.getCurrentPlayer(),player1);
    }

    @Test
    public void gameTurnTest(){
        boardGame.addPlayer("rrr");
        boardGame.addPlayer("rrr");
    }

    @Test
    public void toStringTest(){
        String string= "null";
        assertEquals(boardGame.toString(), string);
    }
}

class SomeBoardGame extends AbstractBoardGame {

    public SomeBoardGame(int playerNum) {
        super(playerNum);
    }

    @Override
    protected Piece[][] setUpBoard() {
        return new Piece[3][3];
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
