package main.java.object3d.camera;

import main.java.math.Point2d;

import java.util.ArrayList;

public class ProjectedFace {
    ArrayList<Point2d> projectedPoints;
    Camera camera;

    public ProjectedFace() {
    }

    public ArrayList<Point2d> getProjectedPoints() {
        return projectedPoints;
    }

    public void setProjectedPoints(ArrayList<Point2d> projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    @Override
    public String toString() {
        return "ProjectedFace{" +
                "projectedPoints=" + projectedPoints +
                '}';
    }
}
