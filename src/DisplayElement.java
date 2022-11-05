import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class DisplayElement {

    protected String name;
    protected Point location;
    protected Dimension size;
    protected boolean interactive;

    public DisplayElement(String name, Point location) {
        this.name = name;
        this.location = location;
        this.interactive = true;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public Dimension getSize() {
        return size;
    }

    public boolean isInteractive() {
        return interactive;
    }

    public abstract BufferedImage getTexture();

    public abstract void mouseMovedOn(Point location);
    public abstract void mouseMovedOff();
    public abstract void mousePressed(Point location);
    public abstract void mouseReleased(Point location);

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
