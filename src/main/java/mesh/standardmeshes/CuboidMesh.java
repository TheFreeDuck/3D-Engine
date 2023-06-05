package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Face;
import main.java.object3d.Orientation;

import java.util.ArrayList;

public class CuboidMesh extends Mesh {
    double width;
    double height;
    double length;

    public CuboidMesh(double height, double width, double length) {
        super();
        this.width = width;
        this.height = height;
        this.length = length;

        // Create vertices
        vertices.add(origin);
        vertices.add(vertices.get(0).addDistanceAlongVector(orientation.getUp(), height));
        vertices.add(vertices.get(1).addDistanceAlongVector(orientation.getRight(), width));
        vertices.add(vertices.get(2).addDistanceAlongVector(orientation.getUp(), -height));
        vertices.add(vertices.get(3).addDistanceAlongVector(orientation.getForward(), length));
        vertices.add(vertices.get(4).addDistanceAlongVector(orientation.getUp(), height));
        vertices.add(vertices.get(5).addDistanceAlongVector(orientation.getRight(), -width));
        vertices.add(vertices.get(6).addDistanceAlongVector(orientation.getUp(), -height));

        // Create edges
        edges = new ArrayList<>();
        edges.add(new Edge(0, 1)); // index 0
        edges.add(new Edge(0, 7)); // index 1
        edges.add(new Edge(0, 3)); // index 2
        edges.add(new Edge(1, 2)); // index 3
        edges.add(new Edge(1, 6)); // index 4
        edges.add(new Edge(2, 3)); // index 5
        edges.add(new Edge(2, 5)); // index 6
        edges.add(new Edge(3, 4)); // index 7
        edges.add(new Edge(4, 5)); // index 8
        edges.add(new Edge(4, 7)); // index 9
        edges.add(new Edge(5, 6)); // index 10
        edges.add(new Edge(6, 7)); // index 11

        // Create triangles
        faces = new ArrayList<>();
        faces.add(new Face(0, 2, 3));
        faces.add(new Face(0, 1, 2));
        faces.add(new Face(1, 5, 6));
        faces.add(new Face(1, 2, 5));
        faces.add(new Face(4, 5, 6));
        faces.add(new Face(4, 6, 7));
        faces.add(new Face(0, 4, 7));
        faces.add(new Face(0, 3, 7));
        faces.add(new Face(3, 6, 7));
        faces.add(new Face(3, 5, 6));
        faces.add(new Face(0, 1, 4));
        faces.add(new Face(1, 4, 5));
    }

    public CuboidMesh(Point3d origin, Orientation orientation, double height, double width, double length) {
        super();
        this.origin = origin;
        this.orientation = orientation;

        this.width = width;
        this.height = height;
        this.length = length;

        // Create vertices
        vertices.add(origin);
        vertices.add(vertices.get(0).addDistanceAlongVector(this.orientation.getUp(), height));
        vertices.add(vertices.get(1).addDistanceAlongVector(this.orientation.getRight(), width));
        vertices.add(vertices.get(2).addDistanceAlongVector(this.orientation.getUp(), -height));
        vertices.add(vertices.get(3).addDistanceAlongVector(this.orientation.getForward(), length));
        vertices.add(vertices.get(4).addDistanceAlongVector(this.orientation.getUp(), height));
        vertices.add(vertices.get(5).addDistanceAlongVector(this.orientation.getRight(), -width));
        vertices.add(vertices.get(6).addDistanceAlongVector(this.orientation.getUp(), -height));

        // Create edges
        edges = new ArrayList<>();
        edges.add(new Edge(0, 1)); // index 0
        edges.add(new Edge(0, 7)); // index 1
        edges.add(new Edge(0, 3)); // index 2
        edges.add(new Edge(1, 2)); // index 3
        edges.add(new Edge(1, 6)); // index 4
        edges.add(new Edge(2, 3)); // index 5
        edges.add(new Edge(2, 5)); // index 6
        edges.add(new Edge(3, 4)); // index 7
        edges.add(new Edge(4, 5)); // index 8
        edges.add(new Edge(4, 7)); // index 9
        edges.add(new Edge(5, 6)); // index 10
        edges.add(new Edge(6, 7)); // index 11

        // Create triangles
        faces = new ArrayList<>();
        faces.add(new Face(0, 2, 3));
        faces.add(new Face(0, 1, 2));
        faces.add(new Face(1, 5, 6));
        faces.add(new Face(1, 2, 5));
        faces.add(new Face(4, 5, 6));
        faces.add(new Face(4, 6, 7));
        faces.add(new Face(0, 4, 7));
        faces.add(new Face(0, 3, 7));
        faces.add(new Face(3, 6, 7));
        faces.add(new Face(3, 5, 6));
        faces.add(new Face(0, 1, 4));
        faces.add(new Face(1, 4, 5));
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new CuboidMesh(position, orientation, height, width, length);
    }
}
