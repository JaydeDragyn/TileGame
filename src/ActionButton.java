/*
    class ActionButton

    This is a Button that causes some action when clicked.  Examples are
    the Menu button, which causes the program to display the Settings Menu,
    and the Quit button, which causes the program to quit the game (after
    confirmation using another ActionButton).

    Each button's textures need to be named "<buttonName>.png",
    "<buttonName>Hover.png", "<buttonName>Pressed.png" and
    "<buttonName>Disabled.png", and be located in the buttons/ folder.
    The ButtonID will have <buttonName> for each button, so coordinate with
    that enumeration if the button names need to change.

    The action button will present its texture depending on what mouse
    interaction it is advised of by the DisplayPanel chain that contains it.

 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class ActionButton extends Button {

    private final BufferedImage buttonTexture;
    private final BufferedImage buttonTextureHover;
    private final BufferedImage buttonTexturePressed;
    private final BufferedImage buttonTextureDisabled;

    public ActionButton(ButtonID buttonID, Point location, Controller controller) {
        super(buttonID, location, controller);
        String buttonName = buttonID.getName();
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
