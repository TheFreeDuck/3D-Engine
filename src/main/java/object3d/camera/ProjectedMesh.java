package main.java.object3d.camera;

import main.java.math.Point2d;
import main.java.mesh.Mesh;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectedMesh{
    Mesh mesh;
    private List<Point2d> projectedPoints;
    private List<ProjectedEdge> projectedEdges;

    //not implemented
    private List<ProjectedFace> projectedFaces;

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
        for (ProjectedFace projectedFace : projectedFaces) {

            List<Point2d> points = projectedFace.projectedPoints;

            int[] xPoints = new int[points.size()];
            int[] yPoints = new int[points.size()];

            for (int i = 0; i < points.size(); i++) {
                if(points.get(i)!= null){
                    xPoints[i] = (int) points.get(i).getX();
                    yPoints[i] = (int) points.get(i).getY();
                }
            }

            g.setColor(mesh.getFaces().get(projectedFaces.indexOf(projectedFace)).getColor());
            g.drawPolygon(xPoints, yPoints, points.size());
        }
    }

    public void drawFaces(Graphics g) {
        for (ProjectedFace projectedFace : projectedFaces) {

            List<Point2d> points = projectedFace.projectedPoints;

            int[] xPoints = new int[points.size()];
            int[] yPoints = new int[points.size()];

            for (int i = 0; i < points.size(); i++) {
                if(points.get(i)!= null){
                xPoints[i] = (int) points.get(i).getX();
                yPoints[i] = (int) points.get(i).getY();
                }
            }

            g.setColor(mesh.getFaces().get(projectedFaces.indexOf(projectedFace)).getColor());
            g.fillPolygon(xPoints, yPoints, points.size());
        }

    }

    public List<Point2d> getProjectedPoints() {
        return projectedPoints;
    }

    public List<ProjectedEdge> getProjectedEdges() {
        return projectedEdges;
    }

    public List<ProjectedFace> getProjectedFaces() {
        return projectedFaces;
    }

    public void setProjectedPoints(List<Point2d> projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    public void setProjectedEdges(List<ProjectedEdge> projectedEdges) {
        this.projectedEdges = projectedEdges;
    }

    public void setProjectedFaces(List<ProjectedFace> projectedFaces) {
        this.projectedFaces = projectedFaces;
    }
}
