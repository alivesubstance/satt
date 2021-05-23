package satt.recorder.config;

import lombok.RequiredArgsConstructor;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import satt.recorder.listeners.KeyListener;
import satt.recorder.listeners.MouseListener;
import satt.recorder.listeners.MouseWheelListener;

import javax.annotation.PostConstruct;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

@Configuration
@RequiredArgsConstructor
public class JNativeHookConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JNativeHookConfiguration.class);

    private final KeyListener keyListener;
    private final MouseListener mouseListener;
    private final MouseWheelListener mouseWheelListener;

    @PostConstruct
    public void setup() {
        logger.info("Start configuring JNativeHook");
        initLogger();

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            logger.error("There was a problem registering the native hook", ex);
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(keyListener);
        GlobalScreen.addNativeMouseListener(mouseListener);
        GlobalScreen.addNativeMouseWheelListener(mouseWheelListener);

        logger.info("Finish configuring JNativeHook");
    }

    private static void initLogger() {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        log.setUseParentHandlers(false);
        log.setLevel(Level.WARNING);

        // Setup a generic ConsoleHandler
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.OFF);
        log.addHandler(handler);
    }

}
