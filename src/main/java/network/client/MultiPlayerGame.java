package main.java.network.client;

import main.java.app.Frame;
import main.java.game.Game;
import main.java.game.GamePanel;
import main.java.game.World;
import main.java.mesh.Mesh;
import main.java.mesh.standardmeshes.CameraMesh;
import main.java.network.server.PlayerData;
import main.java.object3d.Orientation;

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
        ArrayList<Mesh> otherPlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            if(playerData != null) {
                Mesh playerMesh = new CameraMesh(playerData.getPosition(), playerData.getOrientation());
                playerMesh.setColor(Color.green);
                otherPlayers.add(playerMesh);
            }
        }
            MultiPlayerWorld world = (MultiPlayerWorld) getWorld();
            world.setOtherPlayers(otherPlayers);


    }
}
