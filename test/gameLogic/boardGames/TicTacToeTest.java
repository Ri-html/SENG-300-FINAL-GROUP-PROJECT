package gameLogic.boardGames;

import gameLogic.TicTacToe;
import gameLogic.piece.*;
import gameLogic.side.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeTest {

    TicTacToe game1;
    TicTacToe game2;

    private Piece[][] getNewBoard() {
        return new Piece[3][3];
    }
    @Before
    public void setUp() throws Exception {
        game1 = new TicTacToe(2);
        game1.addPlayer("Player 1");
        game1.addPlayer("Player 2");
        game1.setCurrentPlayer(0);

        game2 = new TicTacToe(2);
        game2.addPlayer("Player 1");
        game2.addPlayer("Player 2");
        game2.setCurrentPlayer(0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setUpBoard() {
        TicTacToe game = new TicTacToe(2);
        Piece[][] board = game.setUpBoard();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertNull(board[x][y]);
            }
        }
    }

    @Test
    public void validateGameEnds() {

        // Test ongoing
        assertEquals(game1.validateGameEnds(), -1);

        // Test X's
        // Test row wins
        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < 3; x++ ) {
                game1.makeMove(new int[]{x, i});
            }
            assertEquals(game1.validateGameEnds(), 1);
            game1.setGameBoard(getNewBoard());
        }

        // Test column wins
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++ ) {
                game1.makeMove(new int[]{i, y});
            }
            assertEquals(game1.validateGameEnds(), 1);
            game1.setGameBoard(getNewBoard());
        }

        // Test diagonals
        game1.setGameBoard(getNewBoard());
        game1.makeMove(new int[]{0,0});
        game1.makeMove(new int[]{1,1});
        game1.makeMove(new int[]{2,2});
        assertEquals(game1.validateGameEnds(), 1);

        game1.setGameBoard(getNewBoard());
        game1.makeMove(new int[]{2,0});
        game1.makeMove(new int[]{1,1});
        game1.makeMove(new int[]{0,2});
        assertEquals(game1.validateGameEnds(), 1);

        // Test Y's
        // Test row wins
        game1.switchCurrentPlayer();
        game1.setGameBoard(getNewBoard());
        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < 3; x++ ) {
                game1.makeMove(new int[]{x, i});
            }
            assertEquals(game1.validateGameEnds(), 2);
            game1.setGameBoard(getNewBoard());
        }

        // Test column wins
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++ ) {
                game1.makeMove(new int[]{i, y});
            }
            assertEquals(game1.validateGameEnds(), 2);
            game1.setGameBoard(getNewBoard());
        }

        // Test diagonals
        game1.setGameBoard(getNewBoard());
        game1.makeMove(new int[]{0,0});
        game1.makeMove(new int[]{1,1});
        game1.makeMove(new int[]{2,2});
        assertEquals(game1.validateGameEnds(), 2);

        game1.setGameBoard(getNewBoard());
        game1.makeMove(new int[]{2,0});
        game1.makeMove(new int[]{1,1});
        game1.makeMove(new int[]{0,2});
        assertEquals(game1.validateGameEnds(), 2);

        // Test draw
        game1.setGameBoard(getNewBoard());
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertEquals(game1.validateGameEnds(), -1);     // test ongoing with board filling up
                game1.makeMove(new int[]{x,y});
                if (x % 2 == 0 || y % 2 == 0) {
                    game1.switchCurrentPlayer();
                }
            }
        }
        assertEquals(game1.validateGameEnds(), 3);
    }

    @Test
    public void validateMove() {
        // Test X's
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(game1.validateMove(new int[]{x, y}));
                game1.makeMove(new int[]{x, y});
                assertFalse(game1.validateMove(new int[]{x,y}));
            }
        }
        // Test O's
        game1.switchCurrentPlayer();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(game2.validateMove(new int[]{x, y}));
                game2.makeMove(new int[]{x, y});
                assertFalse(game2.validateMove(new int[]{x,y}));
            }
        }
    }

    @Test
    public void makeMove() {

        // Test X's
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                game1.makeMove(new int[]{x, y});
                System.out.print(game1.getGameBoard()[x][y] + ",");
                assertEquals(game1.getGameBoard()[x][y], new TicTacToePiece(TicTacToeSide.X));

                // test occupied spot
                try {
                    game1.makeMove(new int[]{x, y});
                } catch (IllegalArgumentException e) {
                    assertEquals("Piece already occupied", e.getMessage());
                }
            }
            System.out.println();
        }

        // Test O's
        game2.switchCurrentPlayer();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                game2.makeMove(new int[]{x, y});
                System.out.print(game2.getGameBoard()[x][y] + ",");
                assertEquals(game2.getGameBoard()[x][y], new TicTacToePiece(TicTacToeSide.O));

                // test occupied spot
                try {
                    game2.makeMove(new int[]{x, y});
                } catch (IllegalArgumentException e) {
                    assertEquals("Piece already occupied", e.getMessage());
                }
            }
            System.out.println();
        }
    }
}