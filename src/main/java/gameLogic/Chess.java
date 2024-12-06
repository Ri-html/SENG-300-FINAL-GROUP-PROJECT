package gameLogic;

import gameLogic.boardGames.AbstractBoardGame;
import gameLogic.boardGames.BoardGameObserver;
import gameLogic.piece.Piece;
import gameLogic.piece.chessPiece.*;
import gameLogic.side.ChessSide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Chess extends AbstractBoardGame {

    public static ArrayList<Piece> capturedPieces = new ArrayList<>();

    //constructor
    //chess can only take 2 players, board is 8x8
    public Chess() {
        super(2, 8, 8);
        setUpBoard();
        currentPlayer = 0;
    }
    

    @Override
    protected Piece[][] setUpBoard() {
        //need to assign pieces to a player
        //add white pieces to board
        addToBoard(gameBoard, new Rook(ChessSide.WHITE), 0, 0);
        addToBoard(gameBoard, new Knight(ChessSide.WHITE), 0, 1);
        addToBoard(gameBoard, new Bishop(ChessSide.WHITE), 0, 2);
        addToBoard(gameBoard, new Queen(ChessSide.WHITE), 0, 3);
        addToBoard(gameBoard, new King(ChessSide.WHITE), 0, 4);
        addToBoard(gameBoard, new Bishop(ChessSide.WHITE), 0, 5);
        addToBoard(gameBoard, new Knight(ChessSide.WHITE), 0, 6);
        addToBoard(gameBoard, new Rook(ChessSide.WHITE), 0, 7);
        //add white pawns
        for (int i = 0; i < 8; i++) {
            addToBoard(gameBoard, new Pawn(ChessSide.WHITE), 1, i);
        }
        //add black pieces to board
        addToBoard(gameBoard, new Rook(ChessSide.BLACK), 7, 0);
        addToBoard(gameBoard, new Knight(ChessSide.BLACK), 7, 1);
        addToBoard(gameBoard, new Bishop(ChessSide.BLACK), 7, 2);
        addToBoard(gameBoard, new Queen(ChessSide.BLACK), 7, 3);
        addToBoard(gameBoard, new King(ChessSide.BLACK), 7, 4);
        addToBoard(gameBoard, new Bishop(ChessSide.BLACK), 7, 5);
        addToBoard(gameBoard, new Knight(ChessSide.BLACK), 7, 6);
        addToBoard(gameBoard, new Rook(ChessSide.BLACK), 7, 7);
        //add black pawns
        for (int i = 0; i < 8; i++) {
            addToBoard(gameBoard, new Pawn(ChessSide.BLACK), 6, i);
        }

        //update internal locations to reflect placement on board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(gameBoard[i][j] instanceof AbstractChessPiece){
                    ((AbstractChessPiece) gameBoard[i][j]).setLocation(new int[]{i,j});
                }
            }
        }

        //set white to move
        this.setCurrentPlayer(0);

        return gameBoard;
    }
    //handle adding a piece to the board
    public void addToBoard(Piece[][] board, AbstractChessPiece piece, int x, int y) {
        board[x][y] = piece;
        piece.setLocation(new int[] {x, y});
    }

    /**
     * Returns gameBoard state
     */
    public Piece[][] getBoard() {
        return gameBoard;
    }

    /**
     * Checks if the game ends or not
     * @return -1 if game ongoing, 0 if stalemate. 1 for white win, 2 for black win
     */
    @Override
    public int validateGameEnds() {
        String cp = this.getCurrentPlayer();
        ChessSide us;
        ChessSide them;
        if(Objects.equals(cp, players[0])){
            //white's turn
            us = ChessSide.WHITE;
            them = ChessSide.BLACK;
        }
        else{
            //black's turn
            us = ChessSide.BLACK;
            them = ChessSide.WHITE;
        }
        int[] oppKing = AbstractChessPiece.findKing(gameBoard, them);
        int[] ourKing = AbstractChessPiece.findKing(gameBoard, us);
        //if opposing king is in check & our move, we win
        if(((AbstractChessPiece)gameBoard[oppKing[0]][oppKing[1]]).isInCheck(gameBoard)){
            //check which side wins
            if(gameBoard[oppKing[0]][oppKing[1]].getSide() == ChessSide.WHITE){
                return 2;
            }
            return 1;
        }
        //if our king is in check & we have no valid moves, opponent wins
        else if(((AbstractChessPiece)gameBoard[ourKing[0]][ourKing[1]]).isInCheck(gameBoard)){
            boolean noWin = false;
            //check over all possible moves for this player
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(gameBoard[i][j] instanceof AbstractChessPiece){
                        if(gameBoard[i][j].getSide()==us){
                            int[][] moves = ((AbstractChessPiece) gameBoard[i][j]).getValidMoves(gameBoard);
                            //check if each move prevents check
                            for (int[] move : moves) {
                                //this could be null
                                AbstractChessPiece temp = (AbstractChessPiece) gameBoard[move[0]][move[1]];
                                //make the move
                                ((AbstractChessPiece) gameBoard[i][j]).makeMove(gameBoard, move[0], move[1]);
                                //if the move makes the king not in check
                                if (!((AbstractChessPiece) gameBoard[ourKing[0]][ourKing[1]]).isInCheck(gameBoard)) {
                                    //undo the move:
                                    if (gameBoard[move[0]][move[1]] != null) {
                                        ((AbstractChessPiece) gameBoard[move[0]][move[1]]).undoMove(gameBoard, i, j);
                                    }
                                    //return -1 and game can continue
                                    return -1;
                                }
                                //undo the move
                                //move piece back
                                if (gameBoard[move[0]][move[1]] != null) {
                                    ((AbstractChessPiece) gameBoard[move[0]][move[1]]).undoMove(gameBoard, i, j);
                                }
                            }
                        }
                    }
                }
            }
            //if we don't return at all in the loop, there is a winner
            if(((AbstractChessPiece)gameBoard[oppKing[0]][oppKing[1]]).getSide() == ChessSide.WHITE){
                //white's king in check -> black wins
                return 2;
            }
            //else white wins
            return 1;
        }
        //if our king is not in check & we have no valid moves, stalemate
        else if(!((AbstractChessPiece)gameBoard[ourKing[0]][ourKing[1]]).isInCheck(gameBoard)){
            //compile all possible moves for this player
            ArrayList<int[]> allPossibleMoves = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(gameBoard[i][j] instanceof AbstractChessPiece){
                        if(gameBoard[i][j].getSide()==us){
                            int[][] temp = ((AbstractChessPiece) gameBoard[i][j]).getValidMoves(gameBoard);
                            allPossibleMoves.addAll(Arrays.asList(temp));
                        }
                    }
                }
            }
            //stalemate
            if(allPossibleMoves.isEmpty()){
                return 0;
            }
        }
        //game continues
        return -1;
    }

    /**
     * moves = int[] [currentX, currentY, newX, newY]
     */
    @Override
    public boolean validateMove(int[] moves) {
        int currX = moves[0];
        int currY = moves[1];
        int[] newXY = new int[]{moves[2], moves[3]};
        int[][] valids = ((AbstractChessPiece)gameBoard[currX][currY]).getValidMoves(gameBoard);
        for (int[] valid : valids) {
            if(Arrays.equals(valid, newXY)){
                return true;
            }
        }
        System.out.println(this.toString());
        return false;
    }


    /**
     * Calls makeMove in AbstractChessPiece
     */
    @Override
    public void makeMove(int[] moves) {
        int currX = moves[0];
        int currY = moves[1];
        int newX = moves[2];
        int newY = moves[3];
        //if can make move, make move (in if block), then update piece x,
        ((AbstractChessPiece)gameBoard[currX][currY]).makeMove(gameBoard, newX, newY);
    }

}
