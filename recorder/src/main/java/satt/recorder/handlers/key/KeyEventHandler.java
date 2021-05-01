package satt.recorder.handlers.key;

import org.jnativehook.keyboard.NativeKeyEvent;

public interface KeyEventHandler {

    boolean process(NativeKeyEvent e);

    default boolean isAltPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.META_MASK) != 0;
    }

    default boolean isCtrPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.META_MASK) != 0;
    }

}
