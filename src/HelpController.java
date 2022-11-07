public class HelpController extends Controller {

    private final TileGameController tileGameController;
    private HelpDisplayPanel helpDisplayPanel;

    public HelpController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    @Override
    public DisplayPanel getDisplayPanel() {
        return helpDisplayPanel;
    }

    @Override
    public void initialize() {
        helpDisplayPanel = new HelpDisplayPanel(this);
        helpDisplayPanel.initialize();
    }

    @Override
    public void hover(Button button) {
        tileGameController.hover(button);
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
            case PREV_HELP_PAGE -> helpDisplayPanel.showPrevPage();
            case NEXT_HELP_PAGE -> helpDisplayPanel.showNextPage();
            case RETURN_TO_GAME -> tileGameController.showGame();
        }
    }

    public void gameStarted() {
        helpDisplayPanel.gameStarted();
    }

    public void gameEnded() {
        helpDisplayPanel.gameEnded();
    }
}
