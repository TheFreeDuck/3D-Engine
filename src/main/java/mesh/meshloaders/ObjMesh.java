package main.java.mesh.meshloaders;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.object3d.Orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjMesh extends Mesh {

    List<Point3d> objVertices;
    List<int[]> faces;

    public ObjMesh(InputStream inputStream) {
        super();

        objVertices = new ArrayList<>();
        faces = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts[0].equals("v")) {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);
                    Point3d Point3d = new Point3d(x, y, z);
                    objVertices.add(Point3d);
                } else if (parts[0].equals("f")) {
                    int[] face = new int[parts.length - 1];
                    for (int i = 1; i < parts.length; i++) {
                        String[] indices = parts[i].split("/");
                        face[i - 1] = Integer.parseInt(indices[0]) - 1; // Subtract 1 to convert to 0-based index
                    }
                    faces.add(face);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create vertices
        List<Point3d> vertices = new ArrayList<>();
        for (Point3d Point3d : objVertices) {
            // Apply transformation to each Point3d
            Point3d transformedPoint3d = orientation.multiplyVector(new Vector(Point3d)).add(origin);
            vertices.add(transformedPoint3d);
        }

        // Add vertices to the mesh
        this.vertices.addAll(vertices);

        // Create edges
        edges = new HashMap<>();
        int count = 0;
        for (int[] face : faces) {
            int v1 = face[0];
            int v2 = face[1];
            int v3 = face[2];
            edges.put(count++, new Edge(v1, v2));
            edges.put(count++, new Edge(v2, v3));
            edges.put(count++, new Edge(v3, v1));
        }

        // Create triangles
        triangles = new HashMap<>();
        count = 0;
        for (int[] face : faces) {
            int v1 = face[0];
            int v2 = face[1];
            int v3 = face[2];
            triangles.put(count++, new Triangle(v1, v2, v3));
        }
    }

    public ObjMesh(Point3d position,Orientation orientation,List<Point3d> objVertices,List<int[]> faces) {
        super();
        this.origin = position;
        this.orientation = orientation;
        this.objVertices = objVertices;
        this.faces  = faces;

        for (Point3d Point3d : objVertices) {
            // Apply transformation to each Point3d
            Point3d transformedPoint3d = orientation.multiplyVector(new Vector(Point3d)).add(origin);
            vertices.add(transformedPoint3d);
        }

        // Add vertices to the mesh
        this.vertices.addAll(vertices);

        // Create edges
        edges = new HashMap<>();
        int count = 0;
        for (int[] face : faces) {
            int v1 = face[0];
            int v2 = face[1];
            int v3 = face[2];
            edges.put(count++, new Edge(v1, v2));
            edges.put(count++, new Edge(v2, v3));
            edges.put(count++, new Edge(v3, v1));
        }

        // Create triangles
        triangles = new HashMap<>();
        count = 0;
        for (int[] face : faces) {
            int v1 = face[0];
            int v2 = face[1];
            int v3 = face[2];
            triangles.put(count++, new Triangle(v1, v2, v3));
        }
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new ObjMesh(position,orientation,objVertices,faces);
    }
}
