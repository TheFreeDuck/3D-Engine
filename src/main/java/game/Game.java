package main.java.game;

import main.java.keyinput.Keys;
import main.java.math.Point3d;
import main.java.math.Vector;
import main.java.app.Frame;
import main.java.object3d.Orientation;

public class Game {
    private World world;

    private Frame frame;

    private GameLoop gameLoop;

    private GamePanel gamePanel;

    public Game(World world, Frame frame, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.world = world;
        gamePanel.setWorld(world);
        this.frame = frame;
        frame.addGamePanel(gamePanel);
        gamePanel.requestFocus();
        gameLoop = new GameLoop(gamePanel,this);
    }

    public void start(){
        gameLoop.setRunning(true);
        gameLoop.start();
    }

    public void keyEvents() {
        world.getPlayer().keyEvents();
        if (Keys.RESET.isPressed()) {
            world.getPlayer().setPosition(new Point3d(0, 0, 0));
            world.getPlayer().setOrientation(new Orientation(new Vector(1, 0, 0), new Vector(0, 1, 0)));
        }
        if (Keys.FULL_SCREEN.isPressedOneTick()) {
            Keys.FULL_SCREEN.setPressedOneTick(false);
            frame.setFullscreen(!frame.isFullscreen());
        }
        if (Keys.ESCAPE.isPressedOneTick()) {
            Keys.ESCAPE.setPressedOneTick(false);
            gameLoop.setRunning(false);
            world = null;
            gameLoop = null;
            frame.menuState();
        }
    }


    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public World getWorld() {
        return world;
    }

    public Frame getFrame() {
        return frame;
    }
}
