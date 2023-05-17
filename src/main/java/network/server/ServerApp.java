package main.java.network.server;

import javax.swing.*;

public class ServerApp {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(6969);
    }
    public static void create(){
        try {
            Server server = new Server();
            String input = JOptionPane.showInputDialog("Enter port number");
            if(input != null && !input.isEmpty()){
                server.start(Integer.parseInt(input));
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Not a port number","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
}
