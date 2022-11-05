import java.awt.*;
import java.awt.image.BufferedImage;

public class InfoDisplayPanel extends DisplayPanel {

    private BufferedImage background;

    public InfoDisplayPanel(Controller controller) {
        super("Info Display Panel", new Point(150, 670), controller);
        size = new Dimension(500, 130);
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
        pen.setColor(Color.BLACK);
        pen.fillRoundRect(0,0, 500,120, 100, 100);

        return texture;
    }

    @Override
    public void react(Command command, Object object) {

    }
}
