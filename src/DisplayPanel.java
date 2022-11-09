/*
    class DisplayPanel

    This is a base class for the main components.  It is a DisplayElement in
    that the children of this base will create and add other DisplayElements
    to a list (elements), and then when asked for its texture, it will build
    the texture from the elements in its list.  This will allow those elements
    to always be displayed in their updated forms - a Button for example,
    when the mouse hovers over it, the Button will return it's hovered texture
    instead of its base texture.  This DisplayPanel will draw the button's
    hovered texture and then pass that collection of images upstream to be
    drawn like any other DisplayElement.

    This class also provides the important functionality of figuring out what
    the user is trying to interact with.  When it is provided with mouse
    interaction, it determines where the mouse is, finds out if an interactive
    element is under the mouse (so StaticDisplayElements are ignored) and then
    tells that element that the mouse is doing something with it.

    The "magic" happens when the TileGameDisplayPanel gets input (such as
    mouseMovedTo(location)) and that location is over one of the other
    DisplayPanels (Game, Menu, Help or Info).  The TileGameDisplayPanel
    informs the DisplayPanel currently on display that the mouse moved to
    a location.  That DisplayPanel has its own list of elements and does the
    same thing.  So if the mouse is ultimately over a game tile that is on
    the GameDisplayPanel, which is on the TileGameDisplayPanel, that tile will
    be informed and can notify its controller.



 */

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

    // When the mouse moves on top of the DisplayPanel, first find out which
    // element is under it.  If the mouse is over a different element than it
    // was last time we checked, let the old element know the mouse moved off
    // of it, and then let the new element know the mouse is over it (adjusting
    // the mouse coordinates, so they are now relative to that new element's
    // top/left corner).  This way, each DisplayPanel only needs to be aware
    // of its own size.  Leaf elements (like buttons) won't care where on the
    // button the mouse is, just that it's hovering over the button.  (This
    // structure would allow the element to track the mouse, such as a compass
    // arrow that points to where the mouse is, or a light that follows it...)
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

    // When the user moves the mouse off of an element, let the element know,
    // so it can show a different texture or otherwise react accordingly.
    @Override
    public void mouseMovedOff() {
        if (lastElementInteracted != null) {
            lastElementInteracted.mouseMovedOff();
        }
        lastElementInteracted = null;
    }

    // Like mouseMovedOn(), except the signal will be that the user pressed
    // the mouse button down on the element (but has not released it yet)
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

    // Like mousePressed(), except this completes a mouse "click", so we'll
    // provide that message to the next element down.
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

    // search through list of elements to see if the mouse is on one of them
    // if it is on an interactive element (button, tile, radiobutton)
    // then return that element
    private DisplayElement findElementUnderMouse(Point location) {
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

    // subtract the element's position from the mouse position to get an
    // adjusted position within that element
    private Point getRelativeMousePosition(DisplayElement element, Point location) {
        Point relativeMousePosition = new Point();
        relativeMousePosition.x = location.x - element.getLocation().x;
        relativeMousePosition.y = location.y - element.getLocation().y;
        return relativeMousePosition;
    }
}
