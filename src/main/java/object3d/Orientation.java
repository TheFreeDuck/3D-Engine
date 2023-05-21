package main.java.object3d;

import main.java.math.Vector;

import java.io.Serializable;

public class Orientation implements Serializable {
    private Vector forward;
    private Vector right;

    public Orientation(Vector forward, Vector right) {
        this.forward = forward.unitVector();
        this.right = right.unitVector();
    }

    public Orientation(Orientation other) {
        this.forward = other.forward;
        this.right = other.right;
    }

    public static Orientation standard(){
        return new Orientation(new Vector(1,0,0),new Vector(0,1,0));
    }



    /**
     * Rotates the orientation around a vector
     * @param angle in degrees
     * @param vector to rotate around
     */
    public synchronized void rotate(double angle,Vector vector) {
        right = right.unitVector();
        forward = forward.unitVector();

        right.rotateAroundVector(angle, vector);
        forward.rotateAroundVector(angle, vector);

        forward = forward.unitVector();
        right = right.unitVector();
    }

    /**
     * Multiplies the orientation by a vector
     * @param vector to multiply
     * @return the transformed vector
     */
    public synchronized Vector multiply(Vector vector) {
        double x = right.getX() * vector.getX() + getUp().getX() * vector.getY() + forward.getX() * vector.getZ();
        double y = right.getY() * vector.getX() + getUp().getY() * vector.getY() + forward.getY() * vector.getZ();
        double z = right.getZ() * vector.getX() + getUp().getZ() * vector.getY() + forward.getZ() * vector.getZ();
        return new Vector(x, y, z);
    }

    public Vector getUp() {
        return forward.crossProduct(right).unitVector();
    }

    public Vector getForward() {
        return forward.unitVector();
    }

    public Vector getRight() {
        return right.unitVector();
    }

    @Override
    public String toString() {
        return "Orientation{" +
                "forward=" + forward +
                ", right=" + right +
                ", up=" + getUp() +
                '}';
    }
}
