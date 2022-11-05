public class GameController extends Controller {

    public GameController(Controller controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        displayPanel = new GameDisplayPanel(this);
        displayPanel.initialize();
    }

    @Override
    public void react(Button button) {

    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case START_NEW_GAME -> startNewGame((GameSettings) object);
        }
    }

    private void startNewGame(GameSettings gameSettings) {
        controller.react(Command.GAME_STARTED, null);
        System.out.println("Starting new game with settings:");
        System.out.println(gameSettings.progression());
        System.out.println(gameSettings.boardSize());
    }
}
