package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.object3d.Orientation;
import main.java.object3d.camera.PicturePlane;

public class CameraMesh extends Mesh {

    public CameraMesh() {
        super();
        Vector forward = orientation.getForward();
        Vector right = orientation.getRight();
        Vector up = orientation.getUp();

        Point3d topLeft = origin
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, -1)
                .addDistanceAlongVector(up, 0.5);

        Point3d topRight = origin
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, 1)
                .addDistanceAlongVector(up, 0.5);

        Point3d bottomRight = origin
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, 1)
                .addDistanceAlongVector(up, -0.5);

        Point3d bottomLeft = origin
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, -1)
                .addDistanceAlongVector(up, -0.5);

        vertices.add(origin);
        vertices.add(topLeft);
        vertices.add(topRight);
        vertices.add(bottomRight);
        vertices.add(bottomLeft);

        edges.put(0, new Edge(1,2));
        edges.put(1, new Edge(2,3));
        edges.put(2, new Edge(3,4));
        edges.put(3, new Edge(4,1));
        edges.put(4, new Edge(0,1));
        edges.put(5, new Edge(0,2));
        edges.put(6, new Edge(0,3));
        edges.put(7, new Edge(0,4));

    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new CameraMesh(position, orientation, null);
    }

    public CameraMesh(Point3d origin, Orientation orientation, PicturePlane picturePlane) {
        super();
        this.origin = origin;
        this.orientation = orientation;


        vertices.add(origin);
        vertices.add(picturePlane.getP1());
        vertices.add(picturePlane.getP2());
        vertices.add(picturePlane.getP3());
        vertices.add(picturePlane.getP4());

        edges.put(0, new Edge(1,2));
        edges.put(1, new Edge(2,3));
        edges.put(2, new Edge(3,4));
        edges.put(3, new Edge(4,1));
        edges.put(4, new Edge(0,1));
        edges.put(5, new Edge(0,2));
        edges.put(6, new Edge(0,3));
        edges.put(7, new Edge(0,4));

    }
}
