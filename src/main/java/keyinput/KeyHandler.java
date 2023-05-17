package main.java.keyinput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyHandler implements KeyListener {

    private final Map<Integer, List<Keys>> keys;

    public KeyHandler(InputStream inputStream){
        try {
            this.keys = loadKeysFromStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, List<Keys>> loadKeysFromStream(InputStream inputStream) throws IOException {
        Map<Integer, List<Keys>> keys = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String keyName = parts[0].trim();
                int keyCode = Integer.parseInt(parts[1].trim());
                Keys key = Keys.valueOf(keyName.toUpperCase());
                key.setKeyCode(keyCode);
                if (!keys.containsKey(keyCode)) {
                    keys.put(keyCode, new ArrayList<>());
                }
                keys.get(keyCode).add(key);
            }
        }
        reader.close();
        return keys;
    }

    public static void saveKeysToFile(File file, Map<Integer, List<Keys>> keys) throws IOException, URISyntaxException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (List<Keys> keyList : keys.values()) {
            for (Keys key : keyList) {
                writer.write(key.name() + ":" + key.getKeyCode() + "\n");
            }
        }
        writer.close();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        List<Keys> keyList = keys.get(e.getKeyCode());
        if (keyList != null) {
            for (Keys key : keyList) {
                key.setPressed(true);
                key.setPressedOneTick(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        List<Keys> keyList = keys.get(e.getKeyCode());
        if (keyList != null) {
            for (Keys key : keyList) {
                key.setPressed(false);
                key.setPressedOneTick(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    public void setKeyBinding(Keys key, int keyCode) {
        List<Keys> keyList = keys.get(keyCode);
        if (keyList == null) {
            keyList = new ArrayList<>();
            keys.put(keyCode, keyList);
        }
        key.setKeyCode(keyCode);
        keyList.add(key);
    }

    public void removeKeyBinding(Keys key) {
        int keyCode = key.getKeyCode();
        List<Keys> keyList = keys.get(keyCode);
        if (keyList != null) {
            keyList.remove(key);
            if (keyList.isEmpty()) {
                keys.remove(keyCode);
            }
        }
        key.setKeyCode(0);
    }

    public Map<Integer, List<Keys>> getKeys() {
        return keys;
    }
}
