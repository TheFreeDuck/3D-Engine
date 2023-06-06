package main.java.object3d.camera;

import main.java.game.GamePanel;
import main.java.math.Point2d;
import main.java.math.Point3d;
import main.java.math.Ray;
import main.java.math.Vector;
import main.java.mesh.Face;
import main.java.mesh.Mesh;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    public void drawProjectedMeshes(List<Mesh> meshes, Graphics g) {
        List<ProjectedMesh> projectedMeshes = projectMeshes(meshes);
        for(ProjectedMesh mesh : projectedMeshes){
            mesh.drawVertices(g);
            mesh.drawEdges(g);
            mesh.drawFaces(g);
        }

    }

    /**
     * projects the meshes
     * @param meshes the meshes to be projected
     * @return a hashmap of the projected meshes
     */
    private List<ProjectedMesh> projectMeshes(List<Mesh> meshes) {
        List<ProjectedMesh> projectedMeshes = new ArrayList<>();
        for (Mesh mesh : meshes) {
            projectedMeshes.add(projectMesh(mesh));
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
        List<ProjectedFace> projectedFaces =  new ArrayList<>();
        for (Face face : mesh.getFaces()) {
            ProjectedFace projectedFace = new ProjectedFace();
            List<Point2d> projectedVertices = new ArrayList<>();
            for (int vertexIndex : face.getVertexIndices()) {
                Point2d projectedPoint = projectPoint3dInFrontOfCamera(mesh.getVertices().get(vertexIndex));
                projectedVertices.add(projectedPoint);
                projectedMesh.getProjectedPoints().add(projectedPoint);
            }
            projectedFace.setProjectedPoints(projectedVertices);
            projectedFaces.add(projectedFace);
        }
        projectedMesh.setProjectedFaces(projectedFaces);
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
