import java.awt.*;

public abstract class StaticDisplayElement extends DisplayElement {

    public StaticDisplayElement(String name, Point location) {
        super(name, location);
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
