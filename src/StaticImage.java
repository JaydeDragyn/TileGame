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
