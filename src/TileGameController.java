public class TileGameController extends Controller {

    private DisplayPanel tileGameDisplayPanel;

    public void initialize() {
        tileGameDisplayPanel = new TileGameDisplayPanel();
        tileGameDisplayPanel.initialize(this);
    }

    public DisplayElement getDisplayPanel() {
        return tileGameDisplayPanel;
    }

    @Override
    public void react(Button button) {
        // react to a button press
        switch (button.getCommand()) {
            case SHOW_MENU -> System.out.println("Show the Menu");
            case SHOW_HELP -> System.out.println("Show the Help");
        }

    }

    @Override
    public void react(Command command, Object object) {
        // react to another controller that may provide additional info
        // (like GameSettings for a new game)
    }


}
