package main.java.network.server;

import main.java.math.Point3d;
import main.java.world3d.object3d.Orientation;

import java.io.Serializable;

public class PlayerData implements Serializable {

    private Orientation orientation;
    private Point3d position;


    public PlayerData(Point3d position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point3d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "position=" + position +
                ", orientation=" + orientation +
                '}';
    }
}
