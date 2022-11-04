import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JPanel {

    private final Dimension GAME_WINDOW_SIZE;
    private DisplayElement displayPanel;

    public GameWindow() {
        GAME_WINDOW_SIZE = new Dimension(800, 800);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D screen = (Graphics2D) g;
        DisplayElement.drawElement(screen, displayPanel);
    }

    public void initialize(DisplayElement displayPanel) {
        this.displayPanel = displayPanel;
        initializeWindow();
        initializeListeners();
    }

    private void initializeWindow() {
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

    private void initializeListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                displayPanel.mouseMovedOn(new Point(e.getX(), e.getY()));
                paintComponent(getGraphics());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                displayPanel.mousePressed(new Point(e.getX(), e.getY()));
                paintComponent(getGraphics());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                displayPanel.mouseReleased(new Point(e.getX(), e.getY()));
                paintComponent(getGraphics());
            }
        });
    }
}