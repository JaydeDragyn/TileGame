import java.util.ArrayList;

public class MenuState {

    private ArrayList<TileColor> easyProgression;
    private ArrayList<TileColor> mediumProgression;
    private ArrayList<TileColor> hardProgression;
    private ArrayList<TileColor> currentProgression;
    private GameSettings.BoardSize boardSize;
    private boolean gameInProgress;

    public void initialize() {
        easyProgression = new ArrayList<>();
        easyProgression.add(TileColor.RED);
        easyProgression.add(TileColor.WHITE);

        mediumProgression = new ArrayList<>();
        mediumProgression.add(TileColor.CYAN);
        mediumProgression.add(TileColor.MAGENTA);
        mediumProgression.add(TileColor.YELLOW);

        hardProgression = new ArrayList<>();
        hardProgression.add(TileColor.RED);
        hardProgression.add(TileColor.ORANGE);
        hardProgression.add(TileColor.YELLOW);
        hardProgression.add(TileColor.GREEN);
        hardProgression.add(TileColor.BLUE);
        hardProgression.add(TileColor.PURPLE);

        gameInProgress = false;
    }

    public void setGameMode(GameSettings.Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> currentProgression = easyProgression;
            case MEDIUM -> currentProgression = mediumProgression;
            case HARD -> currentProgression = hardProgression;
        }
    }

    public void setBoardSize(GameSettings.BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public TileColor cycleProgressionTile(GameSettings.Difficulty difficulty, int index) {
        ArrayList<TileColor> workingProgression = getProgression(difficulty);
        TileColor newColor = TileColor.nextColor(workingProgression.get(index));

        while(workingProgression.contains(newColor)) {
            newColor = TileColor.nextColor(newColor);
        }

        workingProgression.set(index, newColor);
        return newColor;
    }

    public GameSettings getNewGameSettings() {
        return new GameSettings(currentProgression, boardSize);
    }

    public void gameStarted() {
        gameInProgress = true;
    }

    public void gameEnded() {
        gameInProgress = false;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    private ArrayList<TileColor> getProgression(GameSettings.Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> easyProgression;
            case MEDIUM -> mediumProgression;
            case HARD -> hardProgression;
        };
    }
}
