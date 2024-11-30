import java.awt.*;

public class ViewConsole implements Observer {

    public char boardChar(int player) {
        if (player==Board.PLAYER1) {
            return 'O';
        }
        if (player==Board.PLAYER2) {
            return 'X';
        }
        return '.';
    }

    public void displayBoard(Board board) {
        System.out.println();
        for(int row=0; row<Board.NUMBER_OF_ROWS; row++) {
            for(int col=0; col<Board.NUMBER_OF_COLS; col++) {
                System.out.print(boardChar(board.get(col,row)));
            }
            System.out.println();
        }
    }

    @Override
    public void update(Object subject) {
        Board grid = (Board)subject;
        displayBoard(grid);
    }

    @Override
    public void endGame(String result) {
        System.out.println(result);
    }
}
