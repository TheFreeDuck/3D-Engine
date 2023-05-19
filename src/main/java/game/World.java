package main.java.game;

import main.java.math.Point3d;
import main.java.object3d.Player;
import main.java.object3d.Orientation;

import java.awt.*;

/**
 * @author Fredrik
 */
public abstract class World{
    protected Player player;
    protected Objects objects;
    protected World(GamePanel gamePanel) {
        player = new Player(new Point3d(0, 0, 0), Orientation.standard(), gamePanel);
        
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void keyEvents();

    public Objects getObjects() {
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }

    public Player getPlayer() {
        return player;
    }
}
