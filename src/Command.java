public enum Command {
    // General moving mouse around
    HOVERING,
    NOT_HOVERING,

    // Main interface interactions
    SHOW_MENU,
    SHOW_HELP,
    SET_STATE,

    // Settings Menu interactions
    SET_GAME_MODE,
    GAME_MODE_EASY,
    GAME_MODE_MEDIUM,
    GAME_MODE_HARD,
    SET_BOARD_SIZE,
    BOARD_SIZE_3X3,
    BOARD_SIZE_3X5,
    BOARD_SIZE_5X5,
    CYCLE_EASY_TILE,
    CYCLE_MEDIUM_TILE,
    CYCLE_HARD_TILE,

    START_NEW_GAME,
    RETURN_TO_GAME,
    QUIT_GAME,

    // Confirm quit
    CONFIRM_QUIT,
    YES_QUIT,
    NO_QUIT,

    // Help screen interactions
    PREV_HELP_PAGE,
    NEXT_HELP_PAGE,
}
