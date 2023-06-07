package main.java.mesh.meshloaders;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Face;
import main.java.mesh.Mesh;
import main.java.object3d.Orientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ObjMesh extends Mesh {

    List<Point3d> objVertices;
    List<Face> faceList;

    public ObjMesh(InputStream inputStream) {
        super();

        objVertices = new ArrayList<>();
        faceList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts[0].equals("v")) {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);
                    Point3d point3d = new Point3d(x, y, z);
                    objVertices.add(point3d);
                } else if (parts[0].equals("f")) {
                    List<Integer> vertexIndices = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        String[] indices = parts[i].split("/");
                        int vertexIndex = Integer.parseInt(indices[0]) - 1; // Subtract 1 to convert to 0-based index
                        vertexIndices.add(vertexIndex);
                    }
                    faceList.add(new Face(vertexIndices));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create vertices
        List<Point3d> vertices = new ArrayList<>();
        for (Point3d point3d : objVertices) {
            // Apply transformation to each point3d
            Point3d transformedPoint3d = orientation.multiplyVector(new Vector(point3d)).add(origin);
            vertices.add(transformedPoint3d);
        }

        // Add vertices to the mesh
        this.vertices.addAll(vertices);

        // Create edges
        edges = new ArrayList<>();
        for (Face face : faceList) {
            List<Integer> vertexIndices = face.getVertexIndices();
            int numVertices = vertexIndices.size();
            for (int i = 0; i < numVertices; i++) {
                int v1 = vertexIndices.get(i);
                int v2 = vertexIndices.get((i + 1) % numVertices); // Wrap around for the last vertex
                edges.add(new Edge(v1, v2));
            }
        }

        // Set faces
        faces = faceList;
    }

    public ObjMesh(Point3d position, Orientation orientation, List<Point3d> objVertices, List<Face> faceList) {
        super();
        this.origin = position;
        this.orientation = orientation;
        this.objVertices = objVertices;
        this.faceList = faceList;

        for (Point3d point3d : objVertices) {
            // Apply transformation to each point3d
            Point3d transformedPoint3d = orientation.multiplyVector(new Vector(point3d)).add(origin);
            vertices.add(transformedPoint3d);
        }

        // Add vertices to the mesh
        this.vertices.addAll(vertices);

        // Create edges
        edges = new ArrayList<>();
        List<Face> faces = new ArrayList<>(faceList);
        for (Face face : faces) {
            List<Integer> vertexIndices = face.getVertexIndices();
            int numVertices = vertexIndices.size();
            for (int i = 0; i < numVertices; i++) {
                int v1 = vertexIndices.get(i);
                int v2 = vertexIndices.get((i + 1) % numVertices); // Wrap around for the last vertex
                edges.add(new Edge(v1, v2));
            }
        }


        // Set faces
        this.faces = faces;
    }

    public ObjMesh(Point3d position, Orientation orientation, InputStream inputStream) {
        super();
        this.origin  = position;
        this.orientation = orientation;

        objVertices = new ArrayList<>();
        faceList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts[0].equals("v")) {
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);
                    Point3d point3d = new Point3d(x, y, z);
                    objVertices.add(point3d);
                } else if (parts[0].equals("f")) {
                    List<Integer> vertexIndices = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        String[] indices = parts[i].split("/");
                        int vertexIndex = Integer.parseInt(indices[0]) - 1; // Subtract 1 to convert to 0-based index
                        vertexIndices.add(vertexIndex);
                    }
                    faceList.add(new Face(vertexIndices));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create vertices
        List<Point3d> vertices = new ArrayList<>();
        for (Point3d point3d : objVertices) {
            // Apply transformation to each point3d
            Point3d transformedPoint3d = orientation.multiplyVector(new Vector(point3d)).add(origin);
            vertices.add(transformedPoint3d);
        }

        // Add vertices to the mesh
        this.vertices.addAll(vertices);

        // Create edges
        edges = new ArrayList<>();
        for (Face face : faceList) {
            List<Integer> vertexIndices = face.getVertexIndices();
            int numVertices = vertexIndices.size();
            for (int i = 0; i < numVertices; i++) {
                int v1 = vertexIndices.get(i);
                int v2 = vertexIndices.get((i + 1) % numVertices); // Wrap around for the last vertex
                edges.add(new Edge(v1, v2));
            }
        }

        // Set faces
        faces = faceList;
    }

    @Override
    public Mesh update(Point3d position, Orientation orientation) {
        return new ObjMesh(position, orientation, objVertices, faceList);
    }
}
