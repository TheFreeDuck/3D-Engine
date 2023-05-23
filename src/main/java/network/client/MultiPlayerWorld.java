package main.java.network.client;

import main.java.game.GamePanel;
import main.java.game.Objects;
import main.java.game.World;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Mesh;
import main.java.mesh.Vertex;
import main.java.object3d.standardobjects.Cuboid;
import main.java.object3d.standardobjects.Pyramid;
import main.java.object3d.standardobjects.Sphere;
import main.java.object3d.standardobjects.Torus;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Fredrik
 */
public class MultiPlayerWorld extends World {

    private ArrayList<Mesh> otherPlayers;
    private Sphere sphere;

    public MultiPlayerWorld(GamePanel gamePanel) {
        super(gamePanel);
        objects = new Objects();
        otherPlayers = new ArrayList<>();

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

        sphere = new Sphere(25, new Vertex(2.5, 70, 13), 20, 20);
        objects.add(sphere);

    }

    @Override
    public void update() {
        sphere.rotate(0.01,new Vector(0.5,0.2,1));
        objects.update();
        player.update();
    }

    public void draw(Graphics g) {
        try {
            player.getCamera().drawProjectedMeshes(objects.meshes(), g);
            player.getCamera().drawProjectedMeshes(otherPlayers,g);
        }catch (Exception e ){
            e.getStackTrace();
        }

    }

    @Override
    public void keyEvents() {
        player.keyEvents();
    }
    public void setOtherPlayers(ArrayList<Mesh> otherPlayers) {
        this.otherPlayers.clear();
        this.otherPlayers.addAll(otherPlayers);
    }
}
