import java.util.ArrayList;

public class GameSettings {

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public enum BoardSize {
        SMALL_3X3,
        MEDIUM_3X5,
        LARGE_5X5
    }

    private ArrayList<TileColor> progression;
    private BoardSize boardSize;

    public GameSettings(ArrayList<TileColor> progression, BoardSize boardSize) {
        this.progression = progression;
        this.boardSize = boardSize;
    }

    public ArrayList<TileColor> getProgression() {
        return progression;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }
}
