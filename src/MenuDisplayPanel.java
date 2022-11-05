import java.awt.*;

public class MenuDisplayPanel extends DisplayPanel {

    private ActionButton startButton;
    private ActionButton backButton;
    private ActionButton quitButton;

    public MenuDisplayPanel(Controller controller) {
        super("Menu Display Panel", new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        startButton = new ActionButton("Start", new Point(25, 425), Command.START_NEW_GAME, controller);
        backButton = new ActionButton("Back", new Point(187, 425), Command.RETURN_TO_GAME, controller);
        quitButton = new ActionButton("Quit", new Point(350, 425), Command.QUIT_GAME, controller);

        addElement(new TextLabel("Settings Menu", new Point(127, 0), TextLabel.FontSize.LARGE, new Color(0.5f, 1.0f, 0.5f)));

        addElement(startButton);
        addElement(backButton);
        addElement(quitButton);

    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING -> controller.react(Command.HOVERING, object);
            case NOT_HOVERING -> controller.react(Command.NOT_HOVERING, object);
        }
    }
}
