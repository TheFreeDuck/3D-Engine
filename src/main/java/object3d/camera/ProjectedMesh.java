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
        projectedFaces = new ArrayList<>();
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
        for (ProjectedFace projectedFace : projectedFaces) {

            ArrayList<Point2d> points = projectedFace.projectedPoints;

            int[] xPoints = new int[points.size()];
            int[] yPoints = new int[points.size()];

            for (int i = 0; i < points.size(); i++) {
                if(points.get(i)!= null){
                xPoints[i] = (int) points.get(i).getX();
                yPoints[i] = (int) points.get(i).getY();
                }
            }

            g.setColor(new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
            g.fillPolygon(xPoints, yPoints, points.size());
        }

    }

    public ArrayList<Point2d> getProjectedPoints() {
        return projectedPoints;
    }

    public ArrayList<ProjectedEdge> getProjectedEdges() {
        return projectedEdges;
    }

    public ArrayList<ProjectedFace> getProjectedFaces() {
        return projectedFaces;
    }

    public void setProjectedPoints(ArrayList<Point2d> projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    public void setProjectedEdges(ArrayList<ProjectedEdge> projectedEdges) {
        this.projectedEdges = projectedEdges;
    }

    public void setProjectedFaces(ArrayList<ProjectedFace> projectedFaces) {
        this.projectedFaces = projectedFaces;
    }
}
