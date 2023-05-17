package main.java.network.client;

import com.formdev.flatlaf.FlatDarkLaf;
import main.java.game.Game;

import javax.swing.*;

public class ClientInput {
    public static Client getClient(Game game) {
        try {

            CustomOptionPane customOptionPane = new CustomOptionPane();
            String address = JOptionPane.showInputDialog("Enter the network address (e.g. 4.tcp.eu.ngrok.io)");
            if (address == null) {
                game.getFrame().menuState();
                return null;
            }
            String answer = JOptionPane.showInputDialog("Enter the port number (e.g. 15589)");
            if(answer == null){
                game.getFrame().menuState();
            }else {
                int port = Integer.parseInt(answer);
                return new Client(address, port, game);
            }
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,  "That's not the input i wanted!","Nope", JOptionPane.ERROR_MESSAGE);
            game.getFrame().menuState();
            return null;
        }
    }
    public static class CustomOptionPane extends JOptionPane {
        public CustomOptionPane() {
            try {
                FlatDarkLaf.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
