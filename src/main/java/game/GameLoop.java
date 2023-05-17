package main.java.game;

import java.awt.*;


/**
 * @author Fredrik
 */
public class GameLoop extends Thread  {

    int maxFps = 60;
    int currentFps;
    final int SECOND_IN_NANO = 1000000000;
    private boolean running = false;

    private Game game;
    private GamePanel gamePanel;
    private World world;

    public GameLoop(GamePanel gamePanel, Game game) {
        world = game.getWorld();
        this.gamePanel = gamePanel;
        this.game = game;
    }

    /**
     * handles how the game should be updated and drawn
     * @throws RuntimeException
     */
    @Override
    public void run() throws RuntimeException {
        long lastTime = System.nanoTime();
        long lastFpsCheck = System.nanoTime();
        long currentTime;
        int interval = SECOND_IN_NANO/maxFps;
        int fps = 0;
        while (running) {
            currentTime = System.nanoTime();
            if (currentTime - lastTime >= interval) {
                update();
                gamePanel.paintImmediately(gamePanel.getBounds());
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                fps++;
                lastTime = currentTime;
            }

            if (System.nanoTime() - lastFpsCheck >= SECOND_IN_NANO) {
                currentFps = fps;
                fps = 0;
                lastFpsCheck = System.nanoTime();
            }

            //https://gafferongames.com/post/fix_your_timestep/
        }
    }

    public void draw(Graphics g) {
        world.draw(g);
        drawFps(g);
    }

    /**
     * write how many fps the game loop is getting
     * @param g Graphics
     */
    private void drawFps(Graphics g) {
        Font fpsFont = new Font("arial", Font.PLAIN, 15);
        g.setFont(fpsFont);
        g.setColor(Color.green);
        g.drawString("fps: " + currentFps, 3, 15);
    }

    /**
     * updates the game
     */
    public void update() {
        game.keyEvents();
        world.keyEvents();
        world.update();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
