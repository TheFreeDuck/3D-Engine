package test;

import main.java.game.Game;
import main.java.game.GamePanel;
import main.java.game.World;
import main.java.network.client.*;
import main.java.network.server.ServerApp;
import main.java.app.Frame;

public class MultiPlayerTest {
    public static void main(String[] args) {
        ServerApp.main(args);
        Frame frame = new Frame();
        GamePanel gamePanel = new GamePanel();
        World world = new MultiPlayerWorld(gamePanel);
        MultiPlayerGame game = new MultiPlayerGame(world, frame , gamePanel);

        Client client = new Client("localhost", 6969,game);
        client.start();


        game.start();
        frame.setVisible(true);

        Frame frame2 = new Frame();
        GamePanel gamePanel2 = new GamePanel();
        World world2 = new MultiPlayerWorld(gamePanel2);
        Game game2 = new Game(world2, frame2 , gamePanel2);

        Client client2 = new Client("localhost", 6969,game);
        client2.start();


        game2.start();
        frame2.setVisible(true);
    }
}
