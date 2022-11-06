import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileGameDisplayPanel extends DisplayPanel {

    private Button menuButton;
    private Button helpButton;
    private DisplayPanel centerDisplayPanel;
    private InfoDisplayPanel infoDisplayPanel;

    public TileGameDisplayPanel(Controller controller) {
        super(DisplayPanelID.TILE_GAME, new Point(0,0), controller);
        size = new Dimension(800, 800);
        texture = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();
    }

    public void initialize() {
        infoDisplayPanel = new InfoDisplayPanel(controller);
        infoDisplayPanel.initialize();

        menuButton = new ActionButton(ButtonID.BUTTON_MENU, new Point(25, 20), controller);
        helpButton = new ActionButton(ButtonID.BUTTON_HELP, new Point(675, 20), controller);

        addElement(new StaticImage("Background", new Point(0,0)));
        addElement(new StaticImage("Tile Game", new Point(225, 22)));
        addElement(menuButton);
        addElement(helpButton);
        addElement(infoDisplayPanel);
    }

    public void setState(DisplayPanel displayPanel) {
        removeElement(this.centerDisplayPanel);
        this.centerDisplayPanel = displayPanel;
        addElement(this.centerDisplayPanel);

        switch (displayPanel.getDisplayPanelID()) {
            case MENU -> {
                menuButton.disable();
                helpButton.enable();
            }
            case GAME -> {
                menuButton.enable();
                helpButton.enable();
            }
            case HELP -> {
                menuButton.enable();
                helpButton.disable();
            }
        }
    }

    public void showHoverInfo(String hoverText, ArrayList<TileColor> tileColors) {
        infoDisplayPanel.showHoverInfo(hoverText, tileColors);
    }

    public void clearHoverInfo() {
        infoDisplayPanel.showHoverInfo(null, null);
    }

    public void gameStarted(GameSettings gameSettings) {
        infoDisplayPanel.gameStarted(gameSettings);
    }
}
