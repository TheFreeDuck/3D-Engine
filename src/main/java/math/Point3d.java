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

    public static final Point3d ZERO = new Point3d(0,0,0);
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

    public Point3d(Vector v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }

    public Point3d addXYZ(double x, double y, double z) {
        return new Point3d(this.x + x, this.y + y, this.z + z);
    }

    public Point3d add(Point3d point){
        return new Point3d(this.x+point.x,this.y+point.y,this.z+point.z);
    }


    public Vector toVector(){
        return new Vector(x, y, z);
    }

    public void setPoint(Point3d point){
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    /**
     * Adds the values of a vector to the values of the point
     * @param velocity The vector to add
     * @return The new point
     */
    public Point3d addVector(Vector velocity) {
        return new Point3d(this.x + velocity.getX(), this.y + velocity.getY(), this.z + velocity.getZ());
    }

    public void rotate(Point3d center, Vector axis, double angle) {

        double translatedX = x - center.getX();
        double translatedY = y - center.getY();
        double translatedZ = z - center.getZ();

        Vector unitAxis = axis.unitVector();

        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);

        double rotatedX = (cosAngle + (1 - cosAngle) * unitAxis.getX() * unitAxis.getX()) * translatedX
                + (((1 - cosAngle) * unitAxis.getX() * unitAxis.getY()) - (sinAngle * unitAxis.getZ())) * translatedY
                + (((1 - cosAngle) * unitAxis.getX() * unitAxis.getZ()) + (sinAngle * unitAxis.getY())) * translatedZ;

        double rotatedY = (((1 - cosAngle) * unitAxis.getX() * unitAxis.getY()) + (sinAngle * unitAxis.getZ())) * translatedX
                + (cosAngle + (1 - cosAngle) * unitAxis.getY() * unitAxis.getY()) * translatedY
                + (((1 - cosAngle) * unitAxis.getY() * unitAxis.getZ()) - (sinAngle * unitAxis.getX())) * translatedZ;

        double rotatedZ = (((1 - cosAngle) * unitAxis.getX() * unitAxis.getZ()) - (sinAngle * unitAxis.getY())) * translatedX
                + (((1 - cosAngle) * unitAxis.getY() * unitAxis.getZ()) + (sinAngle * unitAxis.getX())) * translatedY
                + (cosAngle + (1 - cosAngle) * unitAxis.getZ() * unitAxis.getZ()) * translatedZ;

        x = rotatedX + center.getX();
        y = rotatedY + center.getY();
        z = rotatedZ + center.getZ();
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
     * finds distance between this point and another point
     * @param p the other point
     * @return the distance between this point and the other point
     */
    public double getDistanceFromPoint(Point3d p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) + Math.pow(p.z - z, 2));
    }

    public Point3d multiply(double d){
        return new Point3d(x*d,y*d,z*d);
    }

    public Point3d subtract(Point3d point){
        return new Point3d(x-point.x,y-point.y,z-point.z);
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
