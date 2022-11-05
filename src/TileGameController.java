public class TileGameController extends Controller {

    private TileGameDisplayPanel tileGameDisplayPanel;
    private Controller menuController;
    private Controller gameController;
    private Controller helpController;

    public TileGameController() {
        super(null);
    }

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
        // react to a button press
        switch (button.getCommand()) {
            case SHOW_MENU -> showMenu();
            case SHOW_HELP -> showHelp();
            case SHOW_GAME -> showGame();
        }
    }

    @Override
    public void react(Command command, Object object) {
        // react to another controller that may provide additional info
        // (like GameSettings for a new game)
    }

    private void showMenu() {
        tileGameDisplayPanel.setState(menuController.getDisplayPanel());
    }

    private void showHelp() {
        tileGameDisplayPanel.setState(helpController.getDisplayPanel());
    }

    private void showGame() {
        tileGameDisplayPanel.setState(gameController.getDisplayPanel());
    }
}
