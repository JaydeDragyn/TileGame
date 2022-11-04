import java.awt.*;
import java.awt.image.BufferedImage;

public class HelpDisplayPanel extends DisplayPanel {

    private Controller controller;
    private BufferedImage texture;
    private Graphics2D pen;

    public HelpDisplayPanel() {
        super(ElementID.HELP_DISPLAY_PANEL, new Point(150,150), new Dimension(500, 500));
    }

    @Override
    public BufferedImage getTexture() {
        pen.setColor(Color.RED);
        pen.drawRoundRect(0, 0, 499, 499, 100, 100);
        drawElement(pen, elements.get(0));
        return texture;
    }

    @Override
    public void initialize(Controller controller) {
        this.controller = controller;

        texture = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();

        addElement(new Button(ElementID.BUTTON, new Point(200, 200), new Dimension(100, 100), "Help", Command.SHOW_GAME, controller));
    }
}
