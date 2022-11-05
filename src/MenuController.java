import java.util.ArrayList;

public class MenuController extends Controller {

    private MenuState menuState;
    private ArrayList<DisplayElement> tempElementList;

    public MenuController(Controller controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        displayPanel = new MenuDisplayPanel(this);
        displayPanel.initialize();

        menuState = new MenuState();
        menuState.initialize();

        setGameMode(GameSettings.Difficulty.EASY);
        setBoardSize(GameSettings.BoardSize.SMALL_3X3);
    }

    @Override
    public void react(Button button) {
        switch (button.getCommand()) {
            // Difficulty Radio Buttons
            case GAME_MODE_EASY -> setGameMode(GameSettings.Difficulty.EASY);
            case GAME_MODE_MEDIUM -> setGameMode(GameSettings.Difficulty.MEDIUM);
            case GAME_MODE_HARD -> setGameMode(GameSettings.Difficulty.HARD);

            // Board Size Radio Buttons
            case BOARD_SIZE_3X3 -> setBoardSize(GameSettings.BoardSize.SMALL_3X3);
            case BOARD_SIZE_3X5 -> setBoardSize(GameSettings.BoardSize.MEDIUM_3X5);
            case BOARD_SIZE_5X5 -> setBoardSize(GameSettings.BoardSize.LARGE_5X5);

            // Tile progression changes
            case CYCLE_EASY_TILE -> cycleProgressionTile((Tile) button, GameSettings.Difficulty.EASY);
            case CYCLE_MEDIUM_TILE -> cycleProgressionTile((Tile) button, GameSettings.Difficulty.MEDIUM);
            case CYCLE_HARD_TILE -> cycleProgressionTile((Tile) button, GameSettings.Difficulty.HARD);

            // Action Buttons
            case START_NEW_GAME -> controller.react(Command.START_NEW_GAME, menuState.getNewGameSettings());
            case RETURN_TO_GAME -> controller.react(Command.RETURN_TO_GAME, null);
            case QUIT_GAME -> displayPanel.react(Command.CONFIRM_QUIT, null);

            // Quit confirmation
            case YES_QUIT -> System.exit(0);
            case NO_QUIT -> displayPanel.react(Command.NO_QUIT, null);
        }
    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING, NOT_HOVERING -> controller.react(command, object);

        }
    }

    private void setGameMode(GameSettings.Difficulty difficulty) {
        displayPanel.react(Command.SET_GAME_MODE, difficulty);
        menuState.setGameMode(difficulty);
    }

    private void setBoardSize(GameSettings.BoardSize boardSize) {
        displayPanel.react(Command.SET_BOARD_SIZE, boardSize);
        menuState.setBoardSize(boardSize);
    }

    private void cycleProgressionTile(Tile tile, GameSettings.Difficulty difficulty) {
        TileColor newColor = menuState.cycleProgressionTile(difficulty, Integer.parseInt(tile.getName()));
        tile.setColor(newColor);
        controller.react(Command.HOVERING, tile);
    }

    private void confirmQuit() {

    }

    private void notQuitting() {

    }
}
