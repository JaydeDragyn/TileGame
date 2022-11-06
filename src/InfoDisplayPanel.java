import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InfoDisplayPanel extends DisplayPanel {

    private BufferedImage background;
    private BufferedImage hoverInfoTexture;
    private Graphics2D hoverInfoPen;
    private boolean showingHoverText;

    public InfoDisplayPanel(Controller controller) {
        super(DisplayPanelID.INFO, new Point(150, 670), controller);
        size = new Dimension(500, 130);
        showingHoverText = false;
    }

    @Override
    public void initialize() {
        StaticImage backgroundImage = new StaticImage("Background", new Point(0, 0));
        background = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        Graphics2D backgroundPen = background.createGraphics();
        backgroundPen.drawImage(backgroundImage.getTexture(), -150, -670, null);
        backgroundPen.dispose();

        hoverInfoTexture = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        hoverInfoPen = hoverInfoTexture.createGraphics();
        hoverInfoPen.setColor(Color.BLACK);
    }

    @Override
    public BufferedImage getTexture() {
        if (showingHoverText) {
            return hoverInfoTexture;
        }

        return background;
    }

    public void showHoverInfo(String text, ArrayList<TileColor> tiles) {
        hoverInfoPen.drawImage(background, 0, 0, null);

        if (text == null) {
            showingHoverText = false;
            return;
        }

        TextLabel hoverText = new TextLabel(text, new Point(0, 0), TextLabel.FontSize.SMALL, Color.WHITE);
        int hoverTextStart = 250 - ((TextLabel.getTextLabelSize(text, TextLabel.FontSize.SMALL).width) / 2);
        showingHoverText = true;

        if (tiles == null) {
            hoverInfoPen.fillRoundRect(0, 0, 500, 75, 25, 25);
            hoverInfoPen.drawImage(hoverText.getTexture(), hoverTextStart, 22, null);
        } else {
            hoverInfoPen.fillRoundRect(0, 0, 500, 119, 30, 30);
            hoverInfoPen.drawImage(hoverText.getTexture(), hoverTextStart, 10, null);
            int numTiles = tiles.size();
            int tileWidthNeeded = numTiles * 60;     // 50px for medium tiles + 10px space
            int tileX = 250 - (tileWidthNeeded / 2); // center - half width needed
            for (TileColor color : tiles) {
                Tile tile = new Tile(null, new Point(tileX, 56), color, Tile.Size.MEDIUM, null);
                hoverInfoPen.drawImage(tile.getTexture(), tile.getLocation().x, tile.getLocation().y, null);
                tileX += 60;
            }
        }
    }

    public void gameStarted(GameSettings gameSettings) {

    }
}
