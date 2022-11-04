import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class DisplayElement {

    private final BufferedImage texture;

    public DisplayElement(String file) {
        texture = loadAsset(file);
    }

    public BufferedImage getTexture() {
        return texture;
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
}
