package main.java.world3d.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.standardmeshes.TorusMesh;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;

public class Torus extends Object3d {
    public Torus(double radius1, double radius2, int nSides, int nRings, Point3d center) {
        super(center,Orientation.standard());
        mesh = new TorusMesh(center,Orientation.standard(),radius1, radius2, nSides, nRings);

    }
}
