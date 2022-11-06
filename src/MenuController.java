public class MenuController extends Controller {

    private final TileGameController tileGameController;
    private MenuDisplayPanel menuDisplayPanel;
    private MenuState menuState;

    public MenuController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    @Override
    public void initialize() {
        menuDisplayPanel = new MenuDisplayPanel(this);
        menuDisplayPanel.initialize();

        menuState = new MenuState();
        menuState.initialize();

        setGameMode(GameSettings.Difficulty.EASY);
        setBoardSize(GameSettings.BoardSize.SMALL_3X3);
    }

    @Override
    public DisplayPanel getDisplayPanel() {
        return menuDisplayPanel;
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
            case START_NEW_GAME -> startNewGame();
            case RETURN_TO_GAME -> tileGameController.showGame();
            case QUIT_GAME -> menuDisplayPanel.confirmQuit();

            // Confirmations
            case YES_START -> tileGameController.startNewGame(menuState.getNewGameSettings());
            case YES_QUIT -> System.exit(0);
            case NO_START, NO_QUIT -> menuDisplayPanel.reset();
        }
    }

    @Override
    public void hover(Button button) {
        switch (button.getCommand()) {
            case CYCLE_EASY_TILE ->
                tileGameController.showHoverInfo(button.getHoverText(), menuState.getProgression(GameSettings.Difficulty.EASY));
            case CYCLE_MEDIUM_TILE ->
                tileGameController.showHoverInfo(button.getHoverText(), menuState.getProgression(GameSettings.Difficulty.MEDIUM));
            case CYCLE_HARD_TILE ->
                tileGameController.showHoverInfo(button.getHoverText(), menuState.getProgression(GameSettings.Difficulty.HARD));
            default ->
                tileGameController.showHoverInfo(button.getHoverText(), null);
        }
    }

    @Override
    public void unhover() {
        tileGameController.unhover();
    }

    public void reset() {
        menuDisplayPanel.reset();
    }

    private void setGameMode(GameSettings.Difficulty difficulty) {
        menuState.setGameMode(difficulty);
        menuDisplayPanel.setGameMode(difficulty);
    }

    private void setBoardSize(GameSettings.BoardSize boardSize) {
        menuState.setBoardSize(boardSize);
        menuDisplayPanel.setBoardSize(boardSize);
    }

    private void cycleProgressionTile(Tile tile, GameSettings.Difficulty difficulty) {
        TileColor newColor = menuState.cycleProgressionTile(difficulty, Integer.parseInt(tile.getName()));
        tile.setColor(newColor);
        hover(tile);
    }

    private void startNewGame() {
        if (menuState.isGameInProgress()) {
            menuDisplayPanel.confirmStartNewGame();
        } else {
            tileGameController.startNewGame(menuState.getNewGameSettings());
        }
    }

    public void gameStarted() {
        menuState.gameStarted();
        menuDisplayPanel.gameStarted();
    }

    public void gameEnded() {
        menuState.gameEnded();
        menuDisplayPanel.gameEnded();
    }
}
