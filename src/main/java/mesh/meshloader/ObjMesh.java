package main.java.mesh.meshloader;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Triangle;
import main.java.mesh.Vertex;
import main.java.object3d.Orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjMesh extends Mesh {
    public ObjMesh(Point3d origin, Orientation orientation, InputStream inputStream) {
        super(origin, orientation);

        List<Point3d> objVertices = new ArrayList<>();
        List<int[]> faces = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts[0].equals("v")) {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);
                    Point3d vertex = new Point3d(x, y, z);
                    objVertices.add(vertex);
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
        List<Vertex> vertices = new ArrayList<>();
        for (Point3d vertex : objVertices) {
            // Apply transformation to each vertex
            Point3d transformedVertex = orientation.multiply(new Vector(vertex)).add(origin);
            vertices.add(new Vertex(transformedVertex));
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
}
