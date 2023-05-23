package main.java.object3d.camera;

import main.java.math.Point2d;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.game.GamePanel;
import main.java.object3d.Orientation;
import main.java.math.Rectangle;

public class PicturePlane extends Rectangle {
    private double aspectRatio;
    private double w;
    private double h;

    private Point3d p1;
    private Point3d p2;
    private Point3d p3;
    private Point3d p4;

    public PicturePlane(Point3d p1, Point3d p2, Point3d p3, Point3d p4) {
        super(p1, p2, p3, p4);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        w = p1.getDistanceFromPoint(p2);
        h = p2.getDistanceFromPoint(p3);
        aspectRatio = w/h;
    }

    /**
     * Projects a point on the picture plane onto the game panel
     * @param intersect Point to project
     * @param orientation Orientation of the camera
     * @param gamePanel GamePanel to project on
     * @return Point2d on the game panel
     */
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

    @Override
    public double getW() {
        return w;
    }

    @Override
    public double getH() {
        return h;
    }

    public Point3d getP1() {
        return p1;
    }

    public Point3d getP2() {
        return p2;
    }

    public Point3d getP3() {
        return p3;
    }

    public Point3d getP4() {
        return p4;
    }
}
