import java.awt.*;
import java.awt.image.BufferedImage;

public class RadioButton extends Button {

    private static BufferedImage buttonTextureOff;
    private static BufferedImage buttonTextureOn;
    private static BufferedImage buttonTextureHover;
    private static BufferedImage buttonTextureOnHover;
    private static boolean buttonImagesLoaded = false;
    private boolean activated;

    public RadioButton(String name, Point location, Command command, Controller controller) {
        super(name, location, command, controller);
        if (!buttonImagesLoaded) {
            loadRadioButtonImages();
        }
        activated = false;
        size = new Dimension(buttonTextureOff.getWidth(), buttonTextureOff.getHeight());
    }

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    @Override
    public BufferedImage getTexture() {
        if (hovered && pressed) { return buttonTextureOnHover; }
        if (hovered && !activated) { return buttonTextureHover; }
        if (activated) { return buttonTextureOn; }
        return buttonTextureOff;
    }

    private void loadRadioButtonImages() {
        buttonTextureOff = loadAsset("buttons/RadioButton.png");
        buttonTextureOn = loadAsset("buttons/RadioButtonOn.png");
        buttonTextureHover = loadAsset("buttons/RadioButtonHover.png");
        buttonTextureOnHover = loadAsset("buttons/RadioButtonOnHover.png");
        buttonImagesLoaded = true;
    }
}
