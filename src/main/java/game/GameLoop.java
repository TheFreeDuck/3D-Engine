package main.java.game;

/**
 * @author Fredrik
 */
public class GameLoop extends Thread  {

    int ups = 60;
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
        int interval = SECOND_IN_NANO/ ups;
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

        }
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
