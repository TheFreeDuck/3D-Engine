package main.java.object3d.camera;

import main.java.math.Point2d;

import java.util.List;

public class ProjectedFace {
    List<Point2d> projectedPoints;

    public List<Point2d> getProjectedPoints() {
        return projectedPoints;
    }

    public void setProjectedPoints(List<Point2d> projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    @Override
    public String toString() {
        return "ProjectedFace{" +
                "projectedPoints=" + projectedPoints +
                '}';
    }
}
