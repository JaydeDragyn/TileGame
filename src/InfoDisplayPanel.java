/*
    class InfoDisplayPanel

    This DisplayPanel shows all the information that goes at the bottom of
    the program window.  When the user hovers over a button, this will show
    that button's hoverText.  This DisplayPanel is unique in that it needs to
    be informed of when the GameDisplayPanel is shown or hidden so that it
    can show the accompanying color progression for the user.
    When generating the display, this will prioritize:

    1. Temporary hover text, even if the GameDisplayPanel is showing
    2. Game info
    3. Empty background if nothing is hovered and GameDisplayPanel is hidden

 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InfoDisplayPanel extends DisplayPanel {

    private BufferedImage background;
    private ArrayList<DisplayElement> emptyList;
    private ArrayList<DisplayElement> hoverInfoElements;
    private ArrayList<DisplayElement> gameInfoElements;
    private boolean gameShowing;

    public InfoDisplayPanel(Controller controller) {
        super(DisplayPanelID.INFO, new Point(150, 670), controller);
        size = new Dimension(500, 130);
    }

    @Override
    public void initialize() {
        pen.setColor(Color.BLACK);

        StaticImage backgroundImage = new StaticImage("Background", new Point(0, 0));
        background = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        Graphics2D backgroundPen = background.createGraphics();
        backgroundPen.drawImage(backgroundImage.getTexture(), -150, -670, null);
        backgroundPen.dispose();

        emptyList = elements;
        hoverInfoElements = new ArrayList<>();
        gameInfoElements = new ArrayList<>();

        gameShowing = false;
    }

    // Per the priority list noted above, we'll draw the appropriate elements
    // onto the DisplayPanel texture and then return it
    @Override
    public BufferedImage getTexture() {
        pen.drawImage(background, 0, 0, null);

        if (hoverInfoElements.size() > 0) {
            elements = hoverInfoElements;
            if (hoverInfoElements.size() == 1) {
                pen.fillRoundRect(0, 0, 500, 75, 25, 25);
            } else {
                pen.fillRoundRect(0, 0, 500, 119, 25, 25);
            }

        } else  if (gameShowing) {
            elements = gameInfoElements;
            pen.fillRoundRect(0, 0, 500, 119, 25, 25);

        } else {
            elements = emptyList;
        }

        for (DisplayElement element : elements) {
            drawElement(pen, element);
        }

        return texture;
    }

    public void gameStarted(GameSettings gameSettings) {
        gameInfoElements.clear();
        gameInfoElements.add(createInfoTextLabel("Color progression for this game:", 10));
        addTilesToList(gameInfoElements, gameSettings.progression());
    }

    public void gameShowing() {
        gameShowing = true;
    }

    public void gameHidden() {
        gameShowing = false;
    }

    // The TileGameDisplayPanel communicates what to show by providing
    // zero, one or two arguments to this method.
    // Zero arguments is null, null, and this tells us not to display
    // any hover info, so we'll clear the list.
    // One argument is just text, so we'll create a text label that is
    // centered, so we can show it on an appropriately sized background.
    // Two arguments is text and a color progression, so we can show
    // both items in a larger black background.
    public void setHoverInfo(String text, ArrayList<TileColor> colors) {
        hoverInfoElements.clear();

        if (text == null) {
            return;
        }

        if (colors == null) {
            hoverInfoElements.add(createInfoTextLabel(text, 22));
        } else {
            hoverInfoElements.add(createInfoTextLabel(text, 10));
            addTilesToList(hoverInfoElements, colors);
        }
    }

    private TextLabel createInfoTextLabel(String text, int height) {
        int textStart = 250 - (TextLabel.getTextLabelSize(text, TextLabel.FontSize.SMALL).width / 2);
        return new TextLabel(text, new Point(textStart, height), TextLabel.FontSize.SMALL, Color.WHITE);
    }

    private void addTilesToList(ArrayList<DisplayElement> targetList, ArrayList<TileColor> progression) {
        int numTiles = progression.size();
        int totalWidth = (numTiles * 60) - 10;     // 50px for medium tiles + 10px space (less 10 to keep centered)
        int tileStart = 250 - (totalWidth / 2);
        for (TileColor color : progression) {
            targetList.add(new Tile(ButtonID.TILE_HOVER_INFO, null, new Point(tileStart, 56), color, Tile.Size.MEDIUM, controller));
            tileStart += 60;
        }
    }
}
