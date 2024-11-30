public class GameController implements DrawListener {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(double x, double y) {
        int column=(int)(x/40);
        // Controleren bij game of het spel nog bezig is en de menselijke speler aan de beurt is
        // Als dit het geval is, doMove aanroepen.
        if (game.isTurnHuman()) {
            game.doMove(column);
        }
    }
}
