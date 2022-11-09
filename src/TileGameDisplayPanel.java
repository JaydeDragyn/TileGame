/*
    class TileGameDisplayPanel

    This is the top-level DisplayPanel.  It holds both the InfoDisplayPanel
    and a reference to one of the other DisplayPanels, depending on the
    current program state.  When the GameWindow gets the texture,
    this will use the default DisplayPanel procedure of calling for all
    of its elements current textures and drawing them where they belong,
    and then passing that texture to the GameWindow.  The result should
    always be a full program window size texture that blanks everything
    that was on screen previously.

 */

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
                menuButton.mouseMovedOff();
                menuButton.disable();
                helpButton.enable();
                infoDisplayPanel.gameHidden();
            }
            case GAME -> {
                menuButton.enable();
                helpButton.enable();
                infoDisplayPanel.gameShowing();
            }
            case HELP -> {
                menuButton.enable();
                helpButton.mouseMovedOff();
                helpButton.disable();
                infoDisplayPanel.gameHidden();
            }
        }
    }

    public void showHoverInfo(String hoverText, ArrayList<TileColor> tileColors) {
        infoDisplayPanel.setHoverInfo(hoverText, tileColors);
    }

    public void clearHoverInfo() {
        infoDisplayPanel.setHoverInfo(null, null);
    }

    public void gameStarted(GameSettings gameSettings) {
        infoDisplayPanel.gameStarted(gameSettings);
    }
}
