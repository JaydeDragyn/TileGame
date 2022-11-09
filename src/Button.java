/*
    class Button

    This is a DisplayElement that will interact with the mouse, and when
    interacted with will send messages to a controller to update the game
    state in some way.

 */

import java.awt.*;

public abstract class Button extends DisplayElement {

    protected ButtonID buttonID;
    protected boolean enabled;
    protected boolean hovered;
    protected boolean pressed;
    protected Controller controller;

    public Button(ButtonID buttonID, Point location, Controller controller) {
        super(location);
        this.buttonID = buttonID;
        this.controller = controller;
        enabled = true;
        hovered = false;
        pressed = false;
    }

    public ButtonID getButtonID() {
        return buttonID;
    }

    public String getName() {
        return buttonID.getName();
    }

    public String getHoverText() {
        return buttonID.getHoverText();
    }

    public Command getCommand() {
        return getButtonID().getCommand();
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void hover() {
        hovered = true;
    }

    public void unhover() {
        hovered = false;
        pressed = false;
    }

    public void press() {
        pressed = true;
    }

    public void release() {
        pressed = false;
    }

    @Override
    public void mouseMovedOn(Point location) {
        if (enabled) {
            hover();
            controller.hover(this);
        }
    }

    @Override
    public void mouseMovedOff() {
        if (enabled) {
            unhover();
            controller.unhover();
        }
    }

    @Override
    public void mousePressed(Point location) {
        if (enabled) {
            press();
            controller.press(this);
        }
     }

    @Override
    public void mouseReleased(Point location) {
        // checking if pressed prevents pressing one button and releasing
        // on another button from activating the other button.  Instead,
        // pressing on a button and releasing anywhere other than on that
        // same button will simply cancel the button "click"
        if (enabled && pressed) {
            release();
            controller.react(this);
        }
    }


}
