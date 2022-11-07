import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InfoDisplayPanel extends DisplayPanel {

    private BufferedImage background;

    private ArrayList<DisplayElement> hoverInfoElements;
    private BufferedImage hoverInfoTexture;
    private Graphics2D hoverInfoPen;

    private ArrayList<DisplayElement> gameInfoElements;
    private BufferedImage gameInfoTexture;
    private Graphics2D gameInfoPen;
    private boolean gameShowing;

    public InfoDisplayPanel(Controller controller) {
        super(DisplayPanelID.INFO, new Point(150, 670), controller);
        size = new Dimension(500, 130);
    }

    @Override
    public void initialize() {
        StaticImage backgroundImage = new StaticImage("Background", new Point(0, 0));
        background = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        Graphics2D backgroundPen = background.createGraphics();
        backgroundPen.drawImage(backgroundImage.getTexture(), -150, -670, null);
        backgroundPen.dispose();

        hoverInfoElements = new ArrayList<>();
        hoverInfoTexture = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        hoverInfoPen = hoverInfoTexture.createGraphics();
        hoverInfoPen.setColor(Color.BLACK);

        gameInfoElements = new ArrayList<>();
        gameInfoTexture = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        gameInfoPen = gameInfoTexture.createGraphics();
        gameInfoPen.setColor(Color.BLACK);

        gameShowing = false;
    }

    public void gameShowing() {
        gameShowing = true;
    }

    public void gameHidden() {
        gameShowing = false;
    }

    @Override
    public BufferedImage getTexture() {
        if (hoverInfoElements.size() > 0) {
            drawHoverInfoTexture();
            return hoverInfoTexture;
        }

        if (gameShowing) {
            drawGameInfoTexture();
            return gameInfoTexture;
        }

        return background;
    }

    public void setHoverInfo(String text, ArrayList<TileColor> colors) {
        hoverInfoElements.clear();

        if (text == null) {
            return;
        }

        if (colors == null) {
            hoverInfoElements.add(createInfoTextLabel(text, 22));
        } else {
            hoverInfoElements.add(createInfoTextLabel(text, 10));
            addTilesToList(colors, hoverInfoElements);
        }
    }

    public void gameStarted(GameSettings gameSettings) {
        gameInfoElements.clear();
        gameInfoElements.add(createInfoTextLabel("Color progression for this game:", 10));
        addTilesToList(gameSettings.progression(), gameInfoElements);
    }

    private TextLabel createInfoTextLabel(String text, int height) {
        int textStart = 250 - (TextLabel.getTextLabelSize(text, TextLabel.FontSize.SMALL).width / 2);
        return new TextLabel(text, new Point(textStart, height), TextLabel.FontSize.SMALL, Color.WHITE);
    }

    private void addTilesToList(ArrayList<TileColor> progression, ArrayList<DisplayElement> targetList) {
        int numTiles = progression.size();
        int totalWidth = numTiles * 60;     // 50px for medium tiles + 10px space
        int tileStart = 250 - (totalWidth / 2);
        for (TileColor color : progression) {
            targetList.add(new Tile(ButtonID.TILE_HOVER_INFO, null, new Point(tileStart, 56), color, Tile.Size.MEDIUM, controller));
            tileStart += 60;
        }
    }

    private void drawHoverInfoTexture() {
        hoverInfoPen.drawImage(background, 0, 0, null);
        if (hoverInfoElements.size() == 1) {
            hoverInfoPen.fillRoundRect(0, 0, 500, 75, 25, 25);
        } else {
            hoverInfoPen.fillRoundRect(0, 0, 500, 119, 25, 25);
        }
        for (DisplayElement element : hoverInfoElements) {
            drawElement(hoverInfoPen, element);
        }
    }

    private void drawGameInfoTexture() {
        gameInfoPen.drawImage(background, 0, 0, null);
        gameInfoPen.fillRoundRect(0, 0, 500, 119, 25, 25);
        for (DisplayElement element : gameInfoElements) {
            drawElement(gameInfoPen, element);
        }
    }
}
