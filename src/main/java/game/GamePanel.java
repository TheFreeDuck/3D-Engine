package main.java.game;

import main.java.keyinput.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;

/**
 *
 * @author fredrik.cewersbredbe
 */
public class GamePanel extends JPanel {

    private final int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height/1.3);
    private final double aspectRatio = 2;
    private final int width = (int) (height * aspectRatio);
    private World world;
    private KeyHandler keyHandler;

    public GamePanel() {
        try{
            File bindingsFile = new File("bindings.txt");
            if(!bindingsFile.exists()){
                // create the bindings file and copy the default bindings
                InputStream defaultBindingsStream = getClass().getClassLoader().getResourceAsStream("default_bindings.txt");
                Files.copy(defaultBindingsStream, bindingsFile.toPath());
                JOptionPane.showMessageDialog(null,"Could not load your keybindings. The default ones will be used.");
            }
            keyHandler = new KeyHandler(new FileInputStream(bindingsFile));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Could not create or load your keybindings. The default ones will be used.");
            keyHandler = new KeyHandler(getClass().getClassLoader().getResourceAsStream("default_bindings.txt"));
        }

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(width, height));
        this.addKeyListener(keyHandler);
        setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(world == null) return;
        world.draw(g);
    }

    public double getAspectRatio(){
        return (double)getWidth()/getHeight();
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
