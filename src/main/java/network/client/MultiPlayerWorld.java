package main.java.network.client;

import main.java.game.Objects;
import main.java.game.World;
import main.java.math.Point3d;
import main.java.mesh.Vertex;
import main.java.game.GamePanel;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.standardobjects.Cuboid;
import main.java.world3d.object3d.standardobjects.Pyramid;
import main.java.world3d.object3d.standardobjects.Sphere;
import main.java.world3d.object3d.standardobjects.Torus;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Fredrik
 */
public class MultiPlayerWorld extends World {

    private Objects otherPlayers;

    public MultiPlayerWorld(GamePanel gamePanel) {
        super(gamePanel);
        objects = new Objects();
        otherPlayers = new Objects();

        objects.add(new Cuboid(new Vertex(2, 0, 0), 1, 1, 1));

        objects.add(new Sphere(0.5, new Vertex(2.5,2,0.5),20,20));

        objects.add(new Pyramid(1, 1,new Vertex(2.5,3.5,0)));

        objects.add(new Torus(0.6,0.3,20,20,new Vertex(2.5,5.5,0.3)));

        objects.add(new Torus(200,100,40,40,new Vertex(-350,80,0.3)));

        objects.add(new Sphere(25, new Vertex(2.5,70 ,13),20,20));

        objects.add(new Cuboid(new Vertex(0, 10, 0), 21, 21, 21));
        for (int i = 0; i < 100; i++) {
            objects.add(new Cuboid(new Point3d(Math.max(2, Math.random() * 20), Math.random() * 20+10, Math.random() * 20), 1, 1, 1));
        }

    }

    @Override
    public void update() {
        objects.update();
        player.update();
    }

    public void draw(Graphics g) {
        try {
            player.getCamera().drawProjectedObjects(objects.meshes(), g);
            player.getCamera().drawProjectedObjects(otherPlayers.meshes(),g);
        }catch (Exception e ){
            e.getStackTrace();
        }

    }

    @Override
    public void keyEvents() {
        player.keyEvents();
    }
    public void setOtherPlayers(ArrayList<Object3d> otherPlayers) {
        this.otherPlayers.clear();
        this.otherPlayers.addAll(otherPlayers);
    }
}
