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
    private Command command;
    private Controller controller;

    public Button(ElementID elementID, Point location, Dimension size, String buttonName, Command command, Controller controller) {
        super(elementID, location, size);
        buttonTexture = loadAsset("buttons/" + buttonName + "Button.png");
        buttonTextureHover = loadAsset("buttons/" + buttonName + "ButtonHover.png");
        buttonTexturePressed = loadAsset("buttons/" + buttonName + "ButtonPressed.png");
        buttonTextureDisabled = loadAsset("buttons/" + buttonName + "ButtonDisabled.png");
        enabled = true;
        hovered = false;
        pressed = false;
        this.command = command;
        this.controller = controller;
    }

    @Override
    public BufferedImage getTexture() {
        if (!enabled) { return buttonTextureDisabled; }
        if (!hovered && !pressed) { return buttonTexture; }
        if (hovered && !pressed) { return buttonTextureHover; }
        return buttonTexturePressed;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public void mouseMovedOn(Point location) {
        hovered = true;
    }

    @Override
    public void mouseMovedOff() {
        hovered = false;
        pressed = false;
    }

    @Override
    public void mousePressed(Point location) {
        pressed = true;
    }

    @Override
    public void mouseReleased(Point location) {
        if (enabled && pressed) {
            controller.react(this);
        }
        pressed = false;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }


}
