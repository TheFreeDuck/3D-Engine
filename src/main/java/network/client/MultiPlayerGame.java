package main.java.network.client;

import main.java.app.Frame;
import main.java.game.Game;
import main.java.game.GamePanel;
import main.java.game.World;
import main.java.mesh.Mesh;
import main.java.mesh.meshloaders.ObjMesh;
import main.java.network.server.PlayerData;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerGame extends Game{
    public MultiPlayerGame(World world, Frame frame, GamePanel gamePanel) {
        super(world, frame, gamePanel);
    }

    public PlayerData getPlayerData() {
        return new PlayerData(getWorld().getPlayer().getPosition(),new Orientation(getWorld().getPlayer().getOrientation()));
    }

    public void updatePlayerData(){

    }

    public void updateOtherPlayers(List<PlayerData> playerDataList){
        List<Mesh> otherPlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            if(playerData != null) {
                Mesh playerMesh = new ObjMesh(getClass().getClassLoader().getResourceAsStream("SuzanMonkey.obj"));
                playerMesh.setColor(Color.green);
                otherPlayers.add(playerMesh);
            }
        }
            MultiPlayerWorld world = (MultiPlayerWorld) getWorld();
            world.setOtherPlayers(otherPlayers);


    }
}
