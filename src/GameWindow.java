import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    private final Dimension GAME_WINDOW_SIZE;

    public GameWindow() {
        GAME_WINDOW_SIZE = new Dimension(800, 800);
    }

    public void initialize() {
        this.setPreferredSize(GAME_WINDOW_SIZE);
        this.setBackground(Color.BLACK);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point screenCenter = ge.getCenterPoint();

        JFrame frame = new JFrame("Tile Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(screenCenter.x - GAME_WINDOW_SIZE.width / 2,
                screenCenter.y - GAME_WINDOW_SIZE.height / 2);
        frame.setBackground(Color.BLACK);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        requestFocusInWindow();
    }
}