import java.awt.*;
import java.util.ArrayList;

public class MenuDisplayPanel extends DisplayPanel {

    private RadioButton difficultyEasyButton;
    private RadioButton difficultyMediumButton;
    private RadioButton difficultyHardButton;
    private RadioButton boardSize3x3;
    private RadioButton boardSize3x5;
    private RadioButton boardSize5x5;
    private ActionButton backButton;
    private ArrayList<DisplayElement> menuElements;
    private ArrayList<DisplayElement> confirmStartElements;
    private ArrayList<DisplayElement> confirmQuitElements;

    public MenuDisplayPanel(Controller controller) {
        super(DisplayPanelID.MENU, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        initializeMenuElements();
        initializeConfirmStartElements();
        initializeConfirmQuitElements();

        backButton.disable();
        elements = menuElements;
    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case SET_GAME_MODE -> setGameMode((GameSettings.Difficulty) object);
            case SET_BOARD_SIZE -> setBoardSize((GameSettings.BoardSize) object);

            case HOVERING -> controller.react(Command.HOVERING, object);
            case NOT_HOVERING -> controller.react(Command.NOT_HOVERING, object);

            case CONFIRM_START -> elements = confirmStartElements;
            case CONFIRM_QUIT -> elements = confirmQuitElements;
            case NO_START, NO_QUIT, RESET -> elements = menuElements;

            case GAME_STARTED -> gameStarted();
            case GAME_ENDED -> gameEnded();
        }
    }

    private void setGameMode(GameSettings.Difficulty difficulty) {
        difficultyEasyButton.deactivate();
        difficultyMediumButton.deactivate();
        difficultyHardButton.deactivate();

        switch (difficulty) {
            case EASY -> difficultyEasyButton.activate();
            case MEDIUM -> difficultyMediumButton.activate();
            case HARD -> difficultyHardButton.activate();
        }
    }

    private void setBoardSize(GameSettings.BoardSize boardSize) {
        boardSize3x3.deactivate();
        boardSize3x5.deactivate();
        boardSize5x5.deactivate();

        switch (boardSize) {
            case SMALL_3X3 -> boardSize3x3.activate();
            case MEDIUM_3X5 -> boardSize3x5.activate();
            case LARGE_5X5 -> boardSize5x5.activate();
        }
    }

    private void gameStarted() {
        elements = menuElements;
        backButton.enable();
    }

    private void gameEnded() {
        elements = menuElements;
        backButton.disable();
    }

    private void initializeMenuElements() {
        addElement(new TextLabel("Settings Menu", new Point(127, 0), TextLabel.FontSize.LARGE, new Color(0.5f, 1.0f, 0.5f)));
        addElement(new TextLabel("Difficulty:", new Point(25, 55), TextLabel.FontSize.MEDIUM, Color.GREEN));
        addElement(new TextLabel("Easy", new Point(88,100), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new TextLabel("Medium", new Point(88, 200), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new TextLabel("Hard", new Point(88, 300), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new TextLabel("Board Size", new Point(300, 55), TextLabel.FontSize.MEDIUM, Color.GREEN));
        addElement(new TextLabel("3x3", new Point(363, 100), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new TextLabel("3x5", new Point(363, 200), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new TextLabel("5x5", new Point(363, 300), TextLabel.FontSize.SMALL, Color.LIGHT_GRAY));
        addElement(new StaticImage("Size_3x3", new Point(363, 130)));
        addElement(new StaticImage("Size_3x5", new Point(363, 230)));
        addElement(new StaticImage("Size_5x5", new Point(363, 330)));
        addElement(new Tile(ButtonID.TILE_MENU_EASY_0, new Point(88, 130), TileColor.RED, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_EASY_1, new Point(143, 130), TileColor.WHITE, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_MEDIUM_0, new Point(88, 230), TileColor.CYAN, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_MEDIUM_1, new Point(143, 230), TileColor.MAGENTA, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_MEDIUM_2, new Point(198, 230), TileColor.YELLOW, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_0, new Point(88, 330), TileColor.RED, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_1, new Point(143, 330), TileColor.ORANGE, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_2, new Point(198, 330), TileColor.YELLOW, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_3, new Point(108, 360), TileColor.GREEN, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_4, new Point(163, 360), TileColor.BLUE, Tile.Size.SMALL, controller));
        addElement(new Tile(ButtonID.TILE_MENU_HARD_5, new Point(218, 360), TileColor.PURPLE, Tile.Size.SMALL, controller));
        addElement(difficultyEasyButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_EASY, new Point(25, 110), controller));
        addElement(difficultyMediumButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_MEDIUM, new Point(25, 210), controller));
        addElement(difficultyHardButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_HARD, new Point(25, 310), controller));
        addElement(boardSize3x3 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_3X3, new Point(300, 110), controller));
        addElement(boardSize3x5 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_3X5, new Point(300, 210), controller));
        addElement(boardSize5x5 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_5X5, new Point(300, 310), controller));
        addElement(new ActionButton(ButtonID.BUTTON_START, new Point(25, 425), controller));
        addElement(backButton = new ActionButton(ButtonID.BUTTON_BACK, new Point(187, 425), controller));
        addElement(new ActionButton(ButtonID.BUTTON_QUIT, new Point(350, 425), controller));
        menuElements = elements;
    }

    private void initializeConfirmStartElements() {
        elements = new ArrayList<>();
        addElement(new StaticImage("GameInProgressPrompt", new Point(100, 175)));
        addElement(new ActionButton(ButtonID.BUTTON_YES_START, new Point(120, 260), controller));
        addElement(new ActionButton(ButtonID.BUTTON_NO_START, new Point(255, 260), controller));
        confirmStartElements = elements;
    }

    private void initializeConfirmQuitElements() {
        elements = new ArrayList<>();
        addElement(new StaticImage("ConfirmQuitPrompt", new Point(100, 175)));
        addElement(new ActionButton(ButtonID.BUTTON_YES_QUIT, new Point(120, 260), controller));
        addElement(new ActionButton(ButtonID.BUTTON_NO_QUIT, new Point(255, 260), controller));
        confirmQuitElements = elements;
    }
}
