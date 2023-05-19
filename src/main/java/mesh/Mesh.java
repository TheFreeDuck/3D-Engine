package main.java.mesh;

import main.java.math.Point3d;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Mesh  {
    protected ArrayList<Vertex> vertices;
    protected HashMap<Integer, Edge> edges;
    protected HashMap<Integer, Triangle> triangles;

    protected Point3d origin;

    protected Orientation orientation;

    protected Color color;

    protected Mesh(Point3d origin, Orientation orientation) {
        this.orientation = orientation;
        this.origin = origin;
        vertices = new ArrayList<>();
        edges = new HashMap<>();
        triangles = new HashMap<>();

        color = Color.white;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public HashMap<Integer, Edge> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<Integer, Edge> edges) {
        this.edges = edges;
    }

    public HashMap<Integer, Triangle> getTriangles() {
        return triangles;
    }

    public void setFaces(HashMap<Integer, Triangle> triangles) {
        this.triangles = triangles;
    }

    public void setVertex(int i, Vertex vertex) {
        vertices.set(i, vertex);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
