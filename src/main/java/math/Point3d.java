package main.java.math;


import java.io.Serializable;

/**
 *
 * @author Fredrik
 */
public class Point3d implements Serializable {

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    private double x;
    private double y;
    private double z;

    /**
     * creates a point from 3 values
     * @param x value
     * @param y value
     * @param z value
     */
    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d addPoint(double x, double y, double z) {
        return new Point3d(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Adds the values of a vector to the values of the point
     * @param velocity The vector to add
     * @return The new point
     */
    public Point3d addVector(Vector velocity) {
        return new Point3d(this.x + velocity.getX(), this.y + velocity.getY(), this.z + velocity.getZ());
    }

    /**
     * Adds a given distance along a given vector
     * @param v The vector to move along
     * @param distance The distance to move
     * @return The moved point
     */
    public Point3d addDistanceAlongVector(Vector v, double distance) {
        v = v.unitVector();
        v = new Vector(v.getX()*distance,v.getY()*distance,v.getZ()*distance);
        return new Point3d(this.x + v.getX(), this.y + v.getY(), this.z + v.getZ());
    }

    /**
     * Calculates the distance of the vector from the origin.
     * @return the distance from the origin.
     */
    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * finds distance between this point and another point
     * @param p the other point
     * @return the distance between this point and the other point
     */
    public double getDistanceFromPoint(Point3d p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
    }

    /**
     * Checks if this point is in front of another point along a given vector
     * @param p the other point it is in front of or behind
     * @param vector the vector att which defines the direction
     * @return a boolean indicating if this point is in front of another point along a given vector
     */
    public boolean isInFrontOf(Point3d p,Vector vector){
        return vector.dotProduct(new Vector(p, this)) > 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
    
}
