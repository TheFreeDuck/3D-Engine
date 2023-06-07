package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Face;
import main.java.object3d.Orientation;

import java.util.ArrayList;
import java.util.List;

public class SphereMesh extends Mesh {
    double radius;
    int nLongitudeSegments;
    int nLatitudeSegments;

    public SphereMesh(double radius, int nLongitudeSegments, int nLatitudeSegments) {
        super();
        this.nLatitudeSegments = nLatitudeSegments;
        this.nLongitudeSegments = nLongitudeSegments;
        this.radius = radius;

        // Create vertices
        double longitudeStep = 2 * Math.PI / nLongitudeSegments;
        double latitudeStep = Math.PI / nLatitudeSegments;

        for (int i = 0; i <= nLongitudeSegments; i++) {
            double longitude = i * longitudeStep;
            for (int j = 0; j <= nLatitudeSegments; j++) {
                double latitude = j * latitudeStep;
                double x = radius * Math.sin(latitude) * Math.cos(longitude);
                double y = radius * Math.cos(latitude);
                double z = radius * Math.sin(latitude) * Math.sin(longitude);
                Vector Point3dPosition = new Vector(x, y, z);
                Point3d transformedPoint3dPosition = orientation.multiplyVector(Point3dPosition).add(origin);
                vertices.add(transformedPoint3dPosition);
            }
        }

        // Create edges
        edges = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < nLongitudeSegments; i++) {
            for (int j = 0; j < nLatitudeSegments; j++) {
                int v1 = j + (nLatitudeSegments + 1) * i;
                int v2 = j + (nLatitudeSegments + 1) * (i + 1);
                int v3 = j + 1 + (nLatitudeSegments + 1) * (i + 1);
                int v4 = j + 1 + (nLatitudeSegments + 1) * i;

                edges.add(new Edge(v1, v2));
                edges.add(new Edge(v2, v3));
                edges.add(new Edge(v3, v4));
                edges.add(new Edge(v4, v1));
            }
        }

        // Create triangles
        faces = new ArrayList<>();
        count = 0;
        for (int i = 0; i < nLongitudeSegments; i++) {
            for (int j = 0; j < nLatitudeSegments; j++) {
                int v1 = j + (nLatitudeSegments + 1) * i;
                int v2 = j + (nLatitudeSegments + 1) * (i + 1);
                int v3 = j + 1 + (nLatitudeSegments + 1) * (i + 1);
                int v4 = j + 1 + (nLatitudeSegments + 1) * i;

                faces.add(new Face(v1, v2, v3));
                faces.add(new Face(v1, v3, v4));
            }
        }
    }

    public SphereMesh(Point3d origin, Orientation orientation, double radius, int nLongitudeSegments, int nLatitudeSegments,  List<Face> faceList) {
        super();
        this.nLatitudeSegments  = nLatitudeSegments;
        this.nLongitudeSegments = nLongitudeSegments;
        this.origin = origin;
        this.orientation = orientation;
        this.radius = radius;

        // Create vertices
        double longitudeStep = 2 * Math.PI / nLongitudeSegments;
        double latitudeStep = Math.PI / nLatitudeSegments;

        for (int i = 0; i <= nLongitudeSegments; i++) {
            double longitude = i * longitudeStep;
            for (int j = 0; j <= nLatitudeSegments; j++) {
                double latitude = j * latitudeStep;
                double x = radius * Math.sin(latitude) * Math.cos(longitude);
                double y = radius * Math.cos(latitude);
                double z = radius * Math.sin(latitude) * Math.sin(longitude);
                Vector Point3dPosition = new Vector(x, y, z);
                Point3d transformedPoint3dPosition = orientation.multiplyVector(Point3dPosition).add(origin);
                vertices.add(transformedPoint3dPosition);
            }
        }

        // Create edges
        edges = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < nLongitudeSegments; i++) {
            for (int j = 0; j < nLatitudeSegments; j++) {
                int v1 = j + (nLatitudeSegments + 1) * i;
                int v2 = j + (nLatitudeSegments + 1) * (i + 1);
                int v3 = j + 1 + (nLatitudeSegments + 1) * (i + 1);
                int v4 = j + 1 + (nLatitudeSegments + 1) * i;

                edges.add(new Edge(v1, v2));
                edges.add(new Edge(v2, v3));
                edges.add(new Edge(v3, v4));
                edges.add(new Edge(v4, v1));
            }
        }

        this.faces = faceList;
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new SphereMesh(position, orientation, radius, nLongitudeSegments, nLatitudeSegments, faces);
    }
}
