public class GameState {

    private GameSettings gameSettings;
    private TileColor[][] tiles;

    public void newGame(GameSettings gameSettings) {
        System.out.println("Starting new game with settings:");
        System.out.println(gameSettings.progression());
        System.out.println(gameSettings.boardSize());

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

    private void setNewBoard(int rows, int cols) {
        tiles = new TileColor[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tiles[r][c] = gameSettings.progression().get(0);
            }
        }
    }
}
