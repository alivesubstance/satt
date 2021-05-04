package satt.recorder.handlers.key;

import satt.model.OsType;

import java.awt.event.KeyEvent;

public interface KeyEventHandler {

    boolean process(KeyEvent e);

    default OsType getOsType() {
        // mean applicable for all systems
        return null;
    }

    default boolean isSpecialKey(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ALT ||
                e.getKeyCode() == KeyEvent.VK_CONTROL ||
                e.getKeyCode() == KeyEvent.VK_SHIFT ||
                e.getKeyCode() == KeyEvent.VK_META ||
                e.getKeyCode() == KeyEvent.VK_UNDEFINED;
    }
}
