/*
    record GameSettings

    The game's two settings as a record - each time a new game is created,
    a new GameSetting record will be created.
    We provide enums for compiler-checked lists of options for the
    difficulty (which color progression to use) and the board size.

 */

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
