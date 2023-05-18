package main.java.world3d.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.mesh.standardmeshes.CuboidMesh;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;

/**
 * @author Fredrik
 */
public class Cuboid extends Object3d  {

    private double w;
    private double h;
    private double l;

    public Cuboid(Point3d p1, double w, double h, double l) {
        super(p1,Orientation.standard());
        mesh = new CuboidMesh(p1,orientation,h,w,l);
        this.w = w;
        this.h = h;
        this.l = l;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public double getL() {
        return l;
    }

    @Override
    protected void updateMesh() {
        mesh = new CuboidMesh(position,orientation,h,w,l);
    }
}
