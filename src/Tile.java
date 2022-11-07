import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Tile extends Button {

    public enum Size {
        SMALL(25),
        MEDIUM(50),
        LARGE(100);

        private final int size;

        Size(int size) {
            this.size = size;
        }
    }

    private final Point index;
    private TileColor color;
    private boolean off;

    public Tile(ButtonID buttonID, Point index, Point location, TileColor color, Size size, Controller controller) {
        super(buttonID, location, controller);

        if (!initialized) { initializeTiles(); }

        this.index = index;
        this.color = color;
        this.size = new Dimension(size.size, size.size);
        off = false;
    }

    public Point getIndex() {
        return index;
    }

    public TileColor getColor() {
        return color;
    }

    public void setColor(TileColor color) {
        this.color = color;
    }

    @Override
    public BufferedImage getTexture() {
        if (!enabled) { return sizeTexture(disabledTile); }          // disabled
        if (off) { return sizeTexture(offTiles.get(color)); }        // off
        if (pressed) { return sizeTexture(selectTiles.get(color)); } // selected
        if (hovered) { return sizeTexture(hoverTiles.get(color)); }  // hovered
        return sizeTexture(onTiles.get(color));                      // on
    }

    // -----------------------------------------------------------------------

    private static boolean initialized = false;
    private static final Map<TileColor, BufferedImage> offTiles = new EnumMap<>(TileColor.class);
    private static final Map<TileColor, BufferedImage> onTiles = new EnumMap<>(TileColor.class);
    private static final Map<TileColor, BufferedImage> hoverTiles = new EnumMap<>(TileColor.class);
    private static final Map<TileColor, BufferedImage> selectTiles = new EnumMap<>(TileColor.class);
    private static final BufferedImage disabledTile = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

    private BufferedImage sizeTexture(BufferedImage texture) {
        BufferedImage sizedTexture = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D sizer = sizedTexture.createGraphics();
        sizer.drawImage(texture, 0,0, size.width, size.height, null);
        sizer.dispose();
        return sizedTexture;
    }

    private void initializeTiles() {
        // for each category/Map:
        initializeMap(Tile.offTiles, "Off");
        initializeMap(Tile.onTiles, "On");
        initializeMap(Tile.hoverTiles, "Hover");
        initializeMap(Tile.selectTiles, "Select");
        Tile.initialized = true;
    }

    private void initializeMap(Map<TileColor, BufferedImage> map, String modifier) {
        TileColor[] tileColors = TileColor.values();

        for (TileColor tc : tileColors) {
            String filename = "tiles/" + modifier + tc.toString() + ".png";
            BufferedImage tileImage = loadAsset(filename);
            map.put(tc, tileImage);
        }
    }
}
