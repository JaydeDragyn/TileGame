import java.awt.*;
import java.awt.image.BufferedImage;

public class TileGameDisplayPanel extends DisplayPanel {

    private Button menuButton;
    private Button helpButton;
    private DisplayPanel centerDisplayPanel;
    private DisplayPanel infoDisplayPanel;

    public TileGameDisplayPanel(Controller controller) {
        super("Tile Game Display Panel", new Point(0,0), controller);
        size = new Dimension(800, 800);
        texture = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();
    }

    public void initialize() {
        infoDisplayPanel = new InfoDisplayPanel(controller);
        infoDisplayPanel.initialize();

        menuButton = new ActionButton("Menu", new Point(25, 20), Command.SHOW_MENU, controller);
        helpButton = new ActionButton("Help", new Point(675, 20), Command.SHOW_HELP, controller);

        addElement(new StaticImage("Background", new Point(0,0)));
        addElement(new StaticImage("Tile Game", new Point(225, 22)));
        addElement(menuButton);
        addElement(helpButton);
        addElement(infoDisplayPanel);
    }

    public void react (Command command, Object object) {
        switch (command) {
            case SET_STATE -> setState((DisplayPanel) object);
            case HOVERING -> hover((Button) object);
            case NOT_HOVERING -> hover(null);
        }
    }

    private void setState(DisplayPanel displayPanel) {
        removeElement(this.centerDisplayPanel);
        this.centerDisplayPanel = displayPanel;
        addElement(this.centerDisplayPanel);

        switch (displayPanel.getName()) {
            case "Menu Display Panel" -> {
                menuButton.disable();
                helpButton.enable();
            }
            case "Game Display Panel" -> {
                menuButton.enable();
                helpButton.enable();
            }
            case "Help Display Panel" -> {
                menuButton.enable();
                helpButton.disable();
            }
        }

        infoDisplayPanel.react(Command.NOT_HOVERING, null);
    }

    private void hover(Button button) {
        if (button == null) {
            infoDisplayPanel.react(Command.NOT_HOVERING, null);
        } else {
            infoDisplayPanel.react(Command.HOVERING, button.getName());
        }
    }


}
