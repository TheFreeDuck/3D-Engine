package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Vertex;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.world3d.object3d.Orientation;

import java.util.HashMap;

public class SphereMesh extends Mesh {
    double radius;

    public SphereMesh(Point3d origin, Orientation orientation, double radius, int nLongitudeSegments, int nLatitudeSegments) {
        super(origin, orientation);
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
                Vector vertexPosition = new Vector(x, y, z);
                Point3d transformedVertexPosition = orientation.multiply(vertexPosition).add(origin);
                vertices.add(new Vertex(transformedVertexPosition));
            }
        }

        // Create edges
        edges = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nLongitudeSegments; i++) {
            for (int j = 0; j < nLatitudeSegments; j++) {
                int v1 = j + (nLatitudeSegments + 1) * i;
                int v2 = j + (nLatitudeSegments + 1) * (i + 1);
                int v3 = j + 1 + (nLatitudeSegments + 1) * (i + 1);
                int v4 = j + 1 + (nLatitudeSegments + 1) * i;

                edges.put(count++, new Edge(v1, v2));
                edges.put(count++, new Edge(v2, v3));
                edges.put(count++, new Edge(v3, v4));
                edges.put(count++, new Edge(v4, v1));
            }
        }

        // Create triangles
        triangles = new HashMap<>();
        count = 0;
        for (int i = 0; i < nLongitudeSegments; i++) {
            for (int j = 0; j < nLatitudeSegments; j++) {
                int v1 = j + (nLatitudeSegments + 1) * i;
                int v2 = j + (nLatitudeSegments + 1) * (i + 1);
                int v3 = j + 1 + (nLatitudeSegments + 1) * (i + 1);
                int v4 = j + 1 + (nLatitudeSegments + 1) * i;

                triangles.put(count++, new Triangle(v1, v2, v3));
                triangles.put(count++, new Triangle(v1, v3, v4));
            }
        }
    }
}
