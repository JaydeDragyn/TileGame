import java.awt.*;

public class MenuDisplayPanel extends DisplayPanel {

    public MenuDisplayPanel(Controller controller) {
        super("Menu Display Panel", new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        addElement(new ActionButton("Menu", new Point(200, 200), Command.SHOW_GAME, controller));
    }
}
