public class HelpController extends Controller {

    public HelpController(Controller controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        displayPanel = new HelpDisplayPanel();
        displayPanel.initialize(this);
    }

    @Override
    public void react(Button button) {
        controller.react(button);
    }

    @Override
    public void react(Command command, Object object) {

    }
}
