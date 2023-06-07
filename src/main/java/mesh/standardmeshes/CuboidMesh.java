package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Edge;
import main.java.mesh.Face;
import main.java.mesh.Mesh;
import main.java.object3d.Orientation;

import java.util.ArrayList;
import java.util.List;

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

        /*// Create triangles
        faces = new ArrayList<>();
        faces.add(new Face(0, 1, 2));   // Front face (triangle 1)
        faces.add(new Face(2, 3, 0));   // Front face (triangle 2)
        faces.add(new Face(1, 6, 5));   // Top face (triangle 1)
        faces.add(new Face(5, 2, 1));   // Top face (triangle 2)
        faces.add(new Face(2, 5, 4));   // Right face (triangle 1)
        faces.add(new Face(4, 3, 2));   // Right face (triangle 2)
        faces.add(new Face(1, 6, 7));   // Left face (triangle 1)
        faces.add(new Face(7, 0, 1));   // Left face (triangle 2)
        faces.add(new Face(0, 3, 4));   // Bottom face (triangle 1)
        faces.add(new Face(4, 7, 0));   // Bottom face (triangle 2)
        faces.add(new Face(6, 5, 4));   // Back face (triangle 1)
        faces.add(new Face(4, 7, 6));   // Back face (triangle 2)*/

        // Create quads
        faces = new ArrayList<>();
        faces.add(new Face(0, 1, 2, 3));   // Front face
        faces.add(new Face(6, 5, 2, 1));   // Top face
        faces.add(new Face(5, 4, 3, 2));   // Right face
        faces.add(new Face(1, 6, 7, 0));   // Left face
        faces.add(new Face(7, 4, 3, 0));  // Bottom face
        faces.add(new Face(6, 5, 4, 7));   // Back face





    }

    public CuboidMesh(Point3d origin, Orientation orientation, double height, double width, double length, List<Face> faceList) {
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
        this.faces =  faceList;
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new CuboidMesh(position, orientation, height, width, length, faces);
    }
}
