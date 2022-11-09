/*
    class TileGameController

    This is the big-boss of the program.  It creates the other controllers and
    switches contexts based on Button interaction (like SHOW_MENU) or
    instructions from another controller (like the MenuController calling for a
    new game to start when the user clicks the Start button).

 */
import java.util.ArrayList;

public class TileGameController extends Controller {

    private TileGameDisplayPanel tileGameDisplayPanel;

    private MenuController menuController;
    private GameController gameController;
    private HelpController helpController;

    public void initialize() {
        tileGameDisplayPanel = new TileGameDisplayPanel(this);
        tileGameDisplayPanel.initialize();

        menuController = new MenuController(this);
        menuController.initialize();

        gameController = new GameController(this);
        gameController.initialize();

        helpController = new HelpController(this);
        helpController.initialize();

        tileGameDisplayPanel.setState(menuController.getDisplayPanel());
    }

    public DisplayPanel getDisplayPanel() {
        return tileGameDisplayPanel;
    }

    // Response to hovering over the Menu and Help buttons, or the
    // InfoDisplayPanel elements while the game is being shown.
    @Override
    public void hover(Button button) {
        switch (button.getCommand()) {
            case SHOW_MENU, SHOW_HELP -> tileGameDisplayPanel.showHoverInfo(button.getHoverText(), null);
            case HOVER_TILE -> gameController.hoveringInfoTile((Tile) button);
        }
    }

    // User is no longer hovering over any elements.
    @Override
    public void unhover() {
        tileGameDisplayPanel.clearHoverInfo();
        gameController.unhover();
    }

    // User has pressed a tile in the InfoDisplayPanel but has not yet released
    // it.  No other buttons do anything when pressed, only when released for
    // the complete "click".
    @Override
    public void press(Button button) {
        if (button.getCommand() == Command.HOVER_TILE) {
            gameController.pressingInfoTile((Tile) button);
        }
    }

    // User has completed a "click" on the Menu or Help button
    @Override
    public void react(Button button) {
        menuController.reset();
        switch (button.getCommand()) {
            case SHOW_MENU -> tileGameDisplayPanel.setState(menuController.getDisplayPanel());
            case SHOW_HELP -> tileGameDisplayPanel.setState(helpController.getDisplayPanel());
        }
    }

    // Direct TileGamedisplayPanel to show a button's hover text on the
    // Info Panel, or show a tile's hover text and progression context
    public void showHoverInfo(String hoverText, ArrayList<TileColor> tileColors) {
        tileGameDisplayPanel.showHoverInfo(hoverText, tileColors);
    }

    public void startNewGame(GameSettings gameSettings) {
        gameController.startNewGame(gameSettings);
        menuController.gameStarted();
        helpController.gameStarted();
        tileGameDisplayPanel.gameStarted(gameSettings);
        showGame();
    }

    public void gameEnded() {
        menuController.gameEnded();
        helpController.gameEnded();
    }

    public void showGame() {
        menuController.reset();
        tileGameDisplayPanel.setState(gameController.getDisplayPanel());
    }
}
