package main.java.world3d.object3d.standardobjects;

import main.java.math.Point3d;
import main.java.mesh.standardmeshes.SphereMesh;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;



public class Sphere extends Object3d  {
    double radius;
    Point3d center;

    private int nLongitudeSegments;

    private int nLatitudeSegments;
    public Sphere(double radius, Point3d center, int nLongitudeSegments, int nLatitudeSegments) {
        super(center,Orientation.standard());
        this.center= center;
        this.radius= radius;
        this.nLongitudeSegments= nLongitudeSegments;
        this.nLatitudeSegments = nLatitudeSegments;
        mesh = new SphereMesh(center,orientation, radius,nLongitudeSegments,nLatitudeSegments);

    }

    public Sphere(double radius, Point3d center, Orientation orientation, int nLongitudeSegments, int nLatitudeSegments) {
        super(center,orientation);
        this.center= center;
        this.radius= radius;
        this.nLongitudeSegments= nLongitudeSegments;
        this.nLatitudeSegments = nLatitudeSegments;
        mesh = new SphereMesh(center,orientation, radius,nLongitudeSegments,nLatitudeSegments);

    }

    @Override
    protected void updateMesh() {
        mesh = new SphereMesh(center,orientation, radius,nLongitudeSegments,nLatitudeSegments);
    }
}
