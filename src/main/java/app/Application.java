package main.java.app;

import com.formdev.flatlaf.FlatDarkLaf;
import main.java.window.Frame;

public class Application {
    Frame frame;

    public Application() {
        FlatDarkLaf.setup();
        frame = new Frame();
    }

    public void run(){
        MenuGUI menu = new MenuGUI(frame);
        frame.setContent(menu);
        frame.setVisible(true);
    }
}
