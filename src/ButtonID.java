public enum ButtonID {
    BUTTON_MENU("Menu", "Show the settings menu", Command.SHOW_MENU),
    BUTTON_HELP("Help", "Show the help/info", Command.SHOW_HELP),
    BUTTON_START("Start", "Start a new game", Command.START_NEW_GAME),
    BUTTON_BACK("Back", "Go back to the game in progress", Command.RETURN_TO_GAME),
    BUTTON_QUIT("Quit", "Quit the game", Command.QUIT_GAME),
    BUTTON_PREV("Prev", "Show the previous info page", Command.PREV_HELP_PAGE),
    BUTTON_NEXT("Next", "Show the next info page", Command.NEXT_HELP_PAGE),
    BUTTON_DIFFICULTY_EASY("Difficulty Easy", "Set difficulty to Easy", Command.GAME_MODE_EASY),
    BUTTON_DIFFICULTY_MEDIUM("Difficulty Medium", "Set difficulty to Medium", Command.GAME_MODE_MEDIUM),
    BUTTON_DIFFICULTY_HARD("Difficulty Hard", "Set difficulty to Hard", Command.GAME_MODE_HARD),
    BUTTON_BOARD_SIZE_3X3("Board Size 3x3", "Set board size to 3x3", Command.BOARD_SIZE_3X3),
    BUTTON_BOARD_SIZE_3X5("Board Size 3x5", "Set board size to 3x5", Command.BOARD_SIZE_3X5),
    BUTTON_BOARD_SIZE_5X5("Board Size 5x5", "Set board size to 5x5", Command.BOARD_SIZE_5X5);

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
