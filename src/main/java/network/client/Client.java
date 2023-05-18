package main.java.network.client;

import main.java.game.Game;
import main.java.network.server.PlayerData;
import main.java.world3d.object3d.Object3d;
import main.java.world3d.object3d.standardobjects.Sphere;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Game game;
    private boolean alive;

    public Client(String address, int port, Game game) {
        this.game = game;
        try {
            socket = new Socket(address, port);
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            alive = true;

            System.out.println("Connected to server");
            new Thread(() -> {
                JOptionPane.showMessageDialog(null, "You have successfully connected to the 日本語ボス server \n If you are not a 日本語ボス disconnect immediately");
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
                updateOtherPlayers(playerData);
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
                    outStream.writeObject(new PlayerData(game.getWorld().getPlayer().getPosition(),game.getWorld().getPlayer().getOrientation()));
                    outStream.flush();
                    Thread.sleep(16);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
    }

    private void updateOtherPlayers(ArrayList<PlayerData> playerDataList){
        ArrayList<Object3d> otherPlayers = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            if(playerData != null) {
                Sphere sphere = new Sphere(0.5, playerData.getPosition(), playerData.getOrientation(),20,20);
                sphere.getMesh().setColor(Color.green);
                otherPlayers.add(sphere);
            }
        }
        try {
            MultiPlayerWorld world = (MultiPlayerWorld) game.getWorld();
            world.setOtherPlayers(otherPlayers);
        }catch (Exception e) {
            alive = false;

        }


    }
}
