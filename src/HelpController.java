public class HelpController extends Controller {

    public HelpController(Controller controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        displayPanel = new HelpDisplayPanel(this);
        displayPanel.initialize();
    }

    @Override
    public void react(Button button) {
        controller.react(button);
    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING, NOT_HOVERING -> controller.react(command, object);
        }
    }
}
