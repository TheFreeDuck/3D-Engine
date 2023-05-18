package main.java.network.client;

import main.java.game.Game;
import main.java.game.World;
import main.java.network.server.PlayerData;
import main.java.window.Frame;
import main.java.game.GamePanel;

public class MultiPlayerGame extends Game{
    public MultiPlayerGame(World world, Frame frame, GamePanel gamePanel) {
        super(world, frame, gamePanel);
    }

    public PlayerData getPlayerData() {
        return new PlayerData(getWorld().getPlayer().getPosition(),getWorld().getPlayer().getOrientation());
    }

    public void updatePlayerData(){

    }
}
