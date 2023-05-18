package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Vertex;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.world3d.object3d.Orientation;

import java.util.HashMap;

public class TorusMesh extends Mesh {
    double majorRadius;
    double minorRadius;

    public TorusMesh(Point3d origin, Orientation orientation, double majorRadius, double minorRadius, int nMajorSegments, int nMinorSegments) {
        super(origin, orientation);
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;

        // Create vertices
        double majorStep = 2 * Math.PI / nMajorSegments;
        double minorStep = 2 * Math.PI / nMinorSegments;

        for (int i = 0; i <= nMajorSegments; i++) {
            double majorAngle = i * majorStep;
            for (int j = 0; j <= nMinorSegments; j++) {
                double minorAngle = j * minorStep;
                double x = (majorRadius + minorRadius * Math.cos(minorAngle)) * Math.cos(majorAngle);
                double y = minorRadius * Math.sin(minorAngle);
                double z = (majorRadius + minorRadius * Math.cos(minorAngle)) * Math.sin(majorAngle);
                Vector vertexPosition = new Vector(x, y, z);
                Point3d transformedVertexPosition = orientation.multiply(vertexPosition).add(origin);
                vertices.add(new Vertex(transformedVertexPosition));
            }
        }

        // Create edges
        edges = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nMajorSegments; i++) {
            for (int j = 0; j < nMinorSegments; j++) {
                int v1 = j + (nMinorSegments + 1) * i;
                int v2 = j + (nMinorSegments + 1) * (i + 1);
                int v3 = j + 1 + (nMinorSegments + 1) * (i + 1);
                int v4 = j + 1 + (nMinorSegments + 1) * i;

                edges.put(count++, new Edge(v1, v2));
                edges.put(count++, new Edge(v2, v3));
                edges.put(count++, new Edge(v3, v4));
                edges.put(count++, new Edge(v4, v1));
            }
        }

        // Create triangles
        triangles = new HashMap<>();
        count = 0;
        for (int i = 0; i < nMajorSegments; i++) {
            for (int j = 0; j < nMinorSegments; j++) {
                int v1 = j + (nMinorSegments + 1) * i;
                int v2 = j + (nMinorSegments + 1) * (i + 1);
                int v3 = j + 1 + (nMinorSegments + 1) * (i + 1);
                int v4 = j + 1 + (nMinorSegments + 1) * i;

                triangles.put(count++, new Triangle(v1, v2, v3));
                triangles.put(count++, new Triangle(v1, v3, v4));
            }
        }
    }
}
