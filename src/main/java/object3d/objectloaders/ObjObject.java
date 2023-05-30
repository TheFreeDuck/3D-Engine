package main.java.object3d.objectloaders;

import main.java.math.Point3d;
import main.java.mesh.meshloaders.ObjMesh;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;

/**
 * @author Fredrik
 */
public class ObjObject extends Object3d  {

    String path;

    public ObjObject(Point3d p1, String path) {
        super(p1,Orientation.standard());
        this.path = path;
        mesh = new ObjMesh(p1,Orientation.standard(),getClass().getClassLoader().getResourceAsStream(path));
    }

    public ObjObject(Point3d p1,Orientation orientation, String path) {
        super(p1,orientation);
        this.path = path;
        mesh = new ObjMesh(p1,orientation,getClass().getClassLoader().getResourceAsStream(path));
    }

    @Override
    protected void updateMesh() {
        mesh = new ObjMesh(position,orientation,getClass().getClassLoader().getResourceAsStream(path));
    }
}
