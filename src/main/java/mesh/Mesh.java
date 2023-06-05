package main.java.mesh;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;

public abstract class Mesh  {
    protected ArrayList<Point3d> vertices;
    protected ArrayList<Edge> edges;
    protected ArrayList<Face> faces;

    protected Point3d origin;

    protected Orientation orientation;

    protected Color color;

    protected Mesh() {
        this.orientation = Orientation.standard();
        this.origin = new Point3d(10,0,0);
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        faces = new ArrayList<>();

        color = Color.white;
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



    public ArrayList<Point3d> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point3d> vertices) {
        this.vertices = vertices;
    }

    public void setPoint3d(int i, Point3d Point3d) {
        vertices.set(i, Point3d);
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
