package satt.recorder.handlers.key;

import satt.model.OsType;

import java.awt.event.KeyEvent;

public interface KeyEventHandler {

    boolean process(KeyEvent e);

    default OsType getOsType() {
        // mean applicable for all systems
        return null;
    }

    default boolean isMacCommandPressed(KeyEvent e) {
        return (e.getModifiersEx() & KeyEvent.META_DOWN_MASK) != 0;
    }

    default boolean isCtrPressed(KeyEvent e) {
        return (e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0;
    }

    // option at MAC
    default boolean isAltPressed(KeyEvent e) {
        return (e.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) != 0;
    }

    default boolean isShiftPressed(KeyEvent e) {
        return (e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0;
    }

    default boolean isSpecialKey(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ALT ||
                e.getKeyCode() == KeyEvent.VK_CONTROL ||
                e.getKeyCode() == KeyEvent.VK_SHIFT ||
                e.getKeyCode() == KeyEvent.VK_META ||
                e.getKeyCode() == KeyEvent.VK_UNDEFINED;
    }
}
