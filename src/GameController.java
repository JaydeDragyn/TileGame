public class GameController extends Controller {

    private final TileGameController tileGameController;
    private GameDisplayPanel gameDisplayPanel;
    private GameState gameState;

    public GameController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    @Override
    public void initialize() {
        gameDisplayPanel = new GameDisplayPanel(this);
        gameDisplayPanel.initialize();

        gameState = new GameState();
    }

    @Override
    public DisplayPanel getDisplayPanel() {
        return gameDisplayPanel;
    }

    @Override
    public void hover(Button button) {
        gameDisplayPanel.expandHover((Tile) button);
    }

    @Override
    public void unhover() {
        gameDisplayPanel.clearAll();
    }

    @Override
    public void press(Button button) {
        gameDisplayPanel.expandPress((Tile) button);
    }

    @Override
    public void react(Button button) {
        gameDisplayPanel.clearAll();
    }

    public void startNewGame(GameSettings gameSettings) {
        gameState.newGame(gameSettings);
        TileColor[][] colors = gameState.getTiles();
        gameDisplayPanel.newGame(gameSettings, colors);
    }
}
