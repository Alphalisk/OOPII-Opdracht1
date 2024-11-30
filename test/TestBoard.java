import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @Test
    @DisplayName("Should be a grid of 8x8")
    public void testIsABoard() {
        Board grid = new Board();
        assertInstanceOf(Board.class, grid);
        assertEquals(Board.NUMBER_OF_COLS,8);
        assertEquals(Board.NUMBER_OF_ROWS,8);
    }

    @Test
    @DisplayName("Should get specific value in grid")
    public void testGet() {
        Board grid = new Board();
        assertEquals(grid.get(4,4),0);
    }

    @Test
    @DisplayName("Is column a valid option?")
    public void testIsValidMove() {
        Board grid = new Board();
        assertEquals(grid.isValidMove(4),true);
    }

    @Test
    @DisplayName("Should place a stone in a column")
    public void testDoMove() {
        Board grid = new Board();
        int player = grid.PLAYER1;
        int player2 = grid.PLAYER2;
        int column = 1;

        grid = grid.doMove(player,column);
        grid = grid.doMove(player,column);
        grid = grid.doMove(player,column);
        grid = grid.doMove(player,column);
        grid = grid.doMove(player,column);
        grid = grid.doMove(player2,column);
        grid = grid.doMove(player,column);

        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-1),1);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-2),1);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-3),1);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-4),1);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-5),1);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-6),2);
        assertEquals(grid.get(column,grid.NUMBER_OF_ROWS-7),1);
    }

    @Test
    @DisplayName("Is the grid full of stones?")
    public void testIsFinished() {
        Board grid = new Board();
        int player = grid.PLAYER1;

        assertEquals(grid.isFinished(),false);

        for (int col = 0; col < grid.NUMBER_OF_COLS; col++) {
            for (int row = 0; row < grid.NUMBER_OF_ROWS; row++) {
                grid = grid.doMove(player,col);
            }
        }

        assertEquals(grid.isFinished(),true);
    }

    @Test
    @DisplayName("Is the player a winner?")
    public void testIsWinner() {
        Board grid = new Board();
        int player1 = grid.PLAYER1;
        int player2 = grid.PLAYER2;

        assertEquals(grid.isWinner(player1),false);
        assertEquals(grid.isWinner(player2),false);

        // vertical win
        Board gridVertical = new Board();
        int col = 1;
        gridVertical = gridVertical.doMove(player1,col);
        gridVertical = gridVertical.doMove(player1,col);
        gridVertical = gridVertical.doMove(player1,col);
        gridVertical = gridVertical.doMove(player1,col);
        assertEquals(gridVertical.isWinner(player1),true);
        assertEquals(gridVertical.isWinner(player2),false);

        // horizontal win
        Board gridHorizontal = new Board();
        gridHorizontal = gridHorizontal.doMove(player1,0);
        gridHorizontal = gridHorizontal.doMove(player1,1);
        gridHorizontal = gridHorizontal.doMove(player1,2);
        gridHorizontal = gridHorizontal.doMove(player1,3);
        assertEquals(gridHorizontal.isWinner(player1),true);
        assertEquals(gridHorizontal.isWinner(player2),false);

        // diagonal up win
        Board gridDiagonal = new Board();
        gridDiagonal = gridDiagonal.doMove(player1,0);
        gridDiagonal = gridDiagonal.doMove(player2,1);
        gridDiagonal = gridDiagonal.doMove(player1,1);
        gridDiagonal = gridDiagonal.doMove(player2,2);
        gridDiagonal = gridDiagonal.doMove(player2,2);
        gridDiagonal = gridDiagonal.doMove(player1,2);
        gridDiagonal = gridDiagonal.doMove(player2,3);
        gridDiagonal = gridDiagonal.doMove(player2,3);
        gridDiagonal = gridDiagonal.doMove(player2,3);
        gridDiagonal = gridDiagonal.doMove(player1,3);

        assertEquals(gridDiagonal.isWinner(player1),true);
        assertEquals(gridDiagonal.isWinner(player2),false);

        // diagonal down win
        Board gridDiagonalD = new Board();
        gridDiagonalD = gridDiagonalD.doMove(player2,0);
        gridDiagonalD = gridDiagonalD.doMove(player2,0);
        gridDiagonalD = gridDiagonalD.doMove(player2,0);
        gridDiagonalD = gridDiagonalD.doMove(player1,0);
        gridDiagonalD = gridDiagonalD.doMove(player2,1);
        gridDiagonalD = gridDiagonalD.doMove(player2,1);
        gridDiagonalD = gridDiagonalD.doMove(player1,1);
        gridDiagonalD = gridDiagonalD.doMove(player2,2);
        gridDiagonalD = gridDiagonalD.doMove(player1,2);
        gridDiagonalD = gridDiagonalD.doMove(player1,3);

        assertEquals(gridDiagonalD.isWinner(player1),true);
        assertEquals(gridDiagonalD.isWinner(player2),false);


    }

}
