package main.java.network.server;

import main.java.math.Point3d;
import main.java.world3d.object3d.Orientation;

import java.io.Serializable;

public class PlayerData implements Serializable {

    private Point3d position;
    private Orientation orientation;


    public PlayerData(Point3d position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Point3d getPosition() {
        return position;
    }

    public void setPosition(Point3d position) {
        this.position = position;
    }
}
