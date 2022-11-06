public class GameController extends Controller {

    private final TileGameController tileGameController;
    private GameDisplayPanel gameDisplayPanel;

    public GameController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    @Override
    public void initialize() {
        gameDisplayPanel = new GameDisplayPanel(this);
        gameDisplayPanel.initialize();
    }

    @Override
    public DisplayPanel getDisplayPanel() {
        return gameDisplayPanel;
    }

    @Override
    public void react(Button button) {

    }

    @Override
    public void hover(Button button) {

    }

    @Override
    public void unhover() {

    }

    public void startNewGame(GameSettings gameSettings) {
        System.out.println("Starting new game with settings:");
        System.out.println(gameSettings.progression());
        System.out.println(gameSettings.boardSize());
    }
}
