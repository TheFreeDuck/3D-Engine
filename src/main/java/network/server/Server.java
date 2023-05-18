package main.java.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients;
    private Timer timer;
    private ServerFrame serverFrame;

    public Server() {
        clients = new ArrayList<>();
        timer = new Timer();
    }

    public void start(int port) {
        try {
            this.serverFrame = new ServerFrame(port, clients,this);
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendUpdates();
                }
            }, 0, 1000 / 60);

            while (true) {
                System.out.println("Waiting for clients...");
                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket,this);
                clients.add(clientHandler);
                serverFrame.updateClientsLabel(clients.size());
                new Thread(clientHandler::start).start();
                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {

        }
    }
    private void sendUpdates() {
        ArrayList<PlayerData> playerData = getPlayerData();
        for (int i = 0; i < clients.size(); i++) {
            ArrayList<PlayerData> otherPlayerData = (ArrayList<PlayerData>) playerData.clone();
            otherPlayerData.remove(i);
            clients.get(i).sendPlayerData(otherPlayerData);
        }
    }

    private ArrayList<PlayerData> getPlayerData(){
        ArrayList<PlayerData> playerData = new ArrayList<>();
        for(ClientHandler client : clients) {
            playerData.add(client.getPlayerData());
        }
        return playerData;
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
        serverFrame.updateClientsLabel(clients.size());
    }

    public void stop() {
        for(ClientHandler client : clients){
            client.disconnect();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        timer.cancel();
    }
}
