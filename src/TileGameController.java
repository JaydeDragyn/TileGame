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
    public void react(Button button) {
        menuController.reset();
        switch (button.getCommand()) {
            case SHOW_MENU -> tileGameDisplayPanel.setState(menuController.getDisplayPanel());
            case SHOW_HELP -> tileGameDisplayPanel.setState(helpController.getDisplayPanel());
        }
    }

    @Override
    public void hover(Button button) {
        tileGameDisplayPanel.showHoverInfo(button.getHoverText(), null);
    }

    @Override
    public void unhover() {
        tileGameDisplayPanel.clearHoverInfo();
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

    public void showGame() {
        tileGameDisplayPanel.setState(gameController.getDisplayPanel());
    }
}
