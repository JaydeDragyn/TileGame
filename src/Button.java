import java.awt.*;

public abstract class Button extends DisplayElement {

    protected boolean enabled;
    protected boolean hovered;
    protected boolean pressed;
    protected Command command;
    protected Controller controller;

    public Button(String buttonName, Point location, Command command, Controller controller) {
        super(buttonName, location);
        enabled = true;
        hovered = false;
        pressed = false;
        this.command = command;
        this.controller = controller;
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
