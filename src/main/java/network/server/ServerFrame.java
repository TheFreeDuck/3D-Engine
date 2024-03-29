package main.java.network.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ServerFrame extends JFrame {

    private JLabel serverLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel clientsLabel;

    private int port;
    private List<ClientHandler> clients;
    private JTextArea consoleTextArea;

    public ServerFrame(int port, List<ClientHandler> clients, Server server) {
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
        // Redirect console output to JTextArea and console
        // Create a custom OutputStream that writes to both consoleTextArea and System.out
        PrintStream consoleStream = new PrintStream(System.out) {
            @Override
            public void write(byte[] buf, int off, int len) {
                // Append the characters to the consoleTextArea
                String message = new String(buf, off, len);
                consoleTextArea.append(message);

                // Scroll to the end of the text area
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

                // Write the characters to System.out
                super.write(buf, off, len);
            }
        };

        // Redirect console output to the custom PrintStream
        System.setOut(consoleStream);
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
