import javax.swing.*;

public class TileGame {

    // Entry point and starting up the EDT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TileGame::createGame);
    }

    public static void createGame() {
        TileGameController tileGameController = new TileGameController();
        tileGameController.initialize();

        GameWindow gameWindow = new GameWindow();
        gameWindow.initialize(tileGameController.getDisplayPanel());
    }

}
