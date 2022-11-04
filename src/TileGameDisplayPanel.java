import java.awt.*;
import java.awt.image.BufferedImage;

public class TileGameDisplayPanel extends DisplayPanel {

    private Controller controller;
    private BufferedImage texture;
    private Graphics2D pen;
    private StaticImage background;
    private StaticImage title;
    private Button menuButton;
    private Button helpButton;
    private DisplayPanel centerDisplayPanel;


    public TileGameDisplayPanel() {
        super(ElementID.TILE_GAME_DISPLAY_PANEL, new Point(0,0), new Dimension(800, 800));
        isInteractive = true;
    }

    public BufferedImage getTexture() {
        drawElement(pen, background);
        drawElement(pen, title);
        drawElement(pen, menuButton);
        drawElement(pen, helpButton);
        drawElement(pen, centerDisplayPanel);

        return texture;
    }

    public void initialize(Controller controller) {
        this.controller = controller;

        texture = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();

        background = new StaticImage(ElementID.STATIC_IMAGE, new Point(0,0), "Background");
        title = new StaticImage(ElementID.STATIC_IMAGE, new Point(225, 22), "Tile Game");
        menuButton = new Button(ElementID.BUTTON, new Point(25, 20), new Dimension(100, 100), "Menu", Command.SHOW_MENU, this.controller);
        helpButton = new Button(ElementID.BUTTON, new Point(675, 20), new Dimension(100, 100), "Help", Command.SHOW_HELP, this.controller);

        addElement(background);
        addElement(title);
        addElement(menuButton);
        addElement(helpButton);
    }

    public void setState(DisplayPanel displayPanel) {
        setCenterDisplayPanel(displayPanel);

        switch (displayPanel.getID()) {
            case MENU_DISPLAY_PANEL -> {
                menuButton.disable();
                helpButton.enable();
            }
            case GAME_DISPLAY_PANEL -> {
                menuButton.enable();
                helpButton.enable();
            }
            case HELP_DISPLAY_PANEL -> {
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
