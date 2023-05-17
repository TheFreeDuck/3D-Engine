package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.mesh.Vertex;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.world3d.object3d.Orientation;

import java.util.HashMap;

public class TorusMesh extends Mesh {
    private double innerRadius;
    private double outerRadius;

    private int nSides;
    private int nRings;

    public TorusMesh(Point3d origin, Orientation orientation, double innerRadius, double outerRadius, int nSides, int nRings) {
        super(origin,orientation);
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.nSides = nSides;
        this.nRings = nRings;

        // Create vertices
        double sideStep = 2 * Math.PI / nSides;
        double ringStep = 2 * Math.PI / nRings;

        for (int i = 0; i <= nSides; i++) {
            double sideAngle = i * sideStep;
            for (int j = 0; j <= nRings; j++) {
                double ringAngle = j * ringStep;
                double x = origin.getX() + (innerRadius + outerRadius * Math.cos(ringAngle)) * Math.cos(sideAngle);
                double y = origin.getY() + (innerRadius + outerRadius * Math.cos(ringAngle)) * Math.sin(sideAngle);
                double z = origin.getZ() + outerRadius * Math.sin(ringAngle);
                vertices.add(new Vertex(new Point3d(x, y, z)));
            }
        }

        // Create edges
        edges = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nSides; i++) {
            for (int j = 0; j < nRings; j++) {
                int v1 = j + (nRings + 1) * i;
                int v2 = j + (nRings + 1) * (i + 1);
                edges.put(count++, new Edge(v1, v2));
            }
        }

        for (int i = 0; i <= nSides; i++) {
            for (int j = 0; j < nRings; j++) {
                int v1 = j + (nRings + 1) * i;
                int v2 = j + 1 + (nRings + 1) * i;
                edges.put(count++, new Edge(v1, v2));
            }
        }

        // Create triangles
        triangles = new HashMap<>();
        count = 0;
        for (int i = 0; i < nSides; i++) {
            for (int j = 0; j < nRings; j++) {
                int v1 = j + (nRings + 1) * i;
                int v2 = j + 1 + (nRings + 1) * i;
                int v3 = v1 + nRings + 1;
                int v4 = v2 + nRings + 1;
                triangles.put(count++, new Triangle(v1, v2, v3));
                triangles.put(count++, new Triangle(v2, v4, v3));
            }
        }
    }
}
