package main.java.object3d;

import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.Mesh;


public class Object3d {
    private Mesh mesh;
    private Point3d position;
    private Orientation orientation;
    private Vector velocity;
    private Vector rotationVelocity;

    public Object3d(Point3d position, Orientation orientation, Mesh mesh) {
        this.position = position;
        this.orientation = orientation;
        this.mesh = mesh;
        velocity = new Vector(0, 0, 0);
        rotationVelocity = new Vector(0, 0, 0);
        if(mesh != null){
            mesh = mesh.update(position,orientation);
        }

    }

    public void update(){
        position = position.addVector(velocity);
        rotate(rotationVelocity.scalar(), rotationVelocity);
        if(mesh != null){
            mesh = mesh.update(position,orientation);
        }

    }

    public void rotate(double angle, Vector axis){
        orientation.rotate(angle, axis);
        if(mesh != null){
            mesh = mesh.update(position,orientation);
        }
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

    public Vector getRotationVelocity() {
        return rotationVelocity;
    }

    public void setRotationVelocity(Vector rotationVelocity) {
        this.rotationVelocity = rotationVelocity;
    }
}
