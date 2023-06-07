package main.java.object3d.camera;

import main.java.math.Point3d;
import main.java.mesh.Face;

import java.util.Comparator;
import java.util.List;

public class CameraDistanceComparator implements Comparator<Face> {

    private Point3d cameraPosition;

    private List<Point3d> vertices;

    public CameraDistanceComparator(Point3d cameraPosition, List<Point3d> vertices) {
        this.cameraPosition = cameraPosition;
        this.vertices = vertices;
    }

    @Override
    public int compare(Face face1, Face face2) {
        double distance1 = calculateDistance(face1);
        double distance2 = calculateDistance(face2);

        return Double.compare(distance2, distance1);
    }

    public double calculateDistance(Face face) {
        Point3d midPoint = findMidPoint(face);
        return cameraPosition.getDistanceFromPoint(midPoint);
    }

    public Point3d findMidPoint(Face face) {
        Point3d midPoint = new Point3d(0, 0, 0);

        for (int vertexIndex : face.getVertexIndices()) {
            Point3d vertex = vertices.get(vertexIndex);
            midPoint = midPoint.addXYZ(vertex.getX(), vertex.getY(), vertex.getZ());
        }

        int numVertices = face.getVertexIndices().size();
        midPoint = midPoint.multiply(1.0 / numVertices);

        return midPoint;
    }
}
