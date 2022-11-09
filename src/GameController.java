/*
    class GameController

    This controller interfaces between the GameState (which is where the
    actual game logic lives) and the GameDisplayPanel that the user is
    interacting with.

 */

public class GameController extends Controller {

    private final TileGameController tileGameController;
    private GameDisplayPanel gameDisplayPanel;
    private GameState gameState;

    public GameController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    public void initialize() {
        gameDisplayPanel = new GameDisplayPanel(this);
        gameDisplayPanel.initialize();
        gameState = new GameState();
    }

    public DisplayPanel getDisplayPanel() {
        return gameDisplayPanel;
    }

    // When the user hovers over a tile, tell the GameDisplayPanel to
    // expand the hover highlight to the neighboring tiles (GameState
    // provides the list of neighbors, we're just the messenger)
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

    // When the user presses on a tile (before they lift the mouse button to
    // complete the "click"), tell GameDisplayPanel to expand which tiles are
    // lit up to the neighboring tiles (GameState provides the list of
    // neighbors, we're just the messenger)
    @Override
    public void press(Button button) {
        if (!gameState.isGameActive()) { return; }
        Tile tile = (Tile) button;
        gameDisplayPanel.expandPress(gameState.getNeighbors(tile.getIndex()));
    }

    // Go through with the actual "click" to have GameState calculate the new
    // tile colors, pass that info to GameDisplayPanel, and then check to see
    // if the user won the game.  Tell everyone if they did and also tell the
    // GameDisplayPanel to show the win condition.
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

    // TileGameController gets the notification when the user hovers over
    // a tile in the InfoDisplayPanel and tells us which one.  We then
    // pass that to the GameDisplayController to have it dim tiles
    // (this is meant to help the user see which tiles are the same color
    // as the Info tile they are hovering on.
    public void hoveringInfoTile(Tile tile) {
        gameDisplayPanel.dimAllExcept(tile);
    }

    // Same as hoveringInfoTile, except the user pressed the button on the
    // tile, so we make everything disappear except the color they are
    // pressing, so it is even more obvious.  Some tile colors are not as
    // prominent even when the others are dimmed, so this is meant to give
    // the user a more obvious way to see the tiles.
    public void pressingInfoTile(Tile tile) {
        gameDisplayPanel.disableAllExcept(tile);
    }
}
