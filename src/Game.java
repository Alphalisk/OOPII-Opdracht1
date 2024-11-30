import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.random;

public class Game implements Subject{

    private Board currentBoard;
    private int currentPlayer;

    // User-interface
    private Scanner scanner = new Scanner(System.in);

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(currentBoard);
        }
    }

    @Override
    public void notifyWinObservers(String result) {
        for(Observer observer : observers) {
            observer.endGame(result);
        }
    }

    private void finishTurn() {
        if (currentPlayer==Board.PLAYER1) {
            currentPlayer=Board.PLAYER2;
        } else if (currentPlayer==Board.PLAYER2) {
            currentPlayer=Board.PLAYER1;
        } else {
            throw new IllegalStateException("Invalid current player");
        }
    }

    public void doMove(int column) {
        currentBoard=currentBoard.doMove(currentPlayer, column);

        // controleren of een player wint of het bord vol is
        if (currentBoard.isWinner(Board.PLAYER1)) {
            notifyObservers();
            notifyWinObservers("Speler 1 (COMPUTER) heeft gewonnen!");
            currentPlayer = Board.NONE;
        } else if (currentBoard.isWinner(Board.PLAYER2)) {
            notifyObservers();
            notifyWinObservers("Speler 2 (MENS) heeft gewonnen!");
            currentPlayer = Board.NONE;
        }
        if (!currentBoard.isWinner(Board.PLAYER1) && !currentBoard.isWinner(Board.PLAYER2) && (currentBoard.isFinished())) {
            notifyObservers();
            notifyWinObservers("Er is geen winnaar.");
            currentPlayer = Board.NONE;
        }

        if (currentPlayer != Board.NONE) {
            finishTurn();
            notifyObservers();
        }

        // computer aan de beurt
        if (currentPlayer==Board.PLAYER1) {
            doTurnComputer();
        }
    }

    private void doTurnComputer() {
        int column= (int)(Math.round(random()*7));
        while(!currentBoard.isValidMove(column)) { column= (int)(Math.round(random()*7)); }
        if (column<Board.NUMBER_OF_COLS) {
            doMove(column);
        } else {
            throw new IllegalStateException("No valid move possible");
        }
    }

    private void doTurnHuman() {
        System.out.print("Jij bent aan de beurt. In welke kolom wil je de steen werpen? (1-"+Board. NUMBER_OF_COLS+")");
        int column = Integer.parseInt(scanner.nextLine().trim())-1;
        doMove(column);
    }

    public boolean isTurnHuman() {
        return (currentPlayer==Board.PLAYER2);
    }

    public void runGame() {
        currentBoard=new Board();
        currentPlayer=Board.PLAYER1;
        doTurnComputer();
    }

}
