/*
    class HelpDisplayPanel

    This DisplayPanel creates the elements that show help and information.
    It deviates from the getTexture() method so that it can show the specific
    help page the user has navigated to.  Once each page is created, it is
    saved as a single BufferedImage, so the get texture doesn't need to loop
    through an arbitrary number of elements like the others, it just
    manually draws the 3 buttons and the current help page before returning
    the final texture.

 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HelpDisplayPanel extends DisplayPanel {

    private ActionButton prevButton;
    private ActionButton backButton;
    private ActionButton nextButton;
    private ArrayList<BufferedImage> pages;
    private int currentPage;

    public HelpDisplayPanel(Controller controller) {
        super(DisplayPanelID.HELP, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        pages = new ArrayList<>();
        currentPage = 0;

        initializePage0();
        initializePage1();
        initializePage2();

        addElement(prevButton = new ActionButton(ButtonID.BUTTON_PREV, new Point(25, 425), controller));
        addElement(backButton = new ActionButton(ButtonID.BUTTON_BACK, new Point(187, 425), controller));
        addElement(nextButton = new ActionButton(ButtonID.BUTTON_NEXT, new Point(350, 425), controller));

        prevButton.disable();
        backButton.disable();

    }

    @Override
    public BufferedImage getTexture() {
        pen.drawImage(pages.get(currentPage), 0, 0, null);
        drawElement(pen, prevButton);
        drawElement(pen, backButton);
        drawElement(pen, nextButton);

        return texture;
    }

    // Assumes there is a previous page to go back to, and disables the
    // Prev button when we get to the first page, so we should never
    // end up going to page -1.  Also assumes there is a Next page to
    // go to (the one we just came from!) so makes sure the Next button
    // is enabled.
    public void showPrevPage() {
        currentPage--;
        nextButton.enable();
        if (currentPage == 0) {
            prevButton.mouseMovedOff();
            prevButton.disable();
        }
    }

    // Assumes there is a next page to go forward to, and disables the
    // Next button when we get to the last page that was created, so we
    // should never end up going past the last page.  Also assumes there
    // is a previous page to go to (the one we just came from!) so makes
    // sure the Prev button is enabled.
    public void showNextPage() {
        currentPage++;
        prevButton.enable();
        if (currentPage == pages.size() -1) {
            nextButton.mouseMovedOff();
            nextButton.disable();
        }
    }

    public void gameStarted() {
        backButton.enable();
    }

    public void gameEnded() {
        backButton.mouseMovedOff();
        backButton.disable();
    }

    // The initializePageX() methods all create temporary elements for each
    // text label (we could also add tiles, buttons, and other images if we
    // want to get fancy), and then draws them to a single BufferedImage,
    // so that the getText can just draw the current page by drawing that
    // BufferedImage instead of looping through an arbitrary list of elements
    // that are not going to update with mouse interaction.
    private void initializePage0() {
        BufferedImage page0 = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D pagePen = page0.createGraphics();

        ArrayList<TextLabel> labels = new ArrayList<>();

        labels.add(new TextLabel("Instructions", new Point(146,0), TextLabel.FontSize.LARGE, new Color(0.5f, 1.0f, 0.5f)));
        labels.add(new TextLabel("Goal:", new Point(25,60), TextLabel.FontSize.MEDIUM, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("To change all tiles to the final color in the", new Point(20,110), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("color progression", new Point(20,140), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("Game Settings:", new Point(25,190), TextLabel.FontSize.MEDIUM, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("Select Easy, Medium or Hard difficulty by", new Point(20, 240), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("clicking on the light next to that setting.", new Point(20, 270), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("You can change the color progression by", new Point(20, 300), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("clicking on the color tiles.", new Point(20, 330), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("Then select the board size and click Start.", new Point(20, 360), TextLabel.FontSize.SMALL, Color.WHITE));

        for (TextLabel label : labels) {
            pagePen.drawImage(label.getTexture(), label.getLocation().x, label.getLocation().y, null);
        }

        pages.add(page0);
        pagePen.dispose();
    }

    private void initializePage1() {
        BufferedImage page1 = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D pagePen = page1.createGraphics();

        ArrayList<TextLabel> labels = new ArrayList<>();

        labels.add(new TextLabel("Instructions", new Point(146,0), TextLabel.FontSize.LARGE, new Color(0.5f, 1.0f, 0.5f)));
        labels.add(new TextLabel("Gameplay:", new Point(25,60), TextLabel.FontSize.MEDIUM, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("Change tiles by clicking on them or their", new Point(20,110), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("neighbors.  When you click on a tile, you", new Point(20,140), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("change that tile, and each of its neighbors,", new Point(20,170), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("to the next color in the progression shown", new Point(20,200), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("below the board.  If you change a tile that", new Point(20,230), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("is on the final color, it goes back to the", new Point(20,260), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("first color.", new Point(20,290), TextLabel.FontSize.SMALL, Color.WHITE));

        for (TextLabel label : labels) {
            pagePen.drawImage(label.getTexture(), label.getLocation().x, label.getLocation().y, null);
        }

        pages.add(page1);
        pagePen.dispose();
    }

    private void initializePage2() {
        BufferedImage page2 = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D pagePen = page2.createGraphics();

        ArrayList<TextLabel> labels = new ArrayList<>();

        labels.add(new TextLabel("About this game:", new Point(100, 0), TextLabel.FontSize.LARGE, new Color(0.5f, 1.0f, 0.5f)));
        labels.add(new TextLabel("v1.0 - Copyright (C) 2022  Jayde Dragyn", new Point(49, 50), TextLabel.FontSize.SMALL, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("Written by:", new Point(10, 110), TextLabel.FontSize.MEDIUM, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("Jayde Dragyn", new Point(170, 110), TextLabel.FontSize.MEDIUM, new Color(0.5f, 1.0f, 0.5f)));
        labels.add(new TextLabel("jaydedragyn@outlook.com", new Point(170,150), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("Developed with:", new Point(10, 200), TextLabel.FontSize.MEDIUM, new Color(0.75f, 1.0f, 0.75f)));
        labels.add(new TextLabel("OpenJDK 17", new Point(10,240), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("IntelliJ IDEA 2022.2.2 (Community Edition)", new Point(10,270), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("Blender 3.3.0", new Point(10,300), TextLabel.FontSize.SMALL, Color.WHITE));
        labels.add(new TextLabel("GIMP 2.10", new Point(10,330), TextLabel.FontSize.SMALL, Color.WHITE));

        for (TextLabel label : labels) {
            pagePen.drawImage(label.getTexture(), label.getLocation().x, label.getLocation().y, null);
        }

        pages.add(page2);
        pagePen.dispose();
    }

}
