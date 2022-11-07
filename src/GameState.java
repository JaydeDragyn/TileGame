import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private GameSettings gameSettings;
    private TileColor[][] tiles;
    private boolean activeGame;

    public void newGame(GameSettings gameSettings) {
        activeGame = true;

        this.gameSettings = gameSettings;
        switch (gameSettings.boardSize()) {
            case SMALL_3X3 -> setNewBoard(3, 3);
            case MEDIUM_3X5 -> setNewBoard(3, 5);
            case LARGE_5X5 -> setNewBoard(5, 5);
        }
    }

    public TileColor[][] getTiles() {
        return tiles;
    }

    public boolean isGameActive() {
        return activeGame;
    }

    public void cycleTiles(Point p) {
        cycleTile(p);
        ArrayList<Point> neighbors = getNeighbors(p);
        for (Point neighbor : neighbors) {
            cycleTile(neighbor);
        }
    }

    public ArrayList<Point> getNeighbors(Point p) {
        ArrayList<Point> neighbors = new ArrayList<>();
        if (inbounds(new Point(p.x -1, p.y))) { neighbors.add(new Point(p.x -1, p.y)); }
        if (inbounds(new Point(p.x +1, p.y))) { neighbors.add(new Point(p.x +1, p.y)); }
        if (inbounds(new Point(p.x, p.y -1))) { neighbors.add(new Point(p.x, p.y -1)); }
        if (inbounds(new Point(p.x, p.y +1))) { neighbors.add(new Point(p.x, p.y +1)); }
        return neighbors;
    }

    public boolean winState() {
        int lastColor = gameSettings.progression().size() -1;
        TileColor target = gameSettings.progression().get(lastColor);
        for (TileColor[] colorRow : tiles) {
            for (TileColor color : colorRow) {
                if (color != target) {
                    return false;
                }
            }
        }
        activeGame = false;
        return true;
    }

    private void setNewBoard(int rows, int cols) {
        tiles = new TileColor[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tiles[r][c] = gameSettings.progression().get(0);
            }
        }
    }

    private boolean inbounds(Point p) {
        return (p.x >= 0) &&
                (p.y >= 0) &&
                (p.x < tiles[0].length) &&
                (p.y < tiles.length);
    }

    private void cycleTile(Point p) {
        TileColor currentColor = tiles[p.y][p.x];
        TileColor newColor;
        int index = gameSettings.progression().indexOf(currentColor) + 1;
        if (index >= gameSettings.progression().size()) {
            newColor = gameSettings.progression().get(0);
        }  else {
            newColor = gameSettings.progression().get(index);
        }
        tiles[p.y][p.x] = newColor;
    }
}
