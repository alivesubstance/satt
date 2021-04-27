package satt.recorder;

import app.recorder.listeners.KeyListener;
import app.recorder.listeners.MouseListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Recorder {


    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }


        initLogger();

        // Setup a generic ConsoleHandler
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setLevel(Level.ALL);
//        log.addHandler(handler);

        GlobalScreen.addNativeMouseListener(new MouseListener());
        GlobalScreen.addNativeKeyListener(new KeyListener());
    }

    private static void initLogger() {
        Logger log = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        log.setUseParentHandlers(false);
        log.setLevel(Level.WARNING);
    }
}
