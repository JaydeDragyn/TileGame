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
        }
    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            // Button hovering (other controllers just pass these through)
            case HOVERING -> displayPanel.react(Command.HOVERING, object);
            case NOT_HOVERING -> displayPanel.react(Command.NOT_HOVERING, object);

            case START_NEW_GAME -> startNewGame((GameSettings) object);
            case RETURN_TO_GAME -> showGame();
        }
    }

    private void showMenu() {
        displayPanel.react(Command.SET_STATE, menuController.getDisplayPanel());
    }

    private void showHelp() {
        displayPanel.react(Command.SET_STATE, helpController.getDisplayPanel());
        // if user was in Quit or New Game confirmation and clicked Help
        menuController.react(Command.RESET, null);
    }

    private void showGame() {
        displayPanel.react(Command.SET_STATE, gameController.getDisplayPanel());
    }

    private void startNewGame(GameSettings gameSettings) {
        gameController.react(Command.START_NEW_GAME, gameSettings);
        showGame();
    }
}
