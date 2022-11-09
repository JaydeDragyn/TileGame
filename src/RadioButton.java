/*
    class RadioButton

    This is a Radio button that selects from several options - like old
    car radios where pushing one button changes the station, and pushing
    another changes to a different station - you can never have two options
    selected at the same time.

    This button will not keep track of what group it is in - instead, the
    ButtonID will tell the controller that information, so as long as the
    RadioButtons are created with appropriate ButtonID's, the Controller
    is responsible for making sure the options are updated and the Radio
    buttons are "Activated" or "Deactivated" accordingly (generally,
    when one Radio Button is clicked, deactivate them all for the group
    and then activate the one that was clicked).

    The RadioButtons will all use the same textures, and that being the
    case, the first time a RadioButton is created it will statically load
    all the textures for itself and all subsequent RadioButtons to use.

    As long as appropriate mouse interaction is provided by the DisplayPanel
    that a RadioButton is attached to (and appropriate activate()/deactivate()
    method calls), the RadioButtons will interact with the mouse and send
    their Command to their Controller when "clicked".

 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class RadioButton extends Button {

    private static BufferedImage buttonTextureOff;
    private static BufferedImage buttonTextureOn;
    private static BufferedImage buttonTextureHover;
    private static BufferedImage buttonTextureOnHover;
    private static boolean buttonImagesLoaded = false;
    private boolean activated;

    public RadioButton(ButtonID name, Point location, Controller controller) {
        super(name, location, controller);
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
