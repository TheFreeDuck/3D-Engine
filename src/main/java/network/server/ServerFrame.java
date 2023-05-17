package main.java.network.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFrame extends JFrame {

    private JLabel serverLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel clientsLabel;

    private int port;
    private ArrayList<ClientHandler> clients;
    private JTextArea consoleTextArea;

    public ServerFrame(int port, ArrayList<ClientHandler> clients, Server server) {
        this.port = port;
        this.clients = clients;

        setTitle("Server");

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                server.stop();
            }
        });
        setSize(400, 500);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        serverLabel = new JLabel("Server");
        serverLabel.setFont(new Font("Arial", Font.BOLD, 20));
        serverLabel.setHorizontalAlignment(JLabel.CENTER);

        ipLabel = new JLabel("IP Address: " + getIpAddress());
        ipLabel.setHorizontalAlignment(JLabel.CENTER);

        portLabel = new JLabel("Port Number: " + port);
        portLabel.setHorizontalAlignment(JLabel.CENTER);

        clientsLabel = new JLabel("Connected Clients: " + clients.size());
        clientsLabel.setHorizontalAlignment(JLabel.CENTER);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setLineWrap(true);
        consoleTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setMinimumSize(new Dimension(10, 10));

        mainPanel.add(serverLabel);
        mainPanel.add(ipLabel);
        mainPanel.add(portLabel);
        mainPanel.add(clientsLabel);
        mainPanel.add(scrollPane);

        add(mainPanel);
        setVisible(true);

        // Redirect console output to JTextArea
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleTextArea.append(String.valueOf((char) b));
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }
        }));
    }

    private String getIpAddress() {
        String ip;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().getHostAddress();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    public void updateClientsLabel(int numClients) {
        clientsLabel.setText("Connected Clients: " + numClients);
    }

}
