package main.java.network.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Sendertest {
    public Sendertest(String address, int port) {
        Socket socket = null;
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.getOutputStream().write("{\"command\": \"add_deck\", \"deckName\":\"test123\"}".getBytes());
            DataInputStream ass = new DataInputStream(socket.getInputStream());
            System.out.println(ass.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Sendertest Sendertest = new Sendertest("10.21.251.58",8080);
    }
}