import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class DisplayElement {

    protected Point location;

    public DisplayElement(Point location) {
        this.location = location;
    }

    public abstract BufferedImage getTexture();

    public Point getLocation() {
        return location;
    }

    //-------------------------------------------------------------------------

    public BufferedImage loadAsset(String assetName) {
        String asset = "/" + assetName;

        try {
            InputStream inputStream = getClass().getResourceAsStream(asset);
            if (inputStream == null) { throw new IOException(); }
            return ImageIO.read(inputStream);
        } catch(IOException e) {
            System.err.println("Unable to load image asset: " + assetName);
            return new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        }
    }

    public static void drawElement(Graphics2D pen, DisplayElement element) {
        pen.drawImage(element.getTexture(), element.getLocation().x, element.getLocation().y, null);
    }

}
