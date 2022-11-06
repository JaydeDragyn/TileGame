public class HelpController extends Controller {

    private final TileGameController tileGameController;
    private DisplayPanel helpDisplayPanel;

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
    public void react(Button button) {
        tileGameController.react(button);
    }

    @Override
    public void hover(Button button) {
        tileGameController.hover(button);
    }

    @Override
    public void unhover() {
        tileGameController.unhover();
    }

    public void gameStarted() {

    }

    public void gameEnded() {

    }
}
