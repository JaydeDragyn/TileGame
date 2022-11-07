import java.awt.*;
import java.util.ArrayList;

public class GameDisplayPanel extends DisplayPanel {

    private Tile[][] tiles;

    public GameDisplayPanel(Controller controller) {
        super(DisplayPanelID.GAME, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {

    }

    public void newGame(GameSettings gameSettings, TileColor[][] colors) {
        elements.clear();

        Point corner = getStartingCorner(gameSettings.boardSize());

        int rows = colors.length;
        int cols = colors[0].length;
        tiles = new Tile[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                addElement(tiles[r][c] = new Tile(ButtonID.TILE_GAME_TILE,
                        new Point(c, r),
                        new Point(corner.x + (c * 100), corner.y + (r * 100)),
                        colors[r][c],
                        Tile.Size.LARGE,
                        controller));
            }
        }
    }

    public void updateTileColors(TileColor[][] colors) {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                Point p = tile.getIndex();
                tile.setColor(colors[p.y][p.x]);
            }
        }
    }

    public void expandHover(ArrayList<Point> neighbors) {
        for (Point neighbor : neighbors) {
            tiles[neighbor.y][neighbor.x].hover();
        }
    }

    public void expandPress(ArrayList<Point> neighbors) {
        for (Point neighbor : neighbors) {
            tiles[neighbor.y][neighbor.x].press();
        }
    }

    public void clearAll() {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                tile.release();
                tile.unhover();
            }
        }
    }

    public void showWin() {
        addElement(new StaticImage("CongratsCoverTransparent", new Point(0, 0)));
    }

    private Point getStartingCorner(GameSettings.BoardSize boardSize) {
        return switch (boardSize) {
            case SMALL_3X3 -> new Point(100, 100);
            case MEDIUM_3X5 -> new Point(0, 100);
            case LARGE_5X5 -> new Point(0, 0);
        };
    }
}
