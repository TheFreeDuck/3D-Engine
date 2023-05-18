package main.java.world3d.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.mesh.standardmeshes.TorusMesh;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;

public class Torus extends Object3d {
    private double radius1;
    private double radius2;
    private int nSides;
    private int nRings;

    public Torus(double radius1, double radius2, int nSides, int nRings, Point3d position) {
        super(position,Orientation.standard());
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.nSides = nSides;
        this.nRings = nRings;
        mesh = new TorusMesh(position,Orientation.standard(),radius1, radius2, nSides, nRings);

    }

    @Override
    protected void updateMesh() {
        mesh = new TorusMesh(position,orientation,radius1, radius2, nSides, nRings);
    }
}
