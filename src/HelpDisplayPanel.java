import java.awt.*;

public class HelpDisplayPanel extends DisplayPanel {

    public HelpDisplayPanel(Controller controller) {
        super("Help Display Panel", new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        addElement(new ActionButton("Help", new Point(200, 200), Command.SHOW_GAME, controller));
    }
}
