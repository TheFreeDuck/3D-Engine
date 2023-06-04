package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.object3d.Orientation;

import java.util.ArrayList;

public class PyramidMesh extends Mesh {
    double baseWidth;
    double height;

    public PyramidMesh(double baseWidth, double height) {
        super();
        this.baseWidth = baseWidth;
        this.height = height;

        // Create vertices
        vertices.add(origin);
        vertices.add(vertices.get(0).addXYZ(-baseWidth / 2.0, -baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(-baseWidth / 2.0, baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(baseWidth / 2.0, baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(baseWidth / 2.0, -baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(0, 0, height));

        // Create edges
        edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(0, 3));
        edges.add(new Edge(0, 4));
        edges.add(new Edge(1, 2));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(1, 5));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));

        // Create triangles
        triangles = new ArrayList<>();
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
        triangles.add(new Triangle(0, 3, 4));
        triangles.add(new Triangle(0, 4, 1));
        triangles.add(new Triangle(1, 2, 5));
        triangles.add(new Triangle(2, 3, 5));
        triangles.add(new Triangle(3, 4, 5));
        triangles.add(new Triangle(4, 1, 5));
    }

    public PyramidMesh(Point3d origin, Orientation orientation, double baseWidth, double height) {
        super();
        this.origin = origin;
        this.orientation = orientation;

        this.baseWidth = baseWidth;
        this.height = height;

        // Create vertices
        vertices.add(origin);
        vertices.add(vertices.get(0).addXYZ(-baseWidth / 2.0, -baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(-baseWidth / 2.0, baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(baseWidth / 2.0, baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(baseWidth / 2.0, -baseWidth / 2.0, 0));
        vertices.add(vertices.get(0).addXYZ(0, 0, height));

        // Create edges
        edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(0, 3));
        edges.add(new Edge(0, 4));
        edges.add(new Edge(1, 2));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(1, 5));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));

        // Create triangles
        triangles = new ArrayList<>();
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
        triangles.add(new Triangle(0, 3, 4));
        triangles.add(new Triangle(0, 4, 1));
        triangles.add(new Triangle(1, 2, 5));
        triangles.add(new Triangle(2, 3, 5));
        triangles.add(new Triangle(3, 4, 5));
        triangles.add(new Triangle(4, 1, 5));
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new PyramidMesh(position, orientation, baseWidth, height);
    }
}
