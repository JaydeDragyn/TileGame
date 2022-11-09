/*
    class GameDisplayPanel

    This DisplayPanel creates tiles to play the game with, and adjusts
    them as requested by the GameController.
    Since Tiles are Display Elements, this DisplayPanel also uses the
    default behavior of polling each element in the elements list to
    draw their texture and then presents the completed texture to the
    caller.  This allows the tiles to provide the appropriate texture
    for their state (normal, hovered, pressed, dimmed) without this
    DisplayPanel needing to know anything about the state of the game.

 */

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

    // Brute-force update to all the tiles each time around.
    // For this application, there are at most 25 tiles, so
    // the inefficiency is not worth the trouble to redesign.
    public void updateTileColors(TileColor[][] colors) {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                Point p = tile.getIndex();
                tile.setColor(colors[p.y][p.x]);
            }
        }
    }

    // The neighbors are already calculated for us, we just
    // manually "hover" the list we're given
    public void expandHover(ArrayList<Point> neighbors) {
        for (Point neighbor : neighbors) {
            tiles[neighbor.y][neighbor.x].hover();
        }
    }

    // The neighbors are already calculated for us, we just
    // manually "press" the list we're given
    public void expandPress(ArrayList<Point> neighbors) {
        for (Point neighbor : neighbors) {
            tiles[neighbor.y][neighbor.x].press();
        }
    }

    // Brute force clear all effects
    public void clearAll() {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                tile.enable();
                tile.turnOn();
                tile.release();
                tile.unhover();
            }
        }
    }

    // Dim (.turnOff()) each tile if it is not the color we were given
    public void dimAllExcept(Tile tile) {
        for (Tile[] tileRow : tiles) {
            for (Tile t : tileRow) {
                if (t.getColor() != tile.getColor()) {
                    t.turnOff();
                }
            }
        }
    }

    // Disable each tile if it is not the color we were given
    public void disableAllExcept(Tile tile) {
        for (Tile[] tileRow : tiles) {
            for (Tile t : tileRow) {
                if (t.getColor() != tile.getColor()) {
                    t.disable();
                }
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
