package satt.recorder.config;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import satt.recorder.listeners.KeyListener;
import satt.recorder.listeners.MouseListener;

import javax.annotation.PostConstruct;
import java.util.logging.Level;

@Configuration
public class JNativeHookConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JNativeHookConfiguration.class);

    private MouseListener mouseListener;
    private KeyListener keyListener;

    @Autowired
    public JNativeHookConfiguration(MouseListener mouseListener, KeyListener keyListener) {
        this.mouseListener = mouseListener;
        this.keyListener = keyListener;
    }

    @PostConstruct
    public void setup() {
        logger.info("Start configuring JNativeHook");

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            logger.error("There was a problem registering the native hook", ex);
            System.exit(1);
        }

        initLogger();

        // Setup a generic ConsoleHandler
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setLevel(Level.ALL);
//        log.addHandler(handler);

        GlobalScreen.addNativeMouseListener(mouseListener);
        GlobalScreen.addNativeKeyListener(keyListener);

        logger.info("Finish configuring JNativeHook");
    }

    private static void initLogger() {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        log.setUseParentHandlers(false);
        log.setLevel(Level.WARNING);
    }

}
