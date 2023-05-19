package main.java.object3d;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Mesh;



public abstract class Object3d  {

    protected Mesh mesh;
    protected Point3d position;

    protected Orientation orientation;
    protected Vector velocity;

    protected Vector rotation;

    protected Object3d(Point3d position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        velocity = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);
    }

    public void update(){
        position = position.addVector(velocity);
        rotate(rotation.scalar(), rotation);
        updateMesh();
    }

    public void rotate(double angle, Vector axis){
        orientation.rotate(angle, axis);
        updateMesh();
    }

    protected abstract void updateMesh();

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

    public Vector getRotation() {
        return rotation;
    }

    public void setRotation(Vector rotation) {
        this.rotation = rotation;
    }
}
