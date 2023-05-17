package main.java.world3d.camera;

import main.java.math.Point2d;
import main.java.mesh.Mesh;

import java.awt.*;
import java.util.HashMap;

public class ProjectedMesh{
    Mesh mesh;
    private HashMap<Integer, Point2d> projectedPoints;
    private HashMap<Integer, ProjectedEdge> projectedEdges;

    //not implemented
    private HashMap<Integer, ProjectedEdge> projectedFaces;

    public ProjectedMesh(Mesh mesh) {
        this.mesh = mesh;
        projectedPoints = new HashMap<>();
        projectedEdges = new HashMap<>();
    }
    public void drawVertices(Graphics g) {
        for(int i : projectedPoints.keySet()){
            Point2d p = projectedPoints.get(i);
            if(p != null){
                g.setColor(Color.red);
                g.fillRect((int) p.getX()-2, (int) p.getY()-2,4,4);
            }

        }
    }

    public void drawEdges(Graphics g) {
        for (int i : projectedEdges.keySet()) {
            Point2d p1 = projectedEdges.get(i).getP1();
            Point2d p2 = projectedEdges.get(i).getP2();
            if(p1 != null && p2 != null){
                g.setColor(mesh.getColor());
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }

        }
    }

    public void drawFaces(Graphics g) {
        //not implemented
    }

    public void putProjectedPoint(int i, Point2d projectedPoint){
        projectedPoints.put(i, projectedPoint);
    }

    public void putProjectEdge(int i, ProjectedEdge projectedEdge){
        projectedEdges.put(i, projectedEdge);
    }

    public HashMap<Integer, Point2d> getProjectedPoints() {
        return projectedPoints;
    }

    public void setProjectedPoints(HashMap<Integer, Point2d> projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    public HashMap<Integer, ProjectedEdge> getProjectedEdges() {
        return projectedEdges;
    }

    public void setProjectedEdges(HashMap<Integer, ProjectedEdge> projectedEdges) {
        this.projectedEdges = projectedEdges;
    }
}
