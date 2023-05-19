package test;

import main.java.app.Frame;
import main.java.game.GamePanel;
import main.java.game.World;
import main.java.network.client.Client;
import main.java.network.client.MultiPlayerGame;
import main.java.network.client.MultiPlayerWorld;
import main.java.network.server.ServerApp;

public class MultiPlayerTest {
    public static void main(String[] args){
        new Thread (() -> {
        ServerApp.main(args);
        }).start();
        new Thread (() -> {
            Frame frame = new Frame();
            GamePanel gamePanel = new GamePanel();
            World world = new MultiPlayerWorld(gamePanel);
            MultiPlayerGame game = new MultiPlayerGame(world, frame , gamePanel);

            Client client = new Client("localhost", 6969,game);
            client.start();


            game.start();
            frame.setVisible(true);
        }).start();

        new Thread (() -> {
            Frame frame2 = new Frame();
            GamePanel gamePanel2 = new GamePanel();
            World world2 = new MultiPlayerWorld(gamePanel2);
            MultiPlayerGame game2 = new MultiPlayerGame(world2, frame2 , gamePanel2);

            Client client2 = new Client("localhost", 6969,game2);
            client2.start();


            game2.start();
            frame2.setVisible(true);
        }).start();

    }
}
