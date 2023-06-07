package main.java.game;

import main.java.keyinput.Keys;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.object3d.Object3d;
import main.java.object3d.Orientation;
import main.java.object3d.camera.Camera;

public class Player extends Object3d {
    private Vector movement;
    private double movementSpeed;
    private double rotationSpeed;
    private Camera camera;

    public Player(Point3d position, Orientation orientation, GamePanel gamePanel) {
        super(position, orientation,null);
        camera = new Camera(position, orientation, gamePanel);
        movement = new Vector(0, 0, 0);
        movementSpeed = 0;
        rotationSpeed = 0.02;
    }

    @Override
    public void update() {
        setPosition(getPosition().addVector(getVelocity()).addVector(movement));
        camera.setObserver(getPosition());
        camera.setOrientation(getOrientation());
        camera.update();
    }

    public void keyEvents() {
        movement = new Vector(0, 0, 0);
        setRotationVelocity(new Vector(0, 0, 0));
        if (Keys.MOVE_RIGHT.isPressed()) {
            movement = movement.addVector(getOrientation().getRight().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_LEFT.isPressed()) {
            movement = movement.addVector(getOrientation().getRight().multiplyScalar(-movementSpeed));
        }

        if (Keys.MOVE_UP.isPressed()) {
            movement = movement.addVector(getOrientation().getUp().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_DOWN.isPressed()) {
            movement = movement.addVector(getOrientation().getUp().multiplyScalar(-movementSpeed));
        }

        if (Keys.MOVE_FORWARD.isPressed()) {
            movement = movement.addVector(getOrientation().getForward().multiplyScalar(movementSpeed));
        } else if (Keys.MOVE_BACK.isPressed()) {
            movement = movement.addVector(getOrientation().getForward().multiplyScalar(-movementSpeed));
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
            getOrientation().rotate(rotationSpeed,getOrientation().getUp());
        }
        if(Keys.ROTATE_LEFT.isPressed()){
            getOrientation().rotate(-rotationSpeed,getOrientation().getUp());
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
