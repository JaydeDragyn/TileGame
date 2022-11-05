import java.awt.*;
import java.awt.image.BufferedImage;

public class InfoDisplayPanel extends DisplayPanel {

    private BufferedImage background;
    private TextLabel currentHoverText;

    public InfoDisplayPanel(Controller controller) {
        super("Info Display Panel", new Point(150, 670), controller);
        size = new Dimension(500, 130);
        currentHoverText = null;
    }

    @Override
    public void initialize() {
        StaticImage backgroundImage = new StaticImage("Background", new Point(0,0));
        background = new BufferedImage(500, 130, BufferedImage.TYPE_INT_RGB);
        Graphics2D backgroundPen = background.createGraphics();
        backgroundPen.drawImage(backgroundImage.getTexture(), -150, -670, null);
        backgroundPen.dispose();
    }

    @Override
    public BufferedImage getTexture() {
        pen.drawImage(background, 0, 0, null);

        if (currentHoverText != null) {
            pen.setColor(Color.BLACK);
            pen.fillRoundRect(0,0, 500,75, 100, 100);
            drawElement(pen, currentHoverText);
        }

        return texture;
    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING -> setHoverText((String) object);
            case NOT_HOVERING -> setHoverText(null);
        }
    }

    private void setHoverText(String hoverText) {
        if (currentHoverText != null && currentHoverText.getName().equals(hoverText)) { return; }

        if (hoverText == null) {
            currentHoverText = null;
            return;
        }

        String newHoverText;
        switch (hoverText) {
            case "Menu" -> newHoverText = "Show the settings menu";
            case "Help" -> newHoverText = "Show the help/info";
            case "Start" -> newHoverText = "Start a new game";
            case "Quit" -> newHoverText = "Quit the game";
            case "Back" -> newHoverText = "Go back to the game in progress";
            case "Prev" -> newHoverText = "Show the previous info page";
            case "Next" -> newHoverText = "Show the next info page";

            default -> newHoverText = "Unknown hover text: " + hoverText;
        }

        Dimension hoverTextSize = TextLabel.getTextLabelSize(newHoverText, TextLabel.FontSize.SMALL);
        currentHoverText = new TextLabel(newHoverText, new Point(250-(hoverTextSize.width/2), 22), TextLabel.FontSize.SMALL, Color.WHITE);
    }

}
