import java.awt.*;

public class HelpDisplayPanel extends DisplayPanel {

    private ActionButton prevButton;
    private ActionButton backButton;
    private ActionButton nextButton;

    public HelpDisplayPanel(Controller controller) {
        super("Help Display Panel", new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        prevButton = new ActionButton("Prev", new Point(25, 425), Command.START_NEW_GAME, controller);
        backButton = new ActionButton("Back", new Point(187, 425), Command.RETURN_TO_GAME, controller);
        nextButton = new ActionButton("Next", new Point(350, 425), Command.QUIT_GAME, controller);



        addElement(prevButton);
        addElement(backButton);
        addElement(nextButton);

    }

    @Override
    public void react(Command command, Object object) {

    }
}
