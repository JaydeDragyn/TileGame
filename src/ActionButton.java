import java.awt.*;
import java.awt.image.BufferedImage;

public class ActionButton extends Button {

    private final BufferedImage buttonTexture;
    private final BufferedImage buttonTextureHover;
    private final BufferedImage buttonTexturePressed;
    private final BufferedImage buttonTextureDisabled;

    public ActionButton(String buttonName, Point location, Command command, Controller controller) {
        super(buttonName, location, command, controller);
        buttonTexture = loadAsset("buttons/" + buttonName + "Button.png");
        buttonTextureHover = loadAsset("buttons/" + buttonName + "ButtonHover.png");
        buttonTexturePressed = loadAsset("buttons/" + buttonName + "ButtonPressed.png");
        buttonTextureDisabled = loadAsset("buttons/" + buttonName + "ButtonDisabled.png");
        size = new Dimension(buttonTexture.getWidth(), buttonTexture.getHeight());
    }

    @Override
    public BufferedImage getTexture() {
        if (!enabled) { return buttonTextureDisabled; }
        if (!hovered && !pressed) { return buttonTexture; }
        if (hovered && !pressed) { return buttonTextureHover; }
        return buttonTexturePressed;
    }

}