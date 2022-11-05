import java.awt.*;

public class MenuDisplayPanel extends DisplayPanel {

    private RadioButton difficultyEasyButton;
    private RadioButton difficultyMediumButton;
    private RadioButton difficultyHardButton;
    private RadioButton boardSize3x3;
    private RadioButton boardSize3x5;
    private RadioButton boardSize5x5;
    private ActionButton startButton;
    private ActionButton backButton;
    private ActionButton quitButton;

    public MenuDisplayPanel(Controller controller) {
        super(DisplayPanelID.MENU, new Point(150,150), controller);
        size = new Dimension(500, 500);
    }

    @Override
    public void initialize() {
        difficultyEasyButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_EASY, new Point(25, 110), controller);
        difficultyMediumButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_MEDIUM, new Point(25, 210), controller);
        difficultyHardButton = new RadioButton(ButtonID.BUTTON_DIFFICULTY_HARD, new Point(25, 310), controller);

        boardSize3x3 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_3X3, new Point(300, 110), controller);
        boardSize3x5 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_3X5, new Point(300, 210), controller);
        boardSize5x5 = new RadioButton(ButtonID.BUTTON_BOARD_SIZE_5X5, new Point(300, 310), controller);

        startButton = new ActionButton(ButtonID.BUTTON_START, new Point(25, 425), controller);
        backButton = new ActionButton(ButtonID.BUTTON_BACK, new Point(187, 425), controller);
        quitButton = new ActionButton(ButtonID.BUTTON_QUIT, new Point(350, 425), controller);

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

        addElement(difficultyEasyButton);
        addElement(difficultyMediumButton);
        addElement(difficultyHardButton);
        addElement(boardSize3x3);
        addElement(boardSize3x5);
        addElement(boardSize5x5);
        addElement(startButton);
        addElement(backButton);
        addElement(quitButton);

    }

    @Override
    public void react(Command command, Object object) {
        switch (command) {
            case HOVERING -> controller.react(Command.HOVERING, object);
            case NOT_HOVERING -> controller.react(Command.NOT_HOVERING, object);
        }
    }
}
