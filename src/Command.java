/*
    enum Command

    These are compiler-checked commands that the Buttons will use to inform the
    controllers of the user's intent.

 */

public enum Command {
    SHOW_MENU,
    SHOW_HELP,

    GAME_MODE_EASY,
    GAME_MODE_MEDIUM,
    GAME_MODE_HARD,

    CLICK_TILE,
    HOVER_TILE,

    BOARD_SIZE_3X3,
    BOARD_SIZE_3X5,
    BOARD_SIZE_5X5,

    CYCLE_EASY_TILE,
    CYCLE_MEDIUM_TILE,
    CYCLE_HARD_TILE,

    START_NEW_GAME,
    RETURN_TO_GAME,
    QUIT_GAME,

    YES_START,
    NO_START,

    YES_QUIT,
    NO_QUIT,

    PREV_HELP_PAGE,
    NEXT_HELP_PAGE,
}
