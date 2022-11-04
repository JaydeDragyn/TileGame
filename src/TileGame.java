import javax.swing.*;

public class TileGame {

    // Entry point and starting up the EDT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TileGame::createGame);
    }

    public static void createGame() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.initialize();
    }

}
