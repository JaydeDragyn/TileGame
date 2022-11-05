import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticImage extends StaticDisplayElement {

    private final BufferedImage texture;

    public StaticImage(String name, Point location) {
        super(name, location);
        texture = loadAsset("images/" + name + ".png");
        size = new Dimension(texture.getWidth(), texture.getHeight());
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
