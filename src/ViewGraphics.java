import java.awt.*;

public class ViewGraphics implements Observer {

    private Draw grid;

    public ViewGraphics(Draw grid) {
        this.grid = grid;
        grid.setScale(0, Math.max(Board.NUMBER_OF_COLS,Board.NUMBER_OF_ROWS)*40);
        grid.clear(Color.BLUE);
    }

    @Override
    public void update(Object subject) {
        Board board = (Board)subject;

        for(int col=0; col<Board.NUMBER_OF_COLS; col++) {
            for(int row=0; row<Board.NUMBER_OF_ROWS; row++) {
                if (board.get(col,row)==Board.PLAYER1) {
                    grid.setPenColor(Color.RED);
                } else if (board.get(col,row)==Board.PLAYER2) {
                    grid.setPenColor(Color.YELLOW);
                } else {
                    grid.setPenColor(Color.LIGHT_GRAY);
                }
                grid.filledCircle(20+col*40, Board.NUMBER_OF_ROWS*40-(row*40)+20, 15);
            }
        }
    }

    public void endGame(String result) {
        grid.setPenColor(Color.GREEN);
        grid.setPenRadius(30);
        grid.text(40*Board.NUMBER_OF_COLS/2.0,20,result);
    }
}
