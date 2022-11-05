public class TileGameController extends Controller {

    private Controller menuController;
    private Controller gameController;
    private Controller helpController;

    public TileGameController() {
        super(null);
    }

    public void initialize() {
        displayPanel = new TileGameDisplayPanel(this);
        displayPanel.initialize();

        menuController = new MenuController(this);
        menuController.initialize();

        gameController = new GameController(this);
        gameController.initialize();

        helpController = new HelpController(this);
        helpController.initialize();

        displayPanel.react(Command.SET_STATE, menuController.getDisplayPanel());
    }

    @Override
    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    @Override
    public void react(Button button) {
        Command command = button.getButtonID().getCommand();
        // react to a button press
        switch (command) {
            case SHOW_MENU -> showMenu();
            case SHOW_HELP -> showHelp();
            case RETURN_TO_GAME -> showGame();
        }
    }

    @Override
    public void react(Command command, Object object) {
        // react to another controller that may provide additional info
        // (like GameSettings for a new game)
        switch (command) {
            case HOVERING -> displayPanel.react(Command.HOVERING, object);
            case NOT_HOVERING -> displayPanel.react(Command.NOT_HOVERING, object);
        }
    }

    private void showMenu() {
        displayPanel.react(Command.SET_STATE, menuController.getDisplayPanel());
    }

    private void showHelp() {
        displayPanel.react(Command.SET_STATE, helpController.getDisplayPanel());
    }

    private void showGame() {
        displayPanel.react(Command.SET_STATE, gameController.getDisplayPanel());
    }
}
