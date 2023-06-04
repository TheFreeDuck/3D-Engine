package main.java.object3d.camera;

import main.java.math.Point2d;
import main.java.mesh.Mesh;

import java.awt.*;
import java.util.ArrayList;

public class ProjectedMesh{
    Mesh mesh;
    private ArrayList<Point2d> projectedPoints;
    private ArrayList<ProjectedEdge> projectedEdges;

    //not implemented
    private ArrayList<ProjectedFace> projectedFaces;

    public ProjectedMesh(Mesh mesh) {
        this.mesh = mesh;
        projectedPoints = new ArrayList<>();
        projectedEdges = new ArrayList<>();
    }
    public void drawVertices(Graphics g) {
        for(Point2d point: projectedPoints){
            if(point != null){
                g.setColor(Color.red);
                g.fillRect((int) point.getX()-2, (int) point.getY()-2,4,4);
            }

        }
    }

    public void drawEdges(Graphics g) {
        for (ProjectedEdge edge : projectedEdges) {
            Point2d p1 = edge.getP1();
            Point2d p2 = edge.getP2();
            if(p1 != null && p2 != null){
                g.setColor(mesh.getColor());
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }

        }
    }

    public void drawFaces(Graphics g) {
        //not implemented
    }

}
