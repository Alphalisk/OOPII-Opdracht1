public class Board {

    public static final int NONE = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    public static final int NUMBER_OF_COLS = 8;
    public static final int NUMBER_OF_ROWS = 8;

    private final int[][] board;

    public Board() {
        board = new int[NUMBER_OF_COLS][NUMBER_OF_ROWS];
    }

    public Board(Board board) {
        this();
        // De array wordt gekopiëerd, om onbedoelde wijzigingen in de array van het argument te voorkomen.
        for (int col = 0; col < NUMBER_OF_COLS; col++) {
            for (int row = 0; row < NUMBER_OF_ROWS; row++) {
                this.board[col][row] = board.get(col, row);
            }
        }
    }

    public Board(Board board, int player, int column) {
        this(board);
        if (isValidMove(column)) {
            // Verander de array board, zodat een steen van player in column terecht komt.
            int row = 0;
            while (row<NUMBER_OF_ROWS-1 && this.get(column,row) == NONE) {
                row++;
            }
            if (row == NUMBER_OF_ROWS-1 && this.get(column,row) == NONE) {
                this.board[column][row] = player;
            } else {
                this.board[column][row-1] = player;
            }
        } else {
            throw new IllegalStateException("Invalid move");
        }
    }

    public Board doMove(int player, int column) {
        return new Board(this, player, column);
    }

    // getter voor een steen op bepaalde positie
    public int get(int column, int row) {
        return board[column][row];
    }

    // kan in de kolom een steen worden geworpen?
    public boolean isValidMove(int column) {
        int check = this.get(column,0);
        return check == NONE;
    }

    // is het spel afgelopen?
    public boolean isFinished() {
        for (int col = 0; col < NUMBER_OF_COLS; col++) {
            for (int row = 0; row < NUMBER_OF_ROWS; row++) {
                if (this.get(col,row) == NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    // heeft player gewonnen?
    public boolean isWinner(int player) {
        int n = 0;

        //vertical win
        for (int col = 0; col < NUMBER_OF_COLS-1; col++) {
            for (int row = 0; row < NUMBER_OF_ROWS-3; row++) {
                // vertical win
                for (int i = row;i<row+4;i++) {
                    if (get(col,NUMBER_OF_ROWS-1-i)==player) {
                        n++;
                    }
                }
                if (n == 4) {
                    return true;
                } else {
                    n = 0;
                }
            }
        }

        // horizontal win
        for (int col = 0; col < NUMBER_OF_COLS-3; col++) {
            for (int row = 0; row < NUMBER_OF_ROWS; row++) {
                // horizontal win
                for (int i = col;i<col+4;i++) {
                    if (get(i,row)==player) {
                        n++;
                    }
                }
                if (n == 4) {
                    return true;
                } else {
                    n = 0;
                }
            }
        }

//      // diagonal up win
        for (int col = 0; col < NUMBER_OF_COLS-3; col++) {
            for (int row = NUMBER_OF_ROWS-1; row > 2 ; row--) {
                if (get(col,row) == player && get(col+1,row-1) == player && get(col+2,row-2) == player && get(col+3,row-3) == player){
                    return true;
                }
            }
        }

        // diagonal down win
        for (int col = 0; col < NUMBER_OF_COLS-3; col++) {
            for (int row = 0; row < NUMBER_OF_ROWS-3; row++) {
                if (get(col,row) == player && get(col+1,row+1) == player && get(col+2,row+2) == player && get(col+3,row+3) == player){
                    return true;
                }
            }
        }

        return false;

    }

}
