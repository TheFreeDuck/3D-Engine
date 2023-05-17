package main.java.world3d.camera;

import main.java.math.Point2d;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.game.GamePanel;
import main.java.world3d.object3d.Orientation;
import main.java.math.Rectangle;

public class PicturePlane extends Rectangle {
    private double aspectRatio;

    public PicturePlane(Point3d p1, Point3d p2, Point3d p3, Point3d p4) {
        super(p1, p2, p3, p4);
        aspectRatio = p1.getDistanceFromPoint(p2)/p2.getDistanceFromPoint(p3);
    }
    public Point2d project3dPointOnPanel(Point3d intersect, Orientation orientation, GamePanel gamePanel) {
        Vector hypotenuse = new Vector(intersect, this.getVtx1());
        double length = hypotenuse.scalar();
        double angle;
        if (intersect.isInFrontOf(this.getVtx1(), orientation.getUp())) {
            angle = Math.PI - hypotenuse.angleBetweenVector(orientation.getRight()) * -1;
        } else {
            angle = Math.PI - hypotenuse.angleBetweenVector(orientation.getRight());

        }
        return new Point2d(Math.cos(angle) * length * (gamePanel.getWidth() / this.getW()), Math.sin(angle) * length * (gamePanel.getHeight() / this.getH()));
    }
    public double getAspectRatio() {
        return aspectRatio;
    }
}
