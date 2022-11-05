import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class DisplayPanel extends DisplayElement {

    protected Controller controller;
    protected DisplayPanelID displayPanelID;
    protected ArrayList<DisplayElement> elements;
    protected DisplayElement lastElementInteracted;
    protected BufferedImage texture;
    protected Graphics2D pen;

    public DisplayPanel(DisplayPanelID displayPanelID, Point location, Controller controller) {
        super(location);
        this.controller = controller;
        this.displayPanelID = displayPanelID;
        elements = new ArrayList<>();
        lastElementInteracted = null;

        texture = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();
    }

    public abstract void initialize();

    public abstract void react(Command command, Object object);

    public DisplayPanelID getDisplayPanelID() {
        return displayPanelID;
    }

    @Override
    public BufferedImage getTexture() {
        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, size.width, size.height);

        for (DisplayElement element : elements) {
            drawElement(pen, element);
        }

        return texture;
    }

    @Override
    public void mouseMovedOn(Point location) {
        DisplayElement newDisplayElement = findElementUnderMouse(location);

        if (lastElementInteracted != null) {
            if (newDisplayElement != lastElementInteracted) {
                lastElementInteracted.mouseMovedOff();
            }
        }

        if (newDisplayElement != null) {
            Point relativeMousePosition = getRelativeMousePosition(newDisplayElement, location);
            newDisplayElement.mouseMovedOn(relativeMousePosition);
        }

        lastElementInteracted = newDisplayElement;
    }

    @Override
    public void mouseMovedOff() {
        if (lastElementInteracted != null) {
            lastElementInteracted.mouseMovedOff();
        }
        lastElementInteracted = null;
    }

    @Override
    public void mousePressed(Point location) {
        DisplayElement elementClicked = findElementUnderMouse(location);

        if (lastElementInteracted != null) {
            if (elementClicked != lastElementInteracted) {
                lastElementInteracted.mouseMovedOff();
            }
        }

        if (elementClicked == null) { return; }

        elementClicked.mousePressed(getRelativeMousePosition(elementClicked, location));

        lastElementInteracted = elementClicked;
    }

    @Override
    public void mouseReleased(Point location) {
        DisplayElement elementClicked = findElementUnderMouse(location);

        if (lastElementInteracted != null) {
            if (elementClicked != lastElementInteracted) {
                lastElementInteracted.mouseMovedOff();
            }
        }

        if (elementClicked == null) { return; }

        elementClicked.mouseReleased(getRelativeMousePosition(elementClicked, location));

        lastElementInteracted = elementClicked;
    }

    protected void addElement(DisplayElement element) {
        elements.add(element);
    }

    protected void removeElement(DisplayElement element) {
        if (element != null) {
            elements.remove(element);
        }
    }

    private DisplayElement findElementUnderMouse(Point location) {
        // search through list of elements to see if the mouse is on one of them
        // if it is on an interactive element (button, tile, radiobutton)
        // then return that element
        for (DisplayElement element : elements) {
            if (isWithin(location, element.getLocation(), element.getSize())) {
                if (element.isInteractive()) {
                    return element;
                }
            }
        }

        // mouse not on anything interactive
        return null;
    }

    private boolean isWithin(Point location, Point topLeft, Dimension size) {
        return location.x >= topLeft.x &&
                location.y >= topLeft.y &&
                location.x < (topLeft.x + size.width) &&
                location.y < (topLeft.y + size.height);
    }

    private Point getRelativeMousePosition(DisplayElement element, Point location) {
        // subtract the element's position from the mouse position to get an
        // adjusted position within that element
        Point relativeMousePosition = new Point();
        relativeMousePosition.x = location.x - element.getLocation().x;
        relativeMousePosition.y = location.y - element.getLocation().y;
        return relativeMousePosition;
    }
}
