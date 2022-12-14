/*
    class MenuController

    This controller interfaces between the MenuState (which keeps track of
    what the user currently has selected for when a new game is started),
    and the MenuDisplayPanel that the user is interacting with.

 */

public class MenuController extends Controller {

    private final TileGameController tileGameController;
    private MenuDisplayPanel menuDisplayPanel;
    private MenuState menuState;

    public MenuController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    public void initialize() {
        menuDisplayPanel = new MenuDisplayPanel(this);
        menuDisplayPanel.initialize();

        menuState = new MenuState();
        menuState.initialize();

        setGameMode(GameSettings.Difficulty.EASY);
        setBoardSize(GameSettings.BoardSize.SMALL_3X3);
    }

    public DisplayPanel getDisplayPanel() {
        return menuDisplayPanel;
    }

    @Override
    public void hover(Button button) {
        switch (button.getCommand()) {
            case CYCLE_EASY_TILE ->
                    tileGameController.showHoverInfo(
                            button.getHoverText(),
                            menuState.getProgression(GameSettings.Difficulty.EASY));
            case CYCLE_MEDIUM_TILE ->
                    tileGameController.showHoverInfo(
                            button.getHoverText(),
                            menuState.getProgression(GameSettings.Difficulty.MEDIUM));
            case CYCLE_HARD_TILE ->
                    tileGameController.showHoverInfo(
                            button.getHoverText(),
                            menuState.getProgression(GameSettings.Difficulty.HARD));
            default ->
                    tileGameController.showHoverInfo(button.getHoverText(), null);
        }
    }

    @Override
    public void unhover() {
        tileGameController.unhover();
    }

    @Override
    public void press(Button button) {
        // No press responses to menu items
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

    // The menu may put up a confirmation dialog to confirm the user wants to
    // start a new game while one is in progress, or to confirm the user
    // really wants to quit.  .reset() is used if the user declines either of
    // those confirmations.  If causes the Menu to display the settings
    // elements again so the user can interact with them.
    // This also is used so that if the user gets a confirmation dialog but
    // clicks the Help button instead of Yes or No, the menu is reset so when
    // (if) they go back to the Menu it shows the settings elements instead of
    // the confirmation dialog.
    public void reset() {
        menuDisplayPanel.reset();
    }

    public void gameStarted() {
        menuState.gameStarted();
        menuDisplayPanel.gameStarted();
    }

    public void gameEnded() {
        menuState.gameEnded();
        menuDisplayPanel.gameEnded();
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
        tile.setColor(menuState.cycleProgressionTile(difficulty, Integer.parseInt(tile.getName())));
        hover(tile);
    }

    // If there's no game in progress then we just send the message to start a
    // new game.  Otherwise, we tell the MenuDisplayPanel to confirm that the
    // user wants to abandon the game in progress to start a new one.
    private void startNewGame() {
        if (menuState.isGameInProgress()) {
            menuDisplayPanel.confirmStartNewGame();
        } else {
            tileGameController.startNewGame(menuState.getNewGameSettings());
        }
    }
}
