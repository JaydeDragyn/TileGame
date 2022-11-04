public class TileGameController extends Controller {

    private DisplayPanel tileGameDisplayPanel;

    public void initialize() {
        tileGameDisplayPanel = new TileGameDisplayPanel();
        tileGameDisplayPanel.initialize(this);
    }

    public DisplayElement getDisplayPanel() {
        return tileGameDisplayPanel;
    }

}
