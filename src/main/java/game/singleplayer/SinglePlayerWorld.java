package main.java.game.singleplayer;

import main.java.game.GamePanel;
import main.java.game.Objects;
import main.java.game.World;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Vertex;
import main.java.object3d.standardobjects.Cuboid;
import main.java.object3d.standardobjects.Pyramid;
import main.java.object3d.standardobjects.Sphere;
import main.java.object3d.standardobjects.Torus;

import java.awt.*;

/**
 * @author Fredrik
 */
public class SinglePlayerWorld extends World {

    public SinglePlayerWorld(GamePanel gamePanel) {
        super(gamePanel);
        objects = new Objects();

        objects.add(new Cuboid(new Vertex(2, 0, 0), 1, 1, 1));

        objects.add(new Sphere(0.5, new Vertex(2.5,2,0.5),20,20));
        objects.add(new Pyramid(1, 1,new Vertex(2.5,3.5,0)));

        objects.add(new Torus(0.6,0.3,20,20,new Vertex(2.5,5.5,0.3)));

        Torus torus = new Torus(200,100,40,40,new Vertex(-350,80,0.3));
        torus.setRotation(new Vector(0,0,0.04));

        objects.add(torus);

        //objects.add(new Sphere(25, new Vertex(2.5,70 ,13),20,20));

        objects.add(new Cuboid(new Vertex(0, 10, 0), 21, 21, 21));
        //objects.add(player);
        for (int i = 0; i < 100; i++) {
            objects.add(new Cuboid(new Point3d(Math.max(2, Math.random() * 20), Math.random() * 20+10, Math.random() * 20), 1, 1, 1));
        }

        Sphere sphere = new Sphere(25, new Vertex(2.5,70 ,13),20,20);
        objects.add(sphere);
        sphere.setRotation(new Vector(0.02,0.05,0.08));
    }

    @Override
    public void update() {
        objects.update();
        player.update();
    }

    public void draw(Graphics g) {
        player.getCamera().drawProjectedObjects(objects.meshes(), g);
        //player.camera2.drawProjectedObjects(objects.meshes(), g);
    }

    @Override
    public void keyEvents() {
        player.keyEvents();
    }
}
