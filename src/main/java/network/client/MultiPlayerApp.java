package main.java.network.client;

import main.java.game.Game;
import main.java.game.GamePanel;
import main.java.game.World;
import main.java.window.Frame;

public class MultiPlayerApp {
    public static void main(String[] args) {
        Frame frame = new Frame();
        GamePanel gamePanel = new GamePanel();
        World world = new MultiPlayerWorld(gamePanel);
        Game game = new Game(world, frame , gamePanel);

        Client client = ClientInput.getClient(game);
        client.start();


        game.start();

    }
}
