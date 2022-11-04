import java.awt.*;

public abstract class StaticDisplayElement extends DisplayElement {

    public StaticDisplayElement(ElementID elementID, Point location, Dimension size) {
        super(elementID, location, size);
        this.isInteractive = false;
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
