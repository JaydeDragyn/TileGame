/*
    class MenuState

    This is the "Model" part of the Model/Controller/View design for the
    Menu system.  This model keeps track of the state of the menu -
    which difficulty progression is currently selected, what the progression
    colors are, and what size board is currently selected.
    When the user clicks on one of the buttons or progression tiles, that
    button's controller will tell us what to update.

    This will use the TileColor's .nextColor() method to cycle through the
    colors when the user clicks on a progression tile, skipping colors that
    are already in the progression so that the game does not enter an
    unplayable state.

    When a new game is requested, this will capture the current
    progression and current board size in a GameSettings object and
    return that.

 */

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

    public ArrayList<TileColor> getProgression(GameSettings.Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> easyProgression;
            case MEDIUM -> mediumProgression;
            case HARD -> hardProgression;
        };
    }
}
