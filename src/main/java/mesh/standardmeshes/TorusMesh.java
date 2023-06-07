package main.java.mesh.standardmeshes;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Face;
import main.java.object3d.Orientation;

import java.util.ArrayList;
import java.util.List;

public class TorusMesh extends Mesh {
    double majorRadius;
    double minorRadius;

    int nMajorSegments;
    int nMinorSegments;

    public TorusMesh(double majorRadius, double minorRadius, int nMajorSegments, int nMinorSegments) {
        super();
        this.nMajorSegments = nMajorSegments;
        this.nMinorSegments = nMinorSegments;
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
                Vector Point3dPosition = new Vector(x, y, z);
                Point3d transformedPoint3dPosition = orientation.multiplyVector(Point3dPosition).add(origin);
                vertices.add(transformedPoint3dPosition);
            }
        }

        // Create edges
        edges = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < nMajorSegments; i++) {
            for (int j = 0; j < nMinorSegments; j++) {
                int v1 = j + (nMinorSegments + 1) * i;
                int v2 = j + (nMinorSegments + 1) * (i + 1);
                int v3 = j + 1 + (nMinorSegments + 1) * (i + 1);
                int v4 = j + 1 + (nMinorSegments + 1) * i;

                edges.add(new Edge(v1, v2));
                edges.add(new Edge(v2, v3));
                edges.add(new Edge(v3, v4));
                edges.add(new Edge(v4, v1));
            }
        }

        // Create triangles
        faces = new ArrayList<>();
        count = 0;
        for (int i = 0; i < nMajorSegments; i++) {
            for (int j = 0; j < nMinorSegments; j++) {
                int v1 = j + (nMinorSegments + 1) * i;
                int v2 = j + (nMinorSegments + 1) * (i + 1);
                int v3 = j + 1 + (nMinorSegments + 1) * (i + 1);
                int v4 = j + 1 + (nMinorSegments + 1) * i;

                faces.add(new Face(v1, v2, v3));
                faces.add(new Face(v1, v3, v4));
            }
        }
    }

    public TorusMesh(Point3d origin, Orientation orientation, double majorRadius, double minorRadius, int nMajorSegments, int nMinorSegments,  List<Face> faceList) {
        super();
        this.origin = origin;
        this.orientation = orientation;
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
        this.nMajorSegments = nMajorSegments;
        this.nMinorSegments = nMinorSegments;

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
                Vector Point3dPosition = new Vector(x, y, z);
                Point3d transformedPoint3dPosition = orientation.multiplyVector(Point3dPosition).add(origin);
                vertices.add(transformedPoint3dPosition);
            }
        }

        // Create edges
        edges = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < nMajorSegments; i++) {
            for (int j = 0; j < nMinorSegments; j++) {
                int v1 = j + (nMinorSegments + 1) * i;
                int v2 = j + (nMinorSegments + 1) * (i + 1);
                int v3 = j + 1 + (nMinorSegments + 1) * (i + 1);
                int v4 = j + 1 + (nMinorSegments + 1) * i;

                edges.add(new Edge(v1, v2));
                edges.add(new Edge(v2, v3));
                edges.add(new Edge(v3, v4));
                edges.add(new Edge(v4, v1));
            }
        }

        // Create triangles
        this.faces = faceList;
    }


    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new TorusMesh(position, orientation, majorRadius, minorRadius, nMajorSegments, nMinorSegments,faces);
    }
}
