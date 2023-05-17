package main.java.world3d.entity;

import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.Orientation;
import main.java.math.Point3d;



public abstract class Entity extends Object3d  {

    public Entity(Point3d position, Orientation orientation) {
        super(position, orientation);
    }
}
