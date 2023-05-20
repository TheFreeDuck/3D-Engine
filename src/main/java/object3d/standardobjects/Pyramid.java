package main.java.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.mesh.standardmeshes.PyramidMesh;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;



public class Pyramid extends Object3d  {

    double baseWidth, height;

    public Pyramid(double baseWidth, double height, Point3d position) {
        super(position, Orientation.standard());
        this.baseWidth = baseWidth;
        this.height = height;
        this.position = position;
        mesh = new PyramidMesh(position,orientation, baseWidth, height);
    }

    @Override
    protected void updateMesh() {
        mesh = new PyramidMesh(position, orientation, baseWidth, height);
    }
}