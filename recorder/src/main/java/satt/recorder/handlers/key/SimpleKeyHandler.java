package satt.recorder.handlers.key;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.stereotype.Component;

@Component
public class SimpleKeyHandler implements KeyEventHandler {

    @Override
    public boolean process(NativeKeyEvent e) {
        return false;
    }

}
