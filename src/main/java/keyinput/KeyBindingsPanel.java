package main.java.keyinput;

import main.java.app.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class KeyBindingsPanel extends JPanel {

    private final Map<Keys, JTextField> keyFields;

    KeyHandler keyHandler;

    public KeyBindingsPanel(Frame frame) {
        try{
            File bindingsFile = new File("bindings.txt");
            if(!bindingsFile.exists()){
                // create the bindings file and copy the default bindings
                InputStream defaultBindingsStream = getClass().getClassLoader().getResourceAsStream("default_bindings.txt");
                Files.copy(defaultBindingsStream, bindingsFile.toPath());
                JOptionPane.showMessageDialog(null,"Could not load your keybindings. The default ones will be used.");
            }
            keyHandler = new KeyHandler(new FileInputStream(bindingsFile));
        } catch (IOException e) {
            keyHandler = new KeyHandler(getClass().getClassLoader().getResourceAsStream("default_bindings.txt"));
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        this.keyFields = new HashMap<>();

        JPanel keysPanel = new JPanel();
        keysPanel.setLayout(new GridBagLayout());
        GridBagConstraints k = new GridBagConstraints();
        k.gridx = 0;
        k.gridy = 0;
        k.anchor = GridBagConstraints.LINE_START;
        k.insets = new Insets(5, 5, 5, 5);

        for (Keys key : Keys.values()) {
            JLabel keyLabel = new JLabel(key.name() + ":");
            k.gridx = 0;
            k.gridy++;
            keysPanel.add(keyLabel, k);

            JTextField keyField = new JTextField(KeyEvent.getKeyText(key.getKeyCode()));
            keyField.setPreferredSize(new Dimension(150, 25));
            keyField.setEditable(false);
            k.gridx = 1;
            keysPanel.add(keyField, k);
            keyFields.put(key, keyField);

            JButton changeButton = new JButton("Change");
            k.gridx = 2;
            keysPanel.add(changeButton, k);

            changeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keyField.setText("Press any key...");
                    KeyListener listener = new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int keyCode = e.getKeyCode();
                            keyField.setText(KeyEvent.getKeyText(keyCode));
                            // update the key binding map
                            keyHandler.setKeyBinding(key, keyCode);
                            // remove this listener
                            keyField.removeKeyListener(this);
                            keyField.setText(KeyEvent.getKeyText(key.getKeyCode()));
                        }
                    };
                    FocusListener focusListener = new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            keyField.setText(KeyEvent.getKeyText(key.getKeyCode()));
                        }
                    };
                    keyField.requestFocus();
                    keyField.addKeyListener(listener);
                    keyField.addFocusListener(focusListener);
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(keysPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, c);

        JButton saveButton = new JButton("Save");
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(saveButton, c);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    KeyHandler.saveKeysToFile(new File("bindings.txt"),keyHandler.getKeys());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });

        JButton resetButton = new JButton("Reset to Defaults");
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(resetButton, c);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputStream defaultBindingsStream = getClass().getClassLoader().getResourceAsStream("default_bindings.txt");
                    Files.copy(defaultBindingsStream, new File("bindings.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
                    keyHandler = new KeyHandler(new FileInputStream("bindings.txt"));
                    // update the text fields to display the new key bindings
                    for (Keys key : keyFields.keySet()) {
                        keyFields.get(key).setText(KeyEvent.getKeyText(key.getKeyCode()));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setResizable(false);
        KeyBindingsPanel keyBindingsPanel = new KeyBindingsPanel(frame);
        frame.setContent(keyBindingsPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
