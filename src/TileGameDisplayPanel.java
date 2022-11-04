import java.awt.*;
import java.awt.image.BufferedImage;

public class TileGameDisplayPanel extends DisplayPanel {

    private Controller controller;
    private BufferedImage texture;
    private Graphics2D pen;
    private StaticImage background;
    private StaticImage title;
    private Button menuButton;
    private Button helpButton;


    public TileGameDisplayPanel() {
        super(new Point(0,0), new Dimension(800, 800));
        isInteractive = true;
    }

    public BufferedImage getTexture() {
        drawElement(pen, background);
        drawElement(pen, title);
        drawElement(pen, menuButton);
        drawElement(pen, helpButton);

        return texture;
    }

    public void initialize(Controller controller) {
        this.controller = controller;

        texture = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        pen = texture.createGraphics();

        background = new StaticImage(new Point(0,0), "Background");
        title = new StaticImage(new Point(225, 22), "Tile Game");
        menuButton = new Button(new Point(25, 20), new Dimension(100, 100), "Menu", Command.SHOW_MENU, this.controller);
        helpButton = new Button(new Point(675, 20), new Dimension(100, 100), "Help", Command.SHOW_HELP, this.controller);

        addElement(background);
        addElement(title);
        addElement(menuButton);
        addElement(helpButton);
    }


}
