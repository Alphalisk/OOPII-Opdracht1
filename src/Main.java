public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Draw draw = new Draw();
        game.registerObserver(new ViewConsole());
        game.registerObserver(new ViewGraphics(draw));
        draw.addListener(new GameController(game));
        game.runGame();
    }
}
