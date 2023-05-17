package main.java.network.server;

import main.java.math.Point3d;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    private Server server;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Point3d position;

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
                // read a single byte from the client's input stream
                position = (Point3d) inStream.readObject();
            }
        } catch (IOException e) {
            // An error occurred while reading from the stream, assume the client has disconnected
            if (!socket.isClosed()) {
                disconnect();
            }
        } catch (Exception e) {
            // Catch any other exceptions that may occur
            e.printStackTrace();
            disconnect();
        }

    }
    public void sendPositions(ArrayList<Point3d> positions) {
        try {
            outStream.writeObject(positions);
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

    public Point3d getPosition() {
        return position;
    }
}
