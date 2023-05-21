package main.java.object3d.camera;

import main.java.game.GamePanel;
import main.java.math.Point2d;
import main.java.math.Point3d;
import main.java.math.Ray;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.mesh.Vertex;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Fredrik
 */
public class Camera extends Object3d  {

    private Point3d observer;
    private PicturePlane picturePlane;
    private double fov;
    private GamePanel gamePanel;

    public Camera(Point3d observer, Orientation orientation, GamePanel gamePanel) {
        super(observer, orientation);
        this.gamePanel = gamePanel;
        this.observer = observer;
        this.orientation = orientation;
        fov = 1;
        try{
            updatePicturePlane();
        }catch (Exception e){

        }

    }
    public void drawProjectedObjects(ArrayList<Mesh> meshes, Graphics g) {
        HashMap<Integer, ProjectedMesh> projectedMeshes = projectMeshes(meshes);
        for (int i = 0; i < projectedMeshes.size(); i++) {
            projectedMeshes.get(i).drawEdges(g);
            //projectedMeshes.get(i).drawVertices(g);

        }

    }
    private HashMap<Integer, ProjectedMesh> projectMeshes(ArrayList<Mesh> meshes) {
            HashMap<Integer, ProjectedMesh> projectedMeshes = new HashMap<>();
        for (int i = 0; i < meshes.size(); i++) {
            projectedMeshes.put(i,projectMesh(meshes.get(i)));
        }
        return projectedMeshes;
    }
    private ProjectedMesh projectMesh(Mesh mesh) {
        ProjectedMesh projectedMesh = new ProjectedMesh(mesh);

        for (int edgeIndex : mesh.getEdges().keySet()) {
            Edge edge = mesh.getEdges().get(edgeIndex);
            Vertex vertex1 = mesh.getVertices().get(edge.getVertex1());
            Vertex vertex2 = mesh. getVertices().get(edge.getVertex2());

            boolean vertex1InFront = vertex1.isInFrontOf(observer.addDistanceAlongVector(orientation.getForward(),1), orientation.getForward());
            boolean vertex2InFront = vertex2.isInFrontOf(observer.addDistanceAlongVector(orientation.getForward(),1), orientation.getForward());

            if (vertex1InFront && vertex2InFront) {
                projectedMesh.putProjectedPoint(edge.getVertex1(), projectVertexInFrontOfCamera(vertex1));
                projectedMesh.putProjectedPoint(edge.getVertex2(), projectVertexInFrontOfCamera(vertex2));

                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getVertex1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getVertex2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            } else if (vertex1InFront) {
                projectedMesh.putProjectedPoint(edge.getVertex1(), projectVertexInFrontOfCamera(vertex1));
                projectedMesh.putProjectedPoint(edge.getVertex2(), clippingPoint(vertex2, vertex1));

                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getVertex1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getVertex2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            } else if (vertex2InFront) {
                projectedMesh.putProjectedPoint(edge.getVertex2(), projectVertexInFrontOfCamera(vertex2));
                projectedMesh.putProjectedPoint(edge.getVertex1(), clippingPoint(vertex1, vertex2));

                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getVertex1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getVertex2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            }
        }

        return projectedMesh;
    }


    private Point2d projectVertexInFrontOfCamera(Vertex vertex) {
        Ray ray = new Ray(observer, vertex);
        Point3d intersect = ray.intersectWithPlane(picturePlane);
        if(intersect != null) {
            return picturePlane.project3dPointOnPanel(intersect, orientation, gamePanel);
        }
        return null;
    }

    private Point2d clippingPoint(Vertex vertex, Vertex connectedVertex) {
        Ray ray = new Ray(vertex, connectedVertex);
        Point3d intersect = ray.intersectWithPlane(picturePlane);
        if (intersect != null) {
            return picturePlane.project3dPointOnPanel(intersect, orientation, gamePanel);
        }
        return null;
    }


    @Override
    public void update() {
        super.update();
        updatePicturePlane();
    }

    @Override
    protected void updateMesh() {

    }

    public void updatePicturePlane() {
        Vector forward = orientation.getForward();
        Vector right = orientation.getRight();
        Vector up = orientation.getUp();

        Point3d topLeft = observer
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, -gamePanel.getAspectRatio() * fov / 2)
                .addDistanceAlongVector(up, fov / 2);

        Point3d topRight = observer
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, gamePanel.getAspectRatio() * fov / 2)
                .addDistanceAlongVector(up, fov / 2);

        Point3d bottomRight = observer
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, gamePanel.getAspectRatio() * fov / 2)
                .addDistanceAlongVector(up, -fov / 2);

        Point3d bottomLeft = observer
                .addDistanceAlongVector(forward, 1)
                .addDistanceAlongVector(right, -gamePanel.getAspectRatio() * fov / 2)
                .addDistanceAlongVector(up, -fov / 2);

        picturePlane = new PicturePlane(topLeft, topRight, bottomRight, bottomLeft);
    }



    public Point3d getObserver() {
        return observer;
    }

    public void setObserver(Point3d observer) {
        this.observer = observer;
    }

    public PicturePlane getPicturePlane() {
        return picturePlane;
    }
}
