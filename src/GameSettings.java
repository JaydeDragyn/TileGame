import java.util.ArrayList;

public record GameSettings(ArrayList<TileColor> progression, GameSettings.BoardSize boardSize) {

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
}
