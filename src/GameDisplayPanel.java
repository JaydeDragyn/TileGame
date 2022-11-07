import java.awt.*;

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
                        new Point(r, c),
                        new Point(corner.x + (c * 100), corner.y + (r * 100)),
                        colors[r][c],
                        Tile.Size.LARGE,
                        controller));
            }
        }
    }

    public void expandHover(Tile tile) {
        Point p = tile.getIndex();
        if (inbounds(new Point(p.x -1, p.y))) { tiles[p.x -1][p.y].hover(); }
        if (inbounds(new Point(p.x +1, p.y))) { tiles[p.x +1][p.y].hover(); }
        if (inbounds(new Point(p.x, p.y -1))) { tiles[p.x][p.y -1].hover(); }
        if (inbounds(new Point(p.x, p.y +1))) { tiles[p.x][p.y +1].hover(); }
    }

    public void expandPress(Tile tile) {
        Point p = tile.getIndex();
        if (inbounds(new Point(p.x -1, p.y))) { tiles[p.x -1][p.y].press(); }
        if (inbounds(new Point(p.x +1, p.y))) { tiles[p.x +1][p.y].press(); }
        if (inbounds(new Point(p.x, p.y -1))) { tiles[p.x][p.y -1].press(); }
        if (inbounds(new Point(p.x, p.y +1))) { tiles[p.x][p.y +1].press(); }
    }

    public void clearAll() {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                tile.release();
                tile.unhover();
            }
        }
    }

    private Point getStartingCorner(GameSettings.BoardSize boardSize) {
        return switch (boardSize) {
            case SMALL_3X3 -> new Point(100, 100);
            case MEDIUM_3X5 -> new Point(0, 100);
            case LARGE_5X5 -> new Point(0, 0);
        };
    }

    private boolean inbounds(Point p) {
        return (p.x >= 0) &&
                (p.y >= 0) &&
                (p.x < tiles[0].length) &&
                (p.y < tiles.length);
    }
}
