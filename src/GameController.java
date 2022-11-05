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

    }
}
