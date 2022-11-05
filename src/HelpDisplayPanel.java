import java.awt.*;

public class HelpDisplayPanel extends DisplayPanel {

    private ActionButton prevButton;
    private ActionButton backButton;
    private ActionButton nextButton;

    public HelpDisplayPanel(Controller controller) {
        super(DisplayPanelID.HELP, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        prevButton = new ActionButton(ButtonID.BUTTON_PREV, new Point(25, 425), controller);
        backButton = new ActionButton(ButtonID.BUTTON_BACK, new Point(187, 425), controller);
        nextButton = new ActionButton(ButtonID.BUTTON_NEXT, new Point(350, 425), controller);



        addElement(prevButton);
        addElement(backButton);
        addElement(nextButton);

    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING -> controller.react(Command.HOVERING, object);
            case NOT_HOVERING -> controller.react(Command.NOT_HOVERING, object);
        }
    }
}
