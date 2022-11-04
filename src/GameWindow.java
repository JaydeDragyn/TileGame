import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JPanel {

    private final Dimension GAME_WINDOW_SIZE;
    private final TileGameDisplayPanel tileGameDisplayPanel;

    public GameWindow() {
        GAME_WINDOW_SIZE = new Dimension(800, 800);
        tileGameDisplayPanel = new TileGameDisplayPanel();
        tileGameDisplayPanel.initialize();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D screen = (Graphics2D) g;
        DisplayElement.drawElement(screen, tileGameDisplayPanel);
    }


    public void initialize() {
        initializeWindow();
        initializeListeners();
    }

    public void initializeWindow() {
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
        frame.setIconImage(new StaticImage(null, "Tile Game Icon").getTexture());
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        requestFocusInWindow();
    }

    public void initializeListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }
}