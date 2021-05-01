package satt.recorder.handlers.key;

import org.jnativehook.keyboard.NativeKeyEvent;
import satt.model.OsType;

public interface KeyEventHandler {

    int VC_RIGHT_SHIFT = 3638;

    boolean process(NativeKeyEvent e);

    default OsType getOsType() {
        // mean applicable for all systems
        return null;
    }

    default boolean isMacCommandPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.META_MASK) != 0;
    }

    default boolean isCtrPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0;
    }

    default boolean isAltPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0;
    }

    default boolean isShiftPressed(NativeKeyEvent e) {
        return (e.getModifiers() & NativeKeyEvent.SHIFT_MASK) != 0;
    }

}
