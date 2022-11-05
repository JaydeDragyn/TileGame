public enum ButtonID {
    // Action Buttons
    BUTTON_MENU("Menu", "Show the settings menu", Command.SHOW_MENU),
    BUTTON_HELP("Help", "Show the help/info", Command.SHOW_HELP),
    BUTTON_START("Start", "Start a new game", Command.START_NEW_GAME),
    BUTTON_BACK("Back", "Go back to the game in progress", Command.RETURN_TO_GAME),
    BUTTON_QUIT("Quit", "Quit the game", Command.QUIT_GAME),
    BUTTON_PREV("Prev", "Show the previous info page", Command.PREV_HELP_PAGE),
    BUTTON_NEXT("Next", "Show the next info page", Command.NEXT_HELP_PAGE),

    // Radio buttons
    BUTTON_DIFFICULTY_EASY("Difficulty Easy", "Set difficulty to Easy", Command.GAME_MODE_EASY),
    BUTTON_DIFFICULTY_MEDIUM("Difficulty Medium", "Set difficulty to Medium", Command.GAME_MODE_MEDIUM),
    BUTTON_DIFFICULTY_HARD("Difficulty Hard", "Set difficulty to Hard", Command.GAME_MODE_HARD),
    BUTTON_BOARD_SIZE_3X3("Board Size 3x3", "Set board size to 3x3", Command.BOARD_SIZE_3X3),
    BUTTON_BOARD_SIZE_3X5("Board Size 3x5", "Set board size to 3x5", Command.BOARD_SIZE_3X5),
    BUTTON_BOARD_SIZE_5X5("Board Size 5x5", "Set board size to 5x5", Command.BOARD_SIZE_5X5),

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
    TILE_MENU_HARD_5("5", "Change target color of hard progression", Command.CYCLE_HARD_TILE);

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
