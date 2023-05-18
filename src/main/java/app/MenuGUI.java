package main.java.app;

import main.java.game.Game;
import main.java.game.World;
import main.java.game.singleplayer.SinglePlayerWorld;
import main.java.keyinput.KeyBindingsPanel;
import main.java.network.client.Client;
import main.java.network.client.ClientInput;
import main.java.network.client.MultiPlayerGame;
import main.java.network.client.MultiPlayerWorld;
import main.java.network.server.ServerApp;
import main.java.game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JPanel {

    private JButton singlePlayerButton, multiPlayerButton, createServerButton, keyBindingsButton;
    private Frame frame;

    public MenuGUI(Frame frame) {
        this.frame = frame;
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(500, 500));

        // Initialize the GridBagLayout
        setLayout(new GridBagLayout());

        singlePlayerButton = new JButton("Singleplayer");
        singlePlayerButton.setFocusPainted(false);
        singlePlayerButton.setFocusable(false);
        multiPlayerButton = new JButton("Multiplayer");
        multiPlayerButton.setFocusable(false);
        multiPlayerButton.setFocusPainted(false);
        createServerButton = new JButton("Create Server");
        createServerButton.setFocusable(false);
        createServerButton.setFocusPainted(false);
        keyBindingsButton = new JButton("Key Bindings");
        keyBindingsButton.setFocusable(false);
        keyBindingsButton.setFocusPainted(false);

        // Set the button width to one third of the panel width
        int buttonWidth = this.getPreferredSize().width / 2;
        singlePlayerButton.setPreferredSize(new Dimension(buttonWidth, 70));
        multiPlayerButton.setPreferredSize(new Dimension(buttonWidth, 70));
        createServerButton.setPreferredSize(new Dimension(buttonWidth, 70));
        keyBindingsButton.setPreferredSize(new Dimension(buttonWidth, 70));

        // Add the buttons to the panel using the GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        add(singlePlayerButton, gbc);

        gbc.gridy = 2;
        add(multiPlayerButton, gbc);

        gbc.gridy = 3;
        add(createServerButton, gbc);

        gbc.gridy = 4;
        add(keyBindingsButton, gbc);

        gbc.gridy = 0;
        // Add the title text
        JLabel title = new JLabel("BINGBONG");
        title.setFont(new Font("arial", Font.BOLD, 24));
        title.setForeground(Color.white);
        add(title, gbc);

        // Add empty listeners for now
        singlePlayerButton.addActionListener(e -> {
            GamePanel gamePanel = new GamePanel();
            World world = new SinglePlayerWorld(gamePanel);
            Game game = new Game(world, frame, gamePanel);
            game.start();
        });
        multiPlayerButton.addActionListener(e -> {
            GamePanel gamePanel = new GamePanel();
            World world = new MultiPlayerWorld(gamePanel);
            MultiPlayerGame game = new MultiPlayerGame(world, frame, gamePanel);

            Client client = ClientInput.getClient(game);
            try {
                client.start();
            }catch (Exception ex){
                //do nothing
            }



            game.start();
        });
        createServerButton.addActionListener(e -> {
            new Thread(ServerApp::create).start();
        });
        keyBindingsButton.addActionListener(e -> {
            KeyBindingsPanel.main(null);
        });
    }
}