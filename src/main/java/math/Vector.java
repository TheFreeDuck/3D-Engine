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

    public void setScalar(double scalar) {
        double magnitude = scalar / scalar();
        x *= magnitude;
        y *= magnitude;
        z *= magnitude;
    }

    public Vector getScalarVector(double scalar) {
        double magnitude = scalar / scalar();
        return new Vector(x * magnitude, y * magnitude, z * magnitude);
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



    /*public void rotateAroundVector(double angle, Vector axis) {
        // Compute the angle between the two vectors
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);

        // Compute the axis of rotation perpendicular to both vectors
        Vector rotationAxis = axis.crossProduct(this).unitVector();

        // Apply the Rodrigues' rotation formula
        Vector rotatedVector = this.scale(cosAngle)
                .add(rotationAxis.scale(sinAngle).crossProduct(this))
                .add(rotationAxis.scale(rotationAxis.dotProduct(this) * (1 - cosAngle)));

        // Update the vector components
        this.x = rotatedVector.x;
        this.y = rotatedVector.y;
        this.z = rotatedVector.z;
    }*/



   /* public void rotateAroundVector(double angle, Vector axis) {
        // Normalize the axis vector
        axis.unitVector();

        // Calculate the sine and cosine of the angle
        double sinAngle = Math.sin(angle);
        double cosAngle = Math.cos(angle);

        // Calculate the components of the rotated vector
        double xPrime = x * (cosAngle + (1 - cosAngle) * axis.x * axis.x)
                + y * ((1 - cosAngle) * axis.x * axis.y - sinAngle * axis.z)
                + z * ((1 - cosAngle) * axis.x * axis.z + sinAngle * axis.y);

        double yPrime = x * ((1 - cosAngle) * axis.x * axis.y + sinAngle * axis.z)
                + y * (cosAngle + (1 - cosAngle) * axis.y * axis.y)
                + z * ((1 - cosAngle) * axis.y * axis.z - sinAngle * axis.x);

        double zPrime = x * ((1 - cosAngle) * axis.x * axis.z - sinAngle * axis.y)
                + y * ((1 - cosAngle) * axis.y * axis.z + sinAngle * axis.x)
                + z * (cosAngle + (1 - cosAngle) * axis.z * axis.z);

        // Update the vector with the rotated components
        x = xPrime;
        y = yPrime;
        z = zPrime;
    }*/

    public Vector rotateAroundVector(double angle, Vector axis) {
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
        return this;
    }







    /*public void rotateAroundVector(double angle, Vector axis) {
        // Convert the rotation axis to a unit quaternion
        axis.unitVector();
        Quaternion rotationQuaternion = new Quaternion(axis.x * Math.sin(angle / 2), axis.y * Math.sin(angle / 2), axis.z * Math.sin(angle / 2), Math.cos(angle / 2));

        // Convert the vector to a quaternion
        Quaternion vectorQuaternion = new Quaternion(x, y, z, 0);

        // Rotate the vector quaternion by the rotation quaternion
        Quaternion resultQuaternion = rotationQuaternion.mul(vectorQuaternion).mul(rotationQuaternion.conjugate());

        // Extract the rotated components from the resulting quaternion
        x = resultQuaternion.x;
        y = resultQuaternion.y;
        z = resultQuaternion.z;
    }*/

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
