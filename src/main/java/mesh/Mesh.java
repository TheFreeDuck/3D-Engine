package main.java.mesh;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Mesh  {
    protected List<Point3d> vertices;
    protected List<Edge> edges;
    protected List<Face> faces;

    protected Point3d origin;

    protected Orientation orientation;

    protected Mesh() {
        this.orientation = Orientation.standard();
        this.origin = new Point3d(10,0,0);
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        faces = new ArrayList<>();
    }

    public abstract Mesh update(Point3d position, Orientation orientation);

    public void move(Point3d position) {
        Vector translationVector = position.toVector().subtract(this.origin.toVector());

        for(Point3d vertex : vertices) {
            vertex.setPoint(vertex.addVector(translationVector));
        }

        this.origin = position;
    }

    public void rotateToOrientation(Orientation targetOrientation) {
        targetOrientation.rotate(0.01,Orientation.standard().getForward());
        double angleX = targetOrientation.getForward().angleBetweenVector(orientation.getForward());
        double angleY = targetOrientation.getRight().angleBetweenVector(orientation.getRight());
        for (Point3d vertex : vertices) {
            Point3d translatedVertex = vertex;
            translatedVertex.rotate(origin, orientation.getForward(), angleX);
            translatedVertex.rotate(origin, orientation.getRight(), angleY);
            vertex.setPoint(translatedVertex);
        }

        this.orientation = targetOrientation;
    }

    public void randomizeColor() {
        for(Face face : faces) {
            face.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        }
    }

    public void setColor(Color color) {
        for(Face face : faces) {
            face.setColor(color);
        }
    }

    public void addMesh(Mesh mesh) {
        List<Point3d> newVertices = new ArrayList<>(vertices);
        List<Edge> newEdges = new ArrayList<>(edges);
        List<Face> newFaces = new ArrayList<>(faces);

        int vertexOffset = newVertices.size();

        // Add vertices
        newVertices.addAll(mesh.getVertices());

        // Add edges
        for (Edge edge : mesh.getEdges()) {
            int v1 = edge.getPoint1() + vertexOffset;
            int v2 = edge.getPoint2() + vertexOffset;
            newEdges.add(new Edge(v1, v2));
        }

        // Add faces
        for (Face face : mesh.getFaces()) {
            List<Integer> newVertexIndices = new ArrayList<>();
            for (int index : face.getVertexIndices()) {
                newVertexIndices.add(index + vertexOffset);
            }
            Face newFace = new Face(newVertexIndices);
            newFace.setColor(face.getColor());
            newFaces.add(newFace);
        }

        // Update the current mesh with the combined vertices, edges, and faces
        vertices = newVertices;
        edges = newEdges;
        faces = newFaces;
    }




    public List<Point3d> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setVertices(List<Point3d> vertices) {
        this.vertices = vertices;
    }

    public void setPoint3d(int i, Point3d Point3d) {
        vertices.set(i, Point3d);
    }

    public List<Face> getFaces() {
        return faces;
    }
}
