package main.java.network.client;

import main.java.game.GamePanel;
import main.java.game.Objects;
import main.java.game.World;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Mesh;
import main.java.mesh.meshloaders.ObjMesh;
import main.java.mesh.standardmeshes.CuboidMesh;
import main.java.mesh.standardmeshes.PyramidMesh;
import main.java.mesh.standardmeshes.SphereMesh;
import main.java.mesh.standardmeshes.TorusMesh;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fredrik
 */
public class MultiPlayerWorld extends World {

    Object3d plane;
    private List<Mesh> otherPlayers;

    public MultiPlayerWorld(GamePanel gamePanel) {
        super(gamePanel);
        objects = new Objects();
        otherPlayers = new ArrayList<>();

        objects.add(new Object3d(new Point3d(2,0,0),Orientation.standard(),new CuboidMesh(1,1,1)));
        objects.add(new Object3d(new Point3d(2.5,2,0.5),Orientation.standard(),new SphereMesh(0.5,20,20)));
        objects.add(new Object3d(new Point3d(2.5,3.5,0),Orientation.standard(),new PyramidMesh(1,1)));

        objects.add(new Object3d(new Point3d(2.5,5.5,0.3),Orientation.standard(),new TorusMesh(0.6,0.3,20,20)));

        Object3d torus = new Object3d(new Point3d(-350,80,0.3),Orientation.standard(),new TorusMesh(200,100,40,40));
        torus.setRotationVelocity(new Vector(0,0,0.04));

        objects.add(torus);

        //objects.add(new Sphere(25, new Point3d(2.5,70 ,13),20,20));

        //objects.add(new Object3d(new Point3d(0, 10, 0), Orientation.standard(),new CuboidMesh(21, 21, 21)));
        //objects.add(player);
        for (int i = 0; i < 100; i++) {
            objects.add(new Object3d(new Point3d(Math.max(2, Math.random() * 20), Math.random() * 20+10, Math.random() * 20), Orientation.standard(),new CuboidMesh(1, 1, 1)));
        }

        Object3d sphere2 = new Object3d(new Point3d(2.5,70 ,13),Orientation.standard(), new SphereMesh(25,20,20));
        objects.add(sphere2);
        sphere2.setRotationVelocity(new Vector(0.02,0.05,0.08));

        Object3d monk = new Object3d(new Point3d(3,-2,0),new Orientation(new Vector(-1,0,0),new Vector(0,-1,0)),new ObjMesh(getClass().getClassLoader().getResourceAsStream("SuzanMonkey.obj")));
        objects.add(monk);
        monk.setRotationVelocity(new Vector(0.002,0.05,0.08));

        plane = new Object3d(new Point3d(800,-900,0),new Orientation(new Vector(-1,0,0),new Vector(0,-1,0)),new ObjMesh(getClass().getClassLoader().getResourceAsStream("Plane.obj")));
        objects.add(plane);
        plane.setRotationVelocity(new Vector(0,0,-0.15));

        //monk.setVelocity(new Vector(0.01,0,0));

        //objects.add(new ObjObject(new Point3d(10,-30,0),new Orientation(new Vector(-1,0,0),new Vector(0,0,-1)),"glider.obj"));
    }

    @Override
    public void update() {
        plane.setVelocity(plane.getOrientation().getForward().setScalar(-30));
        objects.update();
        player.update();
    }

    public void draw(Graphics g) {
        try {
            List<Mesh> meshes = new ArrayList<>(objects.meshes());
            meshes.addAll(otherPlayers);
            player.getCamera().drawProjectedMeshes(meshes, g);
        }catch (Exception e ){
            e.getStackTrace();
        }

    }

    @Override
    public void keyEvents() {
        player.keyEvents();
    }
    public void setOtherPlayers(List<Mesh> otherPlayers) {
        this.otherPlayers.clear();
        this.otherPlayers.addAll(otherPlayers);
    }
}
