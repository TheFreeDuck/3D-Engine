package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.mesh.Vertex;
import main.java.object3d.Orientation;

import java.util.HashMap;

public class CuboidMesh extends Mesh {
    double width;
    double height;
    double length;
    public CuboidMesh(Point3d origin,Orientation orientation, double height, double width, double length) {
        super(origin,orientation);
        this.width = width;
        this.height = height;
        this.length = length;
        
        // Create vertices
        vertices.add(new Vertex(origin));
        vertices.add(new Vertex( vertices.get(0).addDistanceAlongVector(orientation.getUp(),height)));
        vertices.add(new Vertex( vertices.get(1).addDistanceAlongVector(orientation.getRight(),width)));
        vertices.add(new Vertex( vertices.get(2).addDistanceAlongVector(orientation.getUp(),-height)));
        vertices.add(new Vertex( vertices.get(3).addDistanceAlongVector(orientation.getForward(),length)));
        vertices.add(new Vertex( vertices.get(4).addDistanceAlongVector(orientation.getUp(),height)));
        vertices.add(new Vertex( vertices.get(5).addDistanceAlongVector(orientation.getRight(),-width)));
        vertices.add(new Vertex( vertices.get(6).addDistanceAlongVector(orientation.getUp(),-height)));

        // Create edges
        edges = new HashMap<>();
        edges.put(0, new Edge(0,1)); //index 0
        edges.put(1, new Edge(0,7)); //index 1
        edges.put(2, new Edge(0,3)); //index 2
        edges.put(3, new Edge(1,2)); //index 3
        edges.put(4, new Edge(1,6)); //index 4
        edges.put(5, new Edge(2,3)); //index 5
        edges.put(6, new Edge(2,5)); //index 6
        edges.put(7, new Edge(3,4)); //index 7
        edges.put(8, new Edge(4,5)); //index 8
        edges.put(9, new Edge(4,7)); //index 9
        edges.put(10, new Edge(5,6)); //index 10
        edges.put(11, new Edge(6,7)); //index 11

        // Create triangles
        triangles = new HashMap<>();
        triangles.put(0, new Triangle(0, 2, 3));
        triangles.put(1, new Triangle(0, 1, 2));
        triangles.put(2, new Triangle(1, 5, 6));
        triangles.put(3, new Triangle(1, 2, 5));
        triangles.put(4, new Triangle(4, 5, 6));
        triangles.put(5, new Triangle(4, 6, 7));
        triangles.put(6, new Triangle(0, 4, 7));
        triangles.put(7, new Triangle(0, 3, 7));
        triangles.put(8, new Triangle(3, 6, 7));
        triangles.put(9, new Triangle(3, 5, 6));
        triangles.put(10, new Triangle(0, 1, 4));
        triangles.put(11, new Triangle(1, 4, 5));
    }
}

