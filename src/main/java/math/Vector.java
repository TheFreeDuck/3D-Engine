package main.java.math;


import java.io.Serializable;

/**
 * @author Fredrik
 */
public class Vector implements Serializable {

    private double x;
    private double y;
    private double z;

    /**
     * Creates vector from 3 values
     * @param x value
     * @param y value
     * @param z value
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point3d point3d) {
        this.x = point3d.getX();
        this.y = point3d.getY();
        this.z = point3d.getZ();
    }

    /**
     * Creates vector from two points
     * @param p1 Point 1
     * @param p2 Points 2
     */
    public Vector(Point3d p1, Point3d p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }

    /**
     * Calculates the magnitude/scalar of the vector
     * @return Scalar value of the vector
     */
    public double scalar() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Calculates the unit vector of the vector
     * @return The unit vector
     */
    public Vector unitVector() {
        return new Vector(this.x / this.scalar(), this.y / this.scalar(), this.z / this.scalar());
    }

    /**
     * Multiplies the vector by a scalar value.
     * @param scalar the scalar value to multiply the vector by.
     * @return the resulting vector.
     */
    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar, z * scalar);
    }

    public Vector setScalar(double scalar) {
        double magnitude = scalar / scalar();

        return new Vector(x * magnitude, y * magnitude, z * magnitude);
    }

    public Point3d toPoint(){
    	return new Point3d(x, y, z);
    }

    public Vector getScalarVector(double scalar) {
        double magnitude = scalar / scalar();
        return new Vector(x * magnitude, y * magnitude, z * magnitude);
    }

    public Vector subtract(Vector vector) {
        return new Vector(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }


    /**
     * Returns the negation of the vector.
     *
     * @return the negated vector
     */
    public Vector negate() {
        return new Vector(-x, -y, -z);
    }



    public Point3d add(Point3d point) {
        return new Point3d(x + point.getX(), y + point.getY(), z + point.getZ());
    }

    /**
     * Rotates the vector
     * @param angle angle of rotation
     * @param axis on which axis to rotate the vector
     */
    public void rotateAroundAxis(double angle, int axis) {
        switch (axis) {
            case Point3d.X:
                y = y * Math.cos(angle) - z * Math.sin(angle);
                z = y * Math.sin(angle) + z * Math.cos(angle);
                break;
            case Point3d.Y:
                x = x * Math.cos(angle) + z * Math.sin(angle);
                z = -x * Math.sin(angle) + z * Math.cos(angle);
                break;
            case Point3d.Z:
                x = x * Math.cos(angle) - y * Math.sin(angle);
                y = x * Math.sin(angle) + y * Math.cos(angle);
                break;
        }

    }

    public Vector inverted() {
        return new Vector(-this.x, -this.y, -this.z);
    }

    /**
     * rotates the vector around another vector
     * @param angle angle of rotation in radians
     * @param axis the vector to rotate around
     */
    public void rotateAroundVector(double angle, Vector axis) {
        // Calculate the components of the vector being rotated
        double u1 = x;
        double u2 = y;
        double u3 = z;

        // Calculate the components of the axis vector
        double a1 = axis.getX();
        double a2 = axis.getY();
        double a3 = axis.getZ();

        // Calculate the cross product of the axis vector and the vector being rotated
        double b1 = a2 * u3 - a3 * u2;
        double b2 = a3 * u1 - a1 * u3;
        double b3 = a1 * u2 - a2 * u1;

        // Calculate the cross product of the axis vector and the result of the previous cross product
        double c1 = a2 * b3 - a3 * b2;
        double c2 = a3 * b1 - a1 * b3;
        double c3 = a1 * b2 - a2 * b1;

        // Calculate the sine and cosine of the angle of rotation
        double sinAngle = Math.sin(angle);
        double cosAngle = Math.cos(angle);

        // Calculate the new components of the vector
        double newX = u1 * cosAngle + b1 * sinAngle + c1 * (1 - cosAngle);
        double newY = u2 * cosAngle + b2 * sinAngle + c2 * (1 - cosAngle);
        double newZ = u3 * cosAngle + b3 * sinAngle + c3 * (1 - cosAngle);

        // Update the vector with the rotated components
        x = newX;
        y = newY;
        z = newZ;
    }

    public Vector scale(double value) {
        return new Vector(x * value, y * value, z * value);
    }
    /**
     * Adds the values of the current vector and another vector together and returns a new vector
     * @param vector The vector to add
     * @return The sum of the two vectors as a new Vector object
     */
    public Vector add(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }



    /**
     * calculates angle between the current vector and a given vector
     * @param vector the vector to calculate the angle between
     * @return returns the angle between the vectors in radians
     */
    public double angleBetweenVector(Vector vector){
        return Math.acos((this.dotProduct(vector))/ (this.scalar() * vector.scalar()));
    }

    /**
     * adds the values of the current vector and another vector together
     * @param vector the vector to add
     * @return the added vectors
     */
    public Vector addVector(Vector vector) {
        vector.setX(vector.getX() + x);
        vector.setY(vector.getY() + y);
        vector.setZ(vector.getZ() + z);
        return vector;
    }

    /**
     * calculates the dot product of the current vector and another vector
     * @param vector the vector to calculate the dot product with
     * @return the dot product of the current vector and another vector
     */
    public double dotProduct(Vector vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    /**
     * calculates the cross product of the current vector and another vector
     * @param vector the vector to calculate the cross product with
     * @return the cross product of the current vector and another vector
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    /**
     * Multiples the values of the vector with a given value
     * @param value The value to multiply the vector values by
     * @return The multiplied vector
     */
    public Vector multiplyScalar(double value) {
        return new Vector(x * value, y * value, z * value);
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
        return "Vector{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

}
