/*
    class DisplayElement

    This is the base class for every element that will be shown on the game
    window.  It requires each child to deal with mouse input in some way,
    and provides the getters for the common data that all DisplayElements
    will need to be displayed on the screen or interacted with.
    It also provides a utility for every child object to load an asset
    resource (the function was crafted to work with disk files or from
    inside an executable .jar, as long as the resources are in the correct
    locations).
    It also provides a utility for the DisplayPanels (which are children of
    this class) to easily draw a given DisplayElement to a BufferedImage.
    The specific command to draw one BufferedImage onto another at a specific
    location can get rather long, so this is a syntactic shortcut that
    makes the code in those places a bit more clear and easy to follow.

 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class DisplayElement {

    protected Point location;
    protected Dimension size;
    protected boolean interactive;

    public DisplayElement(Point location) {
        this.location = location;
        this.interactive = true;
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
