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

    @Override
    public void mouseMovedOn(Point location) {
        if (!enabled) { return; }
        hovered = true;
        controller.react(Command.HOVERING, this);
    }

    @Override
    public void mouseMovedOff() {
        hovered = false;
        pressed = false;
        controller.react(Command.NOT_HOVERING, this);
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
