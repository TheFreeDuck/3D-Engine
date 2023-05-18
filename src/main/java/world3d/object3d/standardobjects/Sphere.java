package main.java.world3d.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.mesh.standardmeshes.SphereMesh;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;



public class Sphere extends Object3d  {
    double radius;

    private int nLongitudeSegments;

    private int nLatitudeSegments;
    public Sphere(double radius, Point3d position, int nLongitudeSegments, int nLatitudeSegments) {
        super(position,Orientation.standard());
        this.radius= radius;
        this.nLongitudeSegments= nLongitudeSegments;
        this.nLatitudeSegments = nLatitudeSegments;
        mesh = new SphereMesh(position,orientation, radius,nLongitudeSegments,nLatitudeSegments);

    }

    public Sphere(double radius, Point3d position, Orientation orientation, int nLongitudeSegments, int nLatitudeSegments) {
        super(position,orientation);
        this.radius= radius;
        this.nLongitudeSegments= nLongitudeSegments;
        this.nLatitudeSegments = nLatitudeSegments;
        mesh = new SphereMesh(position,orientation, radius,nLongitudeSegments,nLatitudeSegments);

    }

    @Override
    protected void updateMesh() {
        mesh = new SphereMesh(position,orientation, radius,nLongitudeSegments,nLatitudeSegments);
    }
}
