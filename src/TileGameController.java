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

    @Override
    public DisplayPanel getDisplayPanel() {
        return tileGameDisplayPanel;
    }

    @Override
    public void hover(Button button) {
        switch (button.getCommand()) {
            case SHOW_MENU, SHOW_HELP -> tileGameDisplayPanel.showHoverInfo(button.getHoverText(), null);
            case HOVER_TILE -> gameController.hoveringInfoTile((Tile) button);
        }
    }

    @Override
    public void unhover() {
        tileGameDisplayPanel.clearHoverInfo();
        gameController.unhover();
    }

    @Override
    public void press(Button button) {
        if (button.getCommand() == Command.HOVER_TILE) {
            gameController.pressingInfoTile((Tile) button);
        }
    }

    @Override
    public void react(Button button) {
        menuController.reset();
        switch (button.getCommand()) {
            case SHOW_MENU -> tileGameDisplayPanel.setState(menuController.getDisplayPanel());
            case SHOW_HELP -> tileGameDisplayPanel.setState(helpController.getDisplayPanel());
        }
    }

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
