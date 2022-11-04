import java.awt.*;
import java.awt.image.BufferedImage;

public class GameDisplayPanel extends DisplayPanel {

    private Controller controller;
    private BufferedImage texture;
    private Graphics2D pen;

    public GameDisplayPanel() {
        super(ElementID.GAME_DISPLAY_PANEL, new Point(150,150), new Dimension(500, 500));
    }

    @Override
    public BufferedImage getTexture() {
        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, 499, 499);
        pen.setColor(Color.GREEN);
        pen.drawRoundRect(0, 0, 499, 499, 100, 100);
        drawElement(pen, elements.get(0));

        return texture;
    }

    @Override
    public void initialize(Controller controller) {
        this.controller = controller;

        texture = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();

        addElement(new StaticImage(ElementID.STATIC_IMAGE, new Point(75, 200), "Tile Game"));
    }
}
