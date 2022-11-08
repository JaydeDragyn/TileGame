/* *****************************************************************************
TileGame - a puzzle game with tiles
Copyright (C) 2022  Jayde Dragyn

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

See License.MD for full GNU GPLv3.0
*******************************************************************************/

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
