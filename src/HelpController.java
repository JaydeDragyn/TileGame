/*
    class HelpController

    This controller responds to user input to either go back to the game in
    progress (if one is in progress), or to direct the HelpDisplayPanel to
    show the next/previous page of help.  There is no need here for another
    class to keep track of which page is being displayed - we'll let the
    HelpDisplayPanel keep track of that and just tell it what the user is
    asking for.

 */

public class HelpController extends Controller {

    private final TileGameController tileGameController;
    private HelpDisplayPanel helpDisplayPanel;

    public HelpController(TileGameController tileGameController) {
        this.tileGameController = tileGameController;
    }

    public DisplayPanel getDisplayPanel() {
        return helpDisplayPanel;
    }

    public void initialize() {
        helpDisplayPanel = new HelpDisplayPanel(this);
        helpDisplayPanel.initialize();
    }

    @Override
    public void hover(Button button) {
        tileGameController.showHoverInfo(button.getHoverText(), null);
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
