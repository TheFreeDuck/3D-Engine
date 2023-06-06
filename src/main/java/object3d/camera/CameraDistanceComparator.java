package main.java.object3d.camera;

import javafx.geometry.Point3D;
import main.java.math.Point3d;
import main.java.mesh.Face;

import java.util.Comparator;
import java.util.List;

public class CameraDistanceComparator implements Comparator<Face> {

    private Point3D cameraPosition;

    private List<Point3d> vertices;

    public CameraDistanceComparator(Point3D cameraPosition, List<Point3d> vertices) {
        this.cameraPosition = cameraPosition;
        this.vertices = vertices;
    }

    @Override
    public int compare(Face face1, Face face2) {
        double distance1 = calculateDistance(face1);
        double distance2 = calculateDistance(face2);

        if (distance1 < distance2) {
            return 1; // Face1 is farther, so it should come first
        } else if (distance1 > distance2) {
            return -1; // Face2 is farther, so it should come first
        } else {
            return 0; // Faces are at the same distance
        }
    }

    public double calculateDistance(Face face) {
        Point3D midPoint = findMidPoint(face);
        return cameraPosition.distance(midPoint);
    }

    public Point3D findMidPoint(Face face) {
        Point3D midPoint = new Point3D(0, 0, 0);

        for (int vertexIndex : face.getVertexIndices()) {
            Point3d vertex = vertices.get(vertexIndex);
            midPoint = midPoint.add(vertex.getX(), vertex.getY(), vertex.getZ());
        }

        int numVertices = face.getVertexIndices().size();
        midPoint = midPoint.multiply(1.0 / numVertices);

        return midPoint;
    }
}
