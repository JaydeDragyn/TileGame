/*
    enum ButtonID

    Every button will use a ButtonID, which will provide a way to add the
    button's name (for ActionButtons, this will be the filename as well),
    the button's hover text, and the button's Command, all in one place.
    If we wanted to localize this game, we could modify this to contain
    all the various hover texts in each language, and then instead of
    hardcoding "Show the settings menu" on the BUTTON_MENU, have it point
    to the current language setting's buttonMenu(language).

    The image of the text on each button won't change without re-rendering
    the buttons with the new language (or just drawing a new button altogether).

    Using this ButtonID prevents the Controllers and the DisplayPanels from
    needing to know specifics about each button beyond which ButtonID and
    where to place it.

 */
public enum ButtonID {
    // Action Buttons
    BUTTON_MENU("Menu", "Show the settings menu", Command.SHOW_MENU),
    BUTTON_HELP("Help", "Show the help/info", Command.SHOW_HELP),
    BUTTON_START("Start", "Start a new game", Command.START_NEW_GAME),
    BUTTON_BACK("Back", "Go back to the game in progress", Command.RETURN_TO_GAME),
    BUTTON_QUIT("Quit", "Quit the game", Command.QUIT_GAME),
    BUTTON_PREV("Prev", "Show the previous info page", Command.PREV_HELP_PAGE),
    BUTTON_NEXT("Next", "Show the next info page", Command.NEXT_HELP_PAGE),
    BUTTON_YES_START("Yes", "Discard current game and start a new game", Command.YES_START),
    BUTTON_NO_START("No", "Keep the current game and return to the menu", Command.NO_START),
    BUTTON_YES_QUIT("Yes", "Quit the game", Command.YES_QUIT),
    BUTTON_NO_QUIT("No", "Return to the menu", Command.NO_QUIT),

    // Radio buttons
    BUTTON_DIFFICULTY_EASY("Easy", "Set difficulty to Easy", Command.GAME_MODE_EASY),
    BUTTON_DIFFICULTY_MEDIUM("Medium", "Set difficulty to Medium", Command.GAME_MODE_MEDIUM),
    BUTTON_DIFFICULTY_HARD("Hard", "Set difficulty to Hard", Command.GAME_MODE_HARD),
    BUTTON_BOARD_SIZE_3X3("3x3", "Set board size to 3x3", Command.BOARD_SIZE_3X3),
    BUTTON_BOARD_SIZE_3X5("3x5", "Set board size to 3x5", Command.BOARD_SIZE_3X5),
    BUTTON_BOARD_SIZE_5X5("5x5", "Set board size to 5x5", Command.BOARD_SIZE_5X5),

    // Tiles
    TILE_MENU_EASY_0("0", "Change starting color of easy progression", Command.CYCLE_EASY_TILE),
    TILE_MENU_EASY_1("1", "Change target color of easy progression", Command.CYCLE_EASY_TILE),
    TILE_MENU_MEDIUM_0("0", "Change starting color of medium progression", Command.CYCLE_MEDIUM_TILE),
    TILE_MENU_MEDIUM_1("1", "Change 2nd color of medium progression", Command.CYCLE_MEDIUM_TILE),
    TILE_MENU_MEDIUM_2("2", "Change target color of medium progression", Command.CYCLE_MEDIUM_TILE),
    TILE_MENU_HARD_0("0", "Change starting color of hard progression", Command.CYCLE_HARD_TILE),
    TILE_MENU_HARD_1("1", "Change 2nd color of hard progression", Command.CYCLE_HARD_TILE),
    TILE_MENU_HARD_2("2", "Change 3rd color of hard progression", Command.CYCLE_HARD_TILE),
    TILE_MENU_HARD_3("3", "Change 4th color of hard progression", Command.CYCLE_HARD_TILE),
    TILE_MENU_HARD_4("4", "Change 5th color of hard progression", Command.CYCLE_HARD_TILE),
    TILE_MENU_HARD_5("5", "Change target color of hard progression", Command.CYCLE_HARD_TILE),

    TILE_GAME_TILE("", "", Command.CLICK_TILE),
    TILE_HOVER_INFO("", "", Command.HOVER_TILE);

    private final String name;
    private final String hoverText;
    private final Command command;

    ButtonID(String name, String hoverText, Command command) {
        this.name = name;
        this.hoverText = hoverText;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public String getHoverText() {
        return hoverText;
    }

    public Command getCommand() {
        return command;
    }
}
