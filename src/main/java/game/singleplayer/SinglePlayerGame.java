package main.java.game.singleplayer;

import main.java.game.Game;
import main.java.game.World;
import main.java.window.Frame;
import main.java.game.GamePanel;

public class SinglePlayerGame {
    public static void main(String[] args) {
        Frame frame = new Frame();
        GamePanel gamePanel = new GamePanel();
        World world = new SinglePlayerWorld(gamePanel);
        Game game = new Game(world, frame, gamePanel);
        game.start();
    }
}
