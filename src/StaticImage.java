import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticImage extends DisplayElement {

    private final BufferedImage texture;

    public StaticImage(Point location, String imageName) {
        super(location);
        texture = loadAsset("images/" + imageName + ".png");
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
