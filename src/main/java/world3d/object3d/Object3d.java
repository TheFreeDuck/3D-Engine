package main.java.world3d.object3d;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Mesh;



public abstract class Object3d  {

    protected Mesh mesh;
    protected Point3d position;

    protected Orientation orientation;
    protected Vector velocity;

    protected Object3d(Point3d position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        velocity = new Vector(0, 0, 0);
    }

    public void update(){
        position = position.addVector(velocity);
    }

    public Point3d getPosition() {
        return position;
    }

    public void setPosition(Point3d position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
