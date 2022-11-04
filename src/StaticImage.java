import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticImage extends StaticDisplayElement {

    private final BufferedImage texture;

    public StaticImage(ElementID elementID, Point location, String imageName) {
        super(elementID, location, null);
        texture = loadAsset("images/" + imageName + ".png");
        this.size = new Dimension(texture.getWidth(), texture.getHeight());
    }

    public BufferedImage getTexture() {
        return texture;
    }
}
