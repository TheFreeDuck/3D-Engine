package main.java.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    private Server server;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private PlayerData playerData;

    Socket socket;
    public ClientHandler(Socket socket,Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void start() {
        try {
            while (socket.isConnected()) {
                playerData = (PlayerData) inStream.readObject();
                System.out.println(playerData);
            }
        } catch (IOException e) {
            if (!socket.isClosed()) {
                disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        }

    }
    public void sendPlayerData(ArrayList<PlayerData> playerData) {
        try {
            outStream.writeObject(playerData);
            outStream.reset();
            outStream.flush();
        } catch (Exception e) {
            if(!socket.isClosed()){
                disconnect();
            }
        }
    }

    public void disconnect() {
        try {
            server.removeClient(this);
            if (!socket.isClosed() && socket.isConnected()) {
                inStream.close();
                socket.close();
            }


            System.out.println("Client from " + socket.getInetAddress().getHostAddress() + " disconnected");
        } catch (IOException e) {
            if (socket.isConnected()) {
                System.err.println("Error disconnecting client: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public boolean isConnected(){
        return socket.isConnected();
    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
