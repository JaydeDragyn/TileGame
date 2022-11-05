import java.awt.*;
import java.awt.image.BufferedImage;

public class TileGameDisplayPanel extends DisplayPanel {

    private Button menuButton;
    private Button helpButton;
    private DisplayPanel centerDisplayPanel;

    public TileGameDisplayPanel(Controller controller) {
        super("Tile Game Display Panel", new Point(0,0), controller);
        size = new Dimension(800, 800);
        texture = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();
    }

    public void initialize() {
        menuButton = new ActionButton("Menu", new Point(25, 20), Command.SHOW_MENU, controller);
        helpButton = new ActionButton("Help", new Point(675, 20), Command.SHOW_HELP, controller);

        addElement(new StaticImage("Background", new Point(0,0)));
        addElement(new StaticImage("Tile Game", new Point(225, 22)));
        addElement(menuButton);
        addElement(helpButton);
    }

    public void setState(DisplayPanel displayPanel) {
        setCenterDisplayPanel(displayPanel);

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
    }

    private void setCenterDisplayPanel(DisplayPanel centerDisplayPanel) {
        removeElement(this.centerDisplayPanel);
        this.centerDisplayPanel = centerDisplayPanel;
        addElement(this.centerDisplayPanel);
    }

}
