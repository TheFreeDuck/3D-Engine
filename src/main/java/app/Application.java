package main.java.app;

import com.formdev.flatlaf.FlatDarkLaf;

public class Application {
    Frame frame;

    public Application() {
        FlatDarkLaf.setup();
    }

    /**
     * stats the application
     */
    public void run(){
        frame = new Frame();
        MenuPanel menu = new MenuPanel(frame);
        frame.setContent(menu);
        frame.setVisible(true);
    }
}
