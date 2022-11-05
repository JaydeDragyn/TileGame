public class MenuController extends Controller {

    public MenuController(Controller controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        displayPanel = new MenuDisplayPanel(this);
        displayPanel.initialize();
    }

    @Override
    public void react(Button button) {
        controller.react(button);
    }

    @Override
    public void react(Command command, Object object) {

    }
}
