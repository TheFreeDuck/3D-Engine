package main.java.network.client;

import main.java.network.server.PlayerData;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private MultiPlayerGame game;
    private boolean alive;

    public Client(String address, int port, MultiPlayerGame game) {
        this.game = game;
        try {
            socket = new Socket(address, port);
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            alive = true;

            System.out.println("Connected to server");
            new Thread(() -> {
                JOptionPane.showMessageDialog(null, "You have successfully connected to the server");
            }).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,  e,"Connection Failed", JOptionPane.ERROR_MESSAGE);
            game.getFrame().menuState();
        }

    }

    public void start(){
        new Thread(this::listen).start();
        new Thread(this::send).start();
    }

    private void listen() {
        while (alive || socket.isBound()) {
            try {
                ArrayList<PlayerData> playerData = (ArrayList<PlayerData>) inStream.readObject();
                game.updateOtherPlayers(playerData);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,  "Connection lost: " + e,"Connection lost", JOptionPane.ERROR_MESSAGE);
                game.getFrame().menuState();
                break;
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
    }

    private void send() {
            while (alive || socket.isBound()) {
                try {
                    PlayerData playerData = game.getPlayerData();
                    outStream.writeObject(playerData);
                    outStream.flush();
                    Thread.sleep(16);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
    }
}
