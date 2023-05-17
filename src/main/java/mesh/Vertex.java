package main.java.mesh;

import main.java.math.Point3d;
public class Vertex extends Point3d  {

    public Vertex(double x, double y, double z) {
        super(x, y, z);
    }

    public Vertex(Point3d point) {
        super(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public Vertex addPoint(double x, double y, double z) {
        return new Vertex(this.getX() + x, this.getY() + y, this.getZ() + z);
    }
}
