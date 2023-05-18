package main.java.network.client;

import main.java.game.Game;
import main.java.game.World;
import main.java.network.server.PlayerData;
import main.java.app.Frame;
import main.java.game.GamePanel;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;
import main.java.world3d.object3d.standardobjects.Sphere;

import java.awt.*;
import java.util.ArrayList;

public class MultiPlayerGame extends Game{
    public MultiPlayerGame(World world, Frame frame, GamePanel gamePanel) {
        super(world, frame, gamePanel);
    }

    public PlayerData getPlayerData() {
        return new PlayerData(getWorld().getPlayer().getPosition(),new Orientation(getWorld().getPlayer().getOrientation()));
    }

    public void updatePlayerData(){

    }

    public void updateOtherPlayers(ArrayList<PlayerData> playerDataList){
        ArrayList<Object3d> otherPlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            if(playerData != null) {
                Sphere sphere = new Sphere(0.5, playerData.getPosition(), playerData.getOrientation(),20,20);
                sphere.getMesh().setColor(Color.green);
                otherPlayers.add(sphere);
            }
        }
            MultiPlayerWorld world = (MultiPlayerWorld) getWorld();
            world.setOtherPlayers(otherPlayers);


    }
}
