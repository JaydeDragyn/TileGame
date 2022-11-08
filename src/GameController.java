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
        if (!gameState.isGameActive()) { return; }
        Tile tile = (Tile) button;
        gameDisplayPanel.expandHover(gameState.getNeighbors(tile.getIndex()));
    }

    @Override
    public void unhover() {
        if (gameState.isGameActive()) {
            gameDisplayPanel.clearAll();
        }
    }

    @Override
    public void press(Button button) {
        if (!gameState.isGameActive()) { return; }
        Tile tile = (Tile) button;
        gameDisplayPanel.expandPress(gameState.getNeighbors(tile.getIndex()));
    }

    @Override
    public void react(Button button) {
        if (!gameState.isGameActive()) { return; }
        Tile tile = (Tile) button;
        gameState.cycleTiles(tile.getIndex());
        gameDisplayPanel.updateTileColors(gameState.getTiles());
        gameDisplayPanel.clearAll();
        if (gameState.winState()) {
            gameDisplayPanel.showWin();
            tileGameController.gameEnded();
        }
    }

    public void startNewGame(GameSettings gameSettings) {
        gameState.newGame(gameSettings);
        gameDisplayPanel.newGame(gameSettings, gameState.getTiles());
    }

    public void hoveringInfoTile(Tile tile) {
        gameDisplayPanel.dimAllExcept(tile);
    }

    public void pressingInfoTile(Tile tile) {
        gameDisplayPanel.disableAllExcept(tile);
    }
}
