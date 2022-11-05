import java.awt.*;

public class GameDisplayPanel extends DisplayPanel {

    public GameDisplayPanel(Controller controller) {
        super(DisplayPanelID.GAME, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        addElement(new StaticImage("Tile Game", new Point(75, 200)));
    }

    @Override
    public void react(Command command, Object object) {

    }
}
