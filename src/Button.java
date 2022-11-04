import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends DisplayElement {

    private final BufferedImage buttonTexture;
    private final BufferedImage buttonTextureHover;
    private final BufferedImage buttonTexturePressed;
    private final BufferedImage buttonTextureDisabled;
    private boolean enabled;
    private boolean hovered;
    private boolean pressed;

    public Button(Point location, String buttonName) {
        super(location);
        buttonTexture = loadAsset("buttons/" + buttonName + "Button.png");
        buttonTextureHover = loadAsset("buttons/" + buttonName + "ButtonHover.png");
        buttonTexturePressed = loadAsset("buttons/" + buttonName + "ButtonPressed.png");
        buttonTextureDisabled = loadAsset("buttons/" + buttonName + "ButtonDisabled.png");
        enabled = true;
        hovered = false;
        pressed = false;
    }

    @Override
    public BufferedImage getTexture() {
        if (!enabled) { return buttonTextureDisabled; }
        if (!hovered && !pressed) { return buttonTexture; }
        if (hovered && !pressed) { return buttonTextureHover; }
        return buttonTexturePressed;
    }
}
