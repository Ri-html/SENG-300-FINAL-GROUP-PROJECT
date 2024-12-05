package gameLogic.boardGames;
import gameLogic.piece.Piece;
import gameLogic.piece.chessPiece.Pawn;
import gameLogic.side.ChessSide;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class AbstractBoardGameTest implements BoardGameObserver{
    SomeBoardGame boardGame;
    String testUpdate;
    @Before
    public void resetBoardGame(){
        testUpdate = "";
        boardGame= new SomeBoardGame(2);
        boardGame.attachBoardSetupObserver(this);
        boardGame.attachGameEndObserver(this);
        boardGame.attachTurnEndObserver(this);
    }

    @Test
    public void addPlayerTest(){
        //the game should not be running with only one player
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.WAITING);
        //the game should be running with two players
        boardGame.addPlayer("rerere");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.INPROGRESS);
        //check to see if error message is send when we add too many players
        boardGame.addPlayer("rerere");
    }
    @Test
    public void testBoardSetupObserver(){
        String now= " , , \n , , \n , , \n";
        boardGame.addPlayer("rerere");
        boardGame.addPlayer("rerere");
        System.out.println(boardGame.toString());
        assertEquals(now, testUpdate);
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
        boardGame.updateMove("1,2");
        assertEquals(boardGame.getGameState(), AbstractBoardGame.GameState.OVER);
    }

    @Test
    public void gameTurnTest2(){
        boardGame.addPlayer("rrr");
        boardGame.addPlayer("rrr");
        String one= boardGame.toString();
        boardGame.setIsMoveCorrect(true);
        boardGame.updateMove("1 , 2");
        String two= boardGame.toString();
        assertNotEquals(one,two);
        //assertNotEquals is junit5, not junit4.
    }

    @Test
    public void turnEndObserverTest(){
        String moves="1,2";
        boardGame.addPlayer("rrr");
        boardGame.addPlayer("rrr");
        boardGame.setIsMoveCorrect(true);
        boardGame.updateMove(moves);
        assertEquals(moves,testUpdate);
    }

    @Test
    public void toStringTest(){
        String string= "null";
        assertEquals(boardGame.toString(), string);
    }

    public void update(String element) {
        String[] list=element.split("\n");
        switch (list[0]) {
            case "Setup":
                StringBuilder builder = new StringBuilder();
                for (int i = 2; i < list.length; i++) {
                    builder.append(list[i]).append("\n");
                }
                testUpdate = builder.toString();
                break;
            case "TurnEnd":
                testUpdate = list[2];
                break;
            case "GameEnd":
                testUpdate = list[2];
                break;
        }

    }

    private static class SomeBoardGame extends AbstractBoardGame {

        int state;
        Boolean isMoveCorrect;
        public SomeBoardGame(int playerNum) {
            super(playerNum, 1, 1);
        }

        @Override
        protected Piece[][] setUpBoard() {
            return new Piece[3][3];
        }

        @Override
        public int validateGameEnds() {
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

        public void setState(int state) {
            this.state = state;
        }

        public void setIsMoveCorrect(Boolean isMoveCorrect) {
            this.isMoveCorrect = isMoveCorrect;
        }
    }
}

