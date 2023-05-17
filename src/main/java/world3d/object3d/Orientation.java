package main.java.world3d.object3d;

import main.java.math.Vector;

public class Orientation  {
    private Vector forward;
    private Vector right;
    private Vector up;

    public Orientation(Vector forward, Vector right) {
        this.forward = forward.unitVector();
        this.right = right.unitVector();
        up = forward.crossProduct(right).unitVector();
    }

    public Orientation(Orientation other) {
        this.forward = other.forward;
        this.right = other.right;
        this.up = other.up;
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
        up = up.unitVector();

        right.rotateAroundVector(angle, vector);
        up.rotateAroundVector(angle, vector);
        forward.rotateAroundVector(angle, vector);

        up = up.unitVector();
        forward = forward.unitVector();
        right = right.unitVector();
    }

    /**
     * Multiplies the orientation by a vector
     * @param vector to multiply
     * @return the transformed vector
     */
    public synchronized Vector multiply(Vector vector) {
        double x = right.getX() * vector.getX() + up.getX() * vector.getY() + forward.getX() * vector.getZ();
        double y = right.getY() * vector.getX() + up.getY() * vector.getY() + forward.getY() * vector.getZ();
        double z = right.getZ() * vector.getX() + up.getZ() * vector.getY() + forward.getZ() * vector.getZ();
        return new Vector(x, y, z);
    }

    public Vector getUp() {
        return up.unitVector();
    }

    public Vector getForward() {
        return forward.unitVector();
    }

    public Vector getRight() {
        return right.unitVector();
    }
}
