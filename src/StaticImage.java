/*
    class StaticImage

    This is just an image.  Given the filename (without .png), as long
    as the file is in the images/ folder, this will load the image and
    make it available to be displayed easily.  It will determine how
    big it is based on the image that is loaded.

 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticImage extends StaticDisplayElement {

    private final BufferedImage texture;

    public StaticImage(String imageName, Point location) {
        super(location);
        texture = loadAsset("images/" + imageName + ".png");
        size = new Dimension(texture.getWidth(), texture.getHeight());
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
