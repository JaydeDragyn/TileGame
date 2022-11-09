/*
    class StaticDisplayElement

    This is a DisplayElement that won't change and does not interact with
    the mouse.  This includes images and text labels.
    This base eats mouse input and marks children as non-interactive.

 */

import java.awt.*;

public abstract class StaticDisplayElement extends DisplayElement {

    public StaticDisplayElement(Point location) {
        super(location);
        this.interactive = false;
    }

    @Override
    public void mouseMovedOn(Point location) {

    }

    @Override
    public void mouseMovedOff() {

    }

    @Override
    public void mousePressed(Point location) {

    }

    @Override
    public void mouseReleased(Point location) {

    }
}
