package main.java.object3d.camera;

import main.java.game.GamePanel;
import main.java.math.Point2d;
import main.java.math.Point3d;
import main.java.math.Ray;
import main.java.math.Vector;
import main.java.mesh.Edge;
import main.java.mesh.Mesh;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Fredrik
 */
public class Camera extends Object3d {

    private Point3d observer;
    private PicturePlane picturePlane;
    private double fov;
    private GamePanel gamePanel;

    public Camera(Point3d observer, Orientation orientation, GamePanel gamePanel) {
        super(observer, orientation,null);
        this.gamePanel = gamePanel;
        this.observer = observer;
        setOrientation(orientation);
        fov = 1;
        try{
            updatePicturePlane();
        }catch (Exception e){

        }

    }

    /**
     * draws the projected meshes
     * @param meshes the meshes to be drawn
     * @param g the graphics object
     */
    public void drawProjectedMeshes(ArrayList<Mesh> meshes, Graphics g) {
        HashMap<Integer, ProjectedMesh> projectedMeshes = projectMeshes(meshes);
        for (int i = 0; i < projectedMeshes.size(); i++) {
            projectedMeshes.get(i).drawEdges(g);
            //projectedMeshes.get(i).drawVertices(g);

        }

    }

    /**
     * projects the meshes
     * @param meshes the meshes to be projected
     * @return a hashmap of the projected meshes
     */
    private HashMap<Integer, ProjectedMesh> projectMeshes(ArrayList<Mesh> meshes) {
            HashMap<Integer, ProjectedMesh> projectedMeshes = new HashMap<>();
        for (int i = 0; i < meshes.size(); i++) {
            projectedMeshes.put(i,projectMesh(meshes.get(i)));
        }
        return projectedMeshes;
    }

    /**
     * projects the mesh
     * @param mesh the mesh to be projected
     * @return the projected mesh
     */
    private ProjectedMesh projectMesh(Mesh mesh) {
        ProjectedMesh projectedMesh = new ProjectedMesh(mesh);

        for (int edgeIndex : mesh.getEdges().keySet()) {
            Edge edge = mesh.getEdges().get(edgeIndex);
            Point3d Point3d1 = mesh.getVertices().get(edge.getPoint3d1());
            Point3d Point3d2 = mesh. getVertices().get(edge.getPoint3d2());

            boolean Point3d1InFront = Point3d1.isInFrontOf(observer.addDistanceAlongVector(getOrientation().getForward(),1), getOrientation().getForward());
            boolean Point3d2InFront = Point3d2.isInFrontOf(observer.addDistanceAlongVector(getOrientation().getForward(),1), getOrientation().getForward());

            if (Point3d1InFront && Point3d2InFront) {
                projectedMesh.putProjectedPoint(edge.getPoint3d1(), projectPoint3dInFrontOfCamera(Point3d1));
                projectedMesh.putProjectedPoint(edge.getPoint3d2(), projectPoint3dInFrontOfCamera(Point3d2));
                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getPoint3d1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getPoint3d2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            } else if (Point3d1InFront) {
                projectedMesh.putProjectedPoint(edge.getPoint3d1(), projectPoint3dInFrontOfCamera(Point3d1));
                projectedMesh.putProjectedPoint(edge.getPoint3d2(), clippingPoint(Point3d2, Point3d1));

                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getPoint3d1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getPoint3d2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            } else if (Point3d2InFront) {
                projectedMesh.putProjectedPoint(edge.getPoint3d2(), projectPoint3dInFrontOfCamera(Point3d2));
                projectedMesh.putProjectedPoint(edge.getPoint3d1(), clippingPoint(Point3d1, Point3d2));

                ProjectedEdge projectedEdge = new ProjectedEdge();
                projectedEdge.setP1(projectedMesh.getProjectedPoints().get(edge.getPoint3d1()));
                projectedEdge.setP2(projectedMesh.getProjectedPoints().get(edge.getPoint3d2()));
                projectedMesh.putProjectEdge(edgeIndex, projectedEdge);
            }
        }

        return projectedMesh;
    }

    /**
     * projects the vertices in front of the camera
     * @param Point3d the Point3d to be projected
     * @return the projected point
     */
    private Point2d projectPoint3dInFrontOfCamera(Point3d Point3d) {
        Ray ray = new Ray(observer, Point3d);
        Point3d intersect = ray.intersectWithPlane(picturePlane);
        if(intersect != null) {
            return picturePlane.project3dPointOnPanel(intersect, getOrientation(), gamePanel);
        }
        return null;
    }
    private Point2d clippingPoint(Point3d Point3d, Point3d connectedPoint3d) {
        Ray ray = new Ray(Point3d, connectedPoint3d);
        Point3d intersect = ray.intersectWithPlane(picturePlane);
        if (intersect != null) {
            return picturePlane.project3dPointOnPanel(intersect, getOrientation(), gamePanel);
        }
        return null;
    }


    @Override
    public void update() {
        super.update();
        updatePicturePlane();
    }

    private void updatePicturePlane() {
        Vector forward = getOrientation().getForward();
        Vector right = getOrientation().getRight();
        Vector up = getOrientation().getUp();

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
