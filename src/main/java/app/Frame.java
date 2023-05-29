package main.java.app;

import com.formdev.flatlaf.FlatDarkLaf;
import main.java.game.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frame extends JFrame {
    private boolean fullscreen;

    public Frame() {
        setTitle("3D Engine");
        Image icon = null;
        try {
            icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(icon);
        setMinimumSize(new Dimension(300,200));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);
        setFullscreen(false);
    }

    public void setFullscreen(Boolean fullscreen) {
        if (!this.fullscreen == fullscreen) {
            this.fullscreen = fullscreen;
            dispose();
            setUndecorated(fullscreen);
            if (fullscreen) {
                GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                device.setFullScreenWindow(this);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
            } else {
                pack();
                setLocationRelativeTo(null);
                setCursor(Cursor.getDefaultCursor());
            }
            setVisible(true);
            setAlwaysOnTop(true);
            setAlwaysOnTop(false);

        }

    }

    public void setContent(JPanel panel){
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public void addGamePanel(GamePanel gamePanel) {
        getContentPane().removeAll();
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[0].getClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }



    public void menuState(){
        setFullscreen(false);
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        setContent(new MenuPanel(this));
    }

    public boolean isFullscreen() {
        return fullscreen;
    }
}
