package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Vertex;
import main.java.object3d.Orientation;
import main.java.object3d.camera.PicturePlane;

public class CameraMesh extends Mesh {
    public CameraMesh(Point3d origin, Orientation orientation, PicturePlane picturePlane) {
        super(origin, orientation);

        vertices.add(new Vertex(origin));
        vertices.add(new Vertex(picturePlane.getP1()));
        vertices.add(new Vertex(picturePlane.getP2()));
        vertices.add(new Vertex(picturePlane.getP3()));
        vertices.add(new Vertex(picturePlane.getP4()));

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
