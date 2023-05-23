package main.java.object3d;

import main.java.game.GamePanel;
import main.java.keyinput.Keys;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.mesh.standardmeshes.CameraMesh;
import main.java.object3d.camera.Camera;

public class Player extends Object3d {
    private Vector movement;
    private double movementSpeed;

    private double rotationSpeed;
    private Camera camera;

    public Camera camera2;

    public Player(Point3d position, Orientation orientation, GamePanel gamePanel) {
        super(position, orientation);
        camera = new Camera(position, orientation, gamePanel);
        camera2 = new Camera(position.addDistanceAlongVector(orientation.getForward(), -2), Orientation.standard(), gamePanel);
        movement = new Vector(0, 0, 0);
        movementSpeed = 0;
        rotationSpeed = 0.01;
        mesh = new CameraMesh(position, orientation,camera.getPicturePlane());
    }

    @Override
    public void update() {
        position = position.addVector(velocity.addVector(movement));
        camera.setObserver(position);
        camera.setOrientation(orientation);
        camera.update();
        camera2.update();
        updateMesh();
    }

    @Override
    protected void updateMesh() {
        mesh = new CameraMesh(position, orientation,camera.getPicturePlane());
    }

    public void keyEvents() {
        movement = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);
        if (Keys.MOVE_RIGHT.isPressed()) {
            movement = movement.addVector(orientation.getRight().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_LEFT.isPressed()) {
            movement = movement.addVector(orientation.getRight().multiplyScalar(-movementSpeed));
        }

        if (Keys.MOVE_UP.isPressed()) {
            movement = movement.addVector(orientation.getUp().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_DOWN.isPressed()) {
            movement = movement.addVector(orientation.getUp().multiplyScalar(-movementSpeed));
        }

        if (Keys.MOVE_FORWARD.isPressed()) {
            movement = movement.addVector(orientation.getForward().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_BACK.isPressed()) {
            movement = movement.addVector(orientation.getForward().multiplyScalar(-movementSpeed));
        }

        if (Keys.SPEED_UP.isPressed()) {
            movementSpeed = 2;
        } else {
            movementSpeed = 0.1;
        }
        if(Keys.ROTATE_UP.isPressed()){
            //rotation distorting the projected image when rotating like this. dont know why :(
            //orientation.rotate(-rotationSpeed,orientation.getRight());
        }
        if(Keys.ROTATE_DOWN.isPressed()){
            //rotation distorting the projected image when rotating like this. dont know why :(
            //orientation.rotate(rotationSpeed,orientation.getRight());
        }
        if(Keys.ROTATE_RIGHT.isPressed()){
            orientation.rotate(rotationSpeed,orientation.getUp());
        }
        if(Keys.ROTATE_LEFT.isPressed()){
            orientation.rotate(-rotationSpeed,orientation.getUp());
        }
        if(Keys.ROLL_RIGHT.isPressed()){
            //rotation distorting the projected image when rotating like this. dont know why :(
            //orientation.rotate(-rotationSpeed,orientation.getForward());
        }
        if(Keys.ROLL_LEFT.isPressed()){
            //rotation distorting the projected image when rotating like this. dont know why :(
            //orientation.rotate(rotationSpeed,orientation.getForward());
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
