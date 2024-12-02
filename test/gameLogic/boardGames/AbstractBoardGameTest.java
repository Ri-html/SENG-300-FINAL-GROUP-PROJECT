package gameLogic.boardGames;
import gameLogic.piece.Piece;
import gameLogic.piece.chessPiece.Pawn;
import gameLogic.side.ChessSide;
import gameLogic.side.Side;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class AbstractBoardGameTest {
    SomeBoardGame boardGame;
    @Before
    public void resetBoardGame(){
        boardGame= new SomeBoardGame(2);
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
        String now= " , , \n , , \n , , \n";
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
        boardGame.setIsMoveCorrect(false);
        boardGame.update(new int[]{1 , 2});
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.OVER);
    }

    @Test
    public void gameTurnTest2(){
        boardGame.addPlayer("rrr");
        boardGame.addPlayer("rrr");
        String one= boardGame.toString();
        boardGame.setIsMoveCorrect(true);
        boardGame.update(new int[]{1 , 2});
        String two= boardGame.toString();
        assertNotEquals(one,two);
    }

    @Test
    public void toStringTest(){
        String string= "null";
        assertEquals(boardGame.toString(), string);
    }

    private static class SomeBoardGame extends AbstractBoardGame {

        GameEndState state;
        Boolean isMoveCorrect;
        public SomeBoardGame(int playerNum) {
            super(playerNum);
        }

        @Override
        protected Piece[][] setUpBoard() {
            return new Piece[3][3];
        }

        @Override
        public GameEndState validateGameEnds() {
            return state;
        }

        @Override
        public boolean validateMove(int[] moves) {
            return isMoveCorrect;
        }

        @Override
        public void makeMove(int[] moves) {
            ChessSide side= ChessSide.WHITE;
            Piece one= new Pawn(side);
            this.placeBoardPiece(one, moves[0], moves[1]);
        }

        public void setState(GameEndState state) {
            this.state = state;
        }

        public void setIsMoveCorrect(Boolean isMoveCorrect) {
            this.isMoveCorrect = isMoveCorrect;
        }
    }
}

